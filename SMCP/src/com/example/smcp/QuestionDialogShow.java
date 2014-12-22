package com.example.smcp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuestionDialogShow extends ActionBarActivity {
	String stresslvlstore;
	AlertDialog sleepTime;
	AlertDialog sleepQuality;
	String sleephr;
	String sleepqt;
	String weightentry;
	String lastquestionTimeAnswer;
	String lastquestionLocationAnswer;
	String seconddialogshow;
	String formattedDate;
	String lastquestionConditionsAnswer;
	String lastquestionStressAnswer;
	String lastquestionEatingAnswer;
	String lastquestionHungerlvlAnswer;
	String TAG;
	String hungerlvl;
	int maxLength = 2;
	int question;
	Database db1 = new Database(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_dialog_show);

		question = getIntent().getIntExtra("question", 0);
		Log.d(TAG, "question: "+question);
		if(question == AlarmReciever.WEIGHT_QUESTION){
			showWeightQuestion();
		} else if (question == AlarmReciever.SLEEP_QUESTION){
			showMorningQuestion();
		} else if (question == AlarmReciever.STRESS_QUESTION){
			showStressQuestion();
		} else if(question == AlarmReciever.LAST_QUESTION){
			//show last question thing
			lastReminderQuestion();
		} else {
			Log.wtf(TAG, "Invalid question id, shouldn't happen");
			finish();
		}
		
//		if (Question != AlarmReciever.QuestionSleep) {
//			showStressQuestion(Question);
//
//		} else {
//			// show morning question
//			showMorningQuestion();
//		}
//		if (Question == AlarmReciever.WeightQuestion) {
//			showweightQuestion();
//		}
//		if(overeatingClickedToday(getBaseContext())){
//			lastreminderquestion();
//		}
	}

	private void showStressQuestion() {
		AlertDialog.Builder nightalertdialog = new AlertDialog.Builder(this);
		nightalertdialog.setCancelable(false);
		nightalertdialog.setTitle("Hello");
		LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
		View view = inflater.inflate(R.layout.stress_question_layout, null);
		final EditText stressAnswer = (EditText)view.findViewById(R.id.stress_edit_text);
		
		nightalertdialog.setView(view);
		nightalertdialog.setNeutralButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						stresslvlstore = stressAnswer.getText().toString();
						Calendar c = Calendar.getInstance();
						System.out.println("Current time => " + c.getTime());
						int hr = c.getInstance().get(c.HOUR_OF_DAY);
						
			            
						
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						formattedDate = df.format(c.getTime());
						StressEntry stresslvl = new StressEntry(stresslvlstore,formattedDate);
						db1.open();
						db1.insertStresslvl(stresslvl);
						db1.close();
						showHungerDialog();
					}
				});
		final AlertDialog dialog = nightalertdialog.create();
		dialog.show();
		dialog.getButton(Dialog.BUTTON_NEUTRAL).setEnabled(false);
		stressAnswer.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				dialog.getButton(Dialog.BUTTON_NEUTRAL).setEnabled(s.toString().length() > 0);
			try{	
					if(Integer.parseInt(s.toString()) > 10){
			
					stressAnswer.setText("10");
				
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}	
			}
		});
		
	}

	

	private void showMorningQuestion() {
		AlertDialog.Builder sleepQualityDialogBuilder = new AlertDialog.Builder(
				this);
		sleepQualityDialogBuilder.setCancelable(false);
		// set title
		sleepQualityDialogBuilder
				.setTitle("Thanks for entering your sleep hours");

		// set dialog message
		sleepQualityDialogBuilder
				.setMessage("How rested do you feel?");
		TextView Scale = new TextView(this);
		Scale.setText("1 = Not at all, 5 = Very much");
		final EditText input1 = new EditText(this);
	
		input1.setInputType(InputType.TYPE_CLASS_NUMBER
				| InputType.TYPE_NUMBER_FLAG_DECIMAL
				| InputType.TYPE_NUMBER_FLAG_SIGNED);
		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(Scale);
		layout.addView(input1);
	//	input.setLayoutParams(lp2);
		sleepQualityDialogBuilder.setView(input1);

		sleepQualityDialogBuilder.setView(layout);
		sleepQualityDialogBuilder.setNeutralButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						Calendar c = Calendar.getInstance();
						System.out.println("Current time => " + c.getTime());
						int hr = c.getInstance().get(c.HOUR_OF_DAY);
						
			            
						
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						formattedDate = df.format(c.getTime());
						sleepqt = input1.getText().toString();
                        
						QuestionsEntry insertdata = new QuestionsEntry(sleepqt,
								sleephr,formattedDate);
						db1.open();
						db1.insertQuestion(insertdata);
						db1.close();

						dialog.dismiss();
						//finish();
						if(question == AlarmReciever.WEIGHT_QUESTION){
							showStressQuestion();

						}
					}
				});
		sleepQuality = sleepQualityDialogBuilder.create();
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setCancelable(false);
		alertDialogBuilder.setTitle("Hello!");
		
		// set dialog message
		alertDialogBuilder
				.setMessage("How many Hours did you sleep last night?");
		final EditText input = new EditText(this);
		    
		
		input.setInputType(InputType.TYPE_CLASS_NUMBER
				| InputType.TYPE_NUMBER_FLAG_DECIMAL
				| InputType.TYPE_NUMBER_FLAG_SIGNED);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		//input.setLayoutParams(lp);
		alertDialogBuilder.setView(input);
		alertDialogBuilder.setNeutralButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						sleephr = input.getText().toString();
						dialog.dismiss();
						sleepQuality.show();
						sleepQuality.getButton(Dialog.BUTTON_NEUTRAL).setEnabled(false);
						input1.addTextChangedListener(new TextWatcher(){

							@Override
							public void afterTextChanged(Editable e) {
								// TODO Auto-generated method stub
								sleepQuality.getButton(Dialog.BUTTON_NEUTRAL).setEnabled(e.toString().length() > 0);
								try{if(Integer.parseInt(e.toString()) > 5){
									input1.setText("5");
								}else if(Integer.parseInt(e.toString()) == 0){
									input1.setText("1");
								}
								}catch(Exception ex){
									ex.printStackTrace();
								}
								
							}

							@Override
							public void beforeTextChanged(CharSequence arg0, int arg1,
									int arg2, int arg3) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onTextChanged(CharSequence arg0, int arg1, int arg2,
									int arg3) {
								// TODO Auto-generated method stub
								
							}
							
						});
						
					}
				});
	
		sleepTime = alertDialogBuilder.create();
		sleepTime.show();
		sleepTime.getButton(Dialog.BUTTON_NEUTRAL).setEnabled(false);
		input.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				sleepTime.getButton(Dialog.BUTTON_NEUTRAL).setEnabled(s.toString().length() > 0);
				try {
					if(Integer.parseInt(s.toString()) > 24){				
						input.setText("24");
					}else if(Integer.parseInt(s.toString()) == 0){
						input.setText("1");
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
			}
		});


		
	}

	private void showWeightQuestion() {
		AlertDialog.Builder weightquestiondialog = new AlertDialog.Builder(this);
		weightquestiondialog.setCancelable(false);
		weightquestiondialog.setTitle("Hi!");
		weightquestiondialog.setMessage("Would You like To Enter Your Weight?");
		weightquestiondialog.setPositiveButton(android.R.string.yes,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// User clicked OK button

						AlertDialog.Builder enterweight = new AlertDialog.Builder(
								QuestionDialogShow.this);
						enterweight.setTitle("Hi!");
						LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
						View view = inflater.inflate(R.layout.weight_question_layout, null);
						final EditText weight = (EditText)view.findViewById(R.id.weight_edit_Text1);
						
						enterweight.setView(view);
						enterweight.setNeutralButton(android.R.string.yes,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										
										Calendar c = Calendar.getInstance();
										System.out.println("Current time => " + c.getTime());
										int hr = c.getInstance().get(c.HOUR_OF_DAY);
										
							            
										
										SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										formattedDate = df.format(c.getTime());
										weightentry = weight.getText()
												.toString();

										WeightEntry insertdata = new WeightEntry(
												weightentry,formattedDate);
										db1.open();
										db1.insertDailyWeight(insertdata);
										db1.close();

										dialog.dismiss();
										showMorningQuestion();


									}
								});
						enterweight.show();
						
					}

				});
		weightquestiondialog.setNegativeButton(android.R.string.no,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						showMorningQuestion();
					}
				});
		
								
		AlertDialog weightDialog = weightquestiondialog.create();
		weightDialog.show();
		Button yesButton = weightDialog.getButton(AlertDialog.BUTTON_POSITIVE);
		yesButton.setText("Yes");
		yesButton.invalidate();
		Button noButton = weightDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
		noButton.setText("No");
		noButton.invalidate();
		
		
	}
     
	private void lastReminderQuestion(){
		AlertDialog.Builder lastquestiondialog = new AlertDialog.Builder(this);
		lastquestiondialog.setTitle("Hello Again!");
		lastquestiondialog.setMessage("You didn’t enter any overeating today. Was there an overeating episode you forgot to enter?");
		lastquestiondialog.setPositiveButton(android.R.string.yes,
				new DialogInterface.OnClickListener(){
           
        
      
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						ifyeswaspressedlastreminder();
						 
						     
					}
			
		});
		lastquestiondialog.setNegativeButton(android.R.string.no,
		new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// User cancelled the dialog
				dialog.dismiss();
			}
		});
	
	lastquestiondialog.create().show();
	
	}
	
	// if(overeatingClickedToday(context)){
	// showklfldsjflsdialog
	// }
	
	private void ifyeswaspressedlastreminder(){
		 AlertDialog.Builder LastQuestions = new AlertDialog.Builder(QuestionDialogShow.this);
		 LastQuestions.setTitle("Hello!");
		 LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
		 View view = inflater.inflate(R.layout.last_reminder_dialog, null);
		 EditText txtfieldTime = (EditText)view.findViewById(R.id.last_edittxt_time);
		 EditText txtfieldLocation = (EditText)view.findViewById(R.id.editText_location);
		 EditText txtfieldNote = (EditText)view.findViewById(R.id.edit_txt_note);
		 EditText txtfieldStress = (EditText)view.findViewById(R.id.editText_stressLvl);
		 EditText txtfieldEating = (EditText)view.findViewById(R.id.edit_txt_Eating);
		 EditText txrfieldHungerLvl = (EditText)view.findViewById(R.id.editText_HungerLvl);
		 lastquestionTimeAnswer = txtfieldTime.getText().toString();
		 lastquestionLocationAnswer = txtfieldLocation.getText().toString();
		 lastquestionConditionsAnswer = txtfieldNote.getText().toString();
		 lastquestionStressAnswer = txtfieldStress.getText().toString();
		 lastquestionEatingAnswer = txtfieldEating.getText().toString();
		 lastquestionHungerlvlAnswer = txrfieldHungerLvl.getText().toString();
		 LastQuestions.setView(view);
		
		 LastQuestions.setNeutralButton(android.R.string.ok,
				new DialogInterface.OnClickListener()
		 
				 {
                   
					@Override
					public void onClick(final DialogInterface dialog,
							int which) {
						OvereatingEntry insertData = new OvereatingEntry(lastquestionTimeAnswer,00,00,lastquestionLocationAnswer,lastquestionConditionsAnswer,lastquestionEatingAnswer,lastquestionStressAnswer,lastquestionHungerlvlAnswer );
						db1.open();
						db1.insertRecordPhase1(insertData);
						db1.close();
						dialog.dismiss();
						// TODO Auto-generated method stub
						finish();
					}
			 
				 });
		 LastQuestions.create().show();
	}
	
public void showHungerDialog(){
	AlertDialog.Builder hungeralertdialog = new AlertDialog.Builder(this);
	hungeralertdialog.setCancelable(false);
	hungeralertdialog.setTitle("Hello");
	LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
	View view = inflater.inflate(R.layout.hungerlvl_layout, null);
	final EditText HungerAnswer = (EditText)view.findViewById(R.id.hungerlvl_editText1);
	
	hungeralertdialog.setView(view);
	hungeralertdialog.setNeutralButton(android.R.string.ok,
				new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method 
						hungerlvl = HungerAnswer.getText().toString();
						Calendar c = Calendar.getInstance();
						System.out.println("Current time => " + c.getTime());
						int hr = c.getInstance().get(c.HOUR_OF_DAY);
						
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						formattedDate = df.format(c.getTime());
						
						HungerEntry insertData = new HungerEntry(formattedDate, Integer.parseInt(hungerlvl));
						
						db1.open();
						db1.insertHungerLvl(insertData);
						db1.close();
						finish();
						
					}});
	
	final AlertDialog hungerDialog = hungeralertdialog.create();
	HungerAnswer.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				hungerDialog.getButton(Dialog.BUTTON_NEUTRAL).setEnabled(s.toString().length() > 0);
			try{	
					if(Integer.parseInt(s.toString()) > 10){
			
					HungerAnswer.setText("10");
				
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}	
			}
		});
	
	hungerDialog.show();

	hungerDialog.getButton(Dialog.BUTTON_NEUTRAL).setEnabled(false);
}	
	public static boolean overeatingClickedToday(Context c) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(c);
		long time = prefs.getLong("last_overeating", 0);
		long timeNow = System.currentTimeMillis();
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR, 8);
		cal.set(Calendar.MINUTE, 0);
		long eightOClock = cal.getTimeInMillis();
		return time < eightOClock;
		
		//return (timeNow - time >= 1000 * 60 * 60 * 20);
	}
}
