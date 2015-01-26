package com.example.smcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.UserManager;
import android.preference.PreferenceManager;
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
import android.widget.Toast;

public class SmartCoachPlusActivity extends Activity {

	private static final String TAG = SmartCoachPlusActivity.class
			.getSimpleName();

	Button Overeating;
	String Text;
	private String Newnote;
	float Longi;
	float Lati;
	String formattedDate;
	String Overeatingstresslvl;
	String locationlabel;
	String Hungerlvl;
	private String OvereatingAnswer1;
	Database db = new Database(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart_coach_plus);
//		TextView setreminder = (TextView) findViewById(R.id.SetReminder);
//		setreminder.setOnClickListener(reminder);
		Overeating = (Button) findViewById(R.id.OvereatingBtn);
		Overeating.setOnClickListener(myhandler);

		// AlarmReciever.setAlarm(2, 11, 39, this,
		// AlarmReciever.StressQuestion1);

		// Log.d(TAG,"LOOPING "+i);
		// AlarmReciever.setAlarm(0, 9, 0, this, AlarmReciever.SLEEP_QUESTION);

		//

		AlarmReciever.setAlarm(1, 9, 0, this, AlarmReciever.WEIGHT_QUESTION);

		//AlarmReciever.setAlarm(2, 9, 0, this, AlarmReciever.SLEEP_QUESTION);

		//AlarmReciever.setAlarm(3, 9, 0, this, AlarmReciever.STRESS_QUESTION);
		AlarmReciever.setAlarm(4, 14, 0, this, AlarmReciever.STRESS_QUESTION);

		AlarmReciever.setAlarm(5, 22, 0, this, AlarmReciever.STRESS_QUESTION);
		// AlarmReciever.setAlarm(5, 9, 0, this, AlarmReciever.WeightQuestion);
		// AlarmReciever.setAlarm(6, 9, 0, this, AlarmReciever.WeightQuestion);
		//
		// AlarmReciever.setAlarm(0, 12, 0, this,
		// AlarmReciever.StressQuestion1);
		// AlarmReciever.setAlarm(1, 12, 0, this,
		// AlarmReciever.StressQuestion1);
		// AlarmReciever.setAlarm(2, 12, 0, this,
		// AlarmReciever.StressQuestion1);
		// AlarmReciever.setAlarm(3, 12, 0, this,
		// AlarmReciever.StressQuestion1);
		// AlarmReciever.setAlarm(4, 12, 0, this,
		// AlarmReciever.StressQuestion1);
		// AlarmReciever.setAlarm(5, 12, 0, this,
		// AlarmReciever.StressQuestion1);
		// AlarmReciever.setAlarm(6, 12, 0, this,
		// AlarmReciever.StressQuestion1);
		//

		// GPS initialization
		LocationManager mLocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		if (mLocManager != null) {
			/* Location is found using Network provider */
			Location location1 = mLocManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if (location1 != null) {
				Lati = (float) location1.getLatitude();
				Longi = (float) location1.getLongitude();
			}
		}
		LocationListener mLocListener = new MyLocationListener();
		mLocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,
				0, mLocListener);
		// scheduleAlarm(null);
	}

	@Override
	protected void onResume() {
		super.onResume();
		db.copyToStorage(this);
	}

	View.OnClickListener reminder = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			Intent intent = new Intent(getApplicationContext(),
					SpinnerExample.class);
			startActivity(intent);

		}
	};

	View.OnClickListener myhandler = new View.OnClickListener() {
		public void onClick(View v) 
	{
//
//			if(1 != 2){
//				Intent intent = new Intent(getApplicationContext(),QuestionDialogShow.class);
//				intent.putExtra("question", AlarmReciever.WEIGHT_QUESTION);
//				startActivity(intent);
//				return;
//			
//		}
			
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			prefs.edit().putLong("last_overeating", System.currentTimeMillis())
					.commit();

			Calendar c = Calendar.getInstance();
			System.out.println("Current time => " + c.getTime());
			int hr = c.getInstance().get(c.HOUR_OF_DAY);

			System.out.println("Hour of the day ==" + hr);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			formattedDate = df.format(c.getTime());

			AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(
					SmartCoachPlusActivity.this);
			final AlertDialog overeatingdialog;
			alertdialogbuilder.setTitle("Hi There!");
		//	alertdialogbuilder.setCancelable(false);
			// Setting Dialog Message
			LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
			View view = inflater.inflate(R.layout.overeating_layout, null);
			final EditText CurrentlyEating = (EditText)view.findViewById(R.id.overeating_answer1);
			final EditText Conditions = (EditText)view.findViewById(R.id.Overeating_conditions_Answer);
			alertdialogbuilder.setView(view);
			alertdialogbuilder.setNeutralButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Newnote = Conditions.getText().toString();
							OvereatingAnswer1 = CurrentlyEating.getText().toString();
							dialog.dismiss();
							AlertDialog.Builder nightalertdialog = new AlertDialog.Builder(
									SmartCoachPlusActivity.this);
							nightalertdialog.setTitle("Hello");
							LayoutInflater inflater = LayoutInflater
									.from(getApplicationContext());
							View view = inflater.inflate(
									R.layout.stress_and_hunger, null);
							final EditText stressAnswer = (EditText) view
									.findViewById(R.id.stress_edit_text);
							final EditText hungerAnswer = (EditText) view
							.findViewById(R.id.hunger_edit_Text1);
							nightalertdialog.setView(view);
							nightalertdialog.setNeutralButton(
									android.R.string.ok,
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface arg0, int arg1) {
											// TODO Auto-generated method stub
											Overeatingstresslvl = stressAnswer
													.getText().toString();
											Hungerlvl = hungerAnswer.getText().toString();
											locationlabel = "no value";
											OvereatingEntry insertData = new OvereatingEntry(
													formattedDate, Lati, Longi,
													locationlabel, Newnote,OvereatingAnswer1,
													Overeatingstresslvl,Hungerlvl);

											db.open();
											db.insertRecordPhase1(insertData);
											db.close();
											Toast.makeText(
													SmartCoachPlusActivity.this,
													"New Episode Recorded",
													Toast.LENGTH_SHORT).show();
										}

									});

							// overeatingdialog.cancel();
							final AlertDialog Stressdialog =	nightalertdialog.create();
							Stressdialog.show();
							Stressdialog.getButton(Dialog.BUTTON_NEUTRAL).setEnabled(false);
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
									
								try{	
										if(Integer.parseInt(s.toString()) > 10){
								
										stressAnswer.setText("10");
										}
//									else if(Integer.parseInt(s.toString()) == 0){
//										stressAnswer.setText("1");
//									}
								}catch(Exception ex){
									ex.printStackTrace();
								}	
								Stressdialog.getButton(Dialog.BUTTON_NEUTRAL).setEnabled(s.toString().length() > 0);
								}
							});
						}
					});
			(overeatingdialog = alertdialogbuilder.create()).show();
			final Button neutralButton = overeatingdialog
					.getButton(AlertDialog.BUTTON_NEUTRAL);
			neutralButton.setEnabled(false);
			TextWatcher watcher = new TextWatcher() {

				@Override
				public void afterTextChanged(Editable e) {
					Log.d(TAG, "aftertextchanged \'" + e.toString() + "\'");
					neutralButton.setEnabled(CurrentlyEating.getText().toString().length() > 0 && Conditions.getText().toString().length() >0);
				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1,
						int arg2, int arg3) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onTextChanged(CharSequence arg0, int arg1,
						int arg2, int arg3) {
					// TODO Auto-generated method stub

				}

			};
			CurrentlyEating.addTextChangedListener(watcher);
			Conditions.addTextChangedListener(watcher);
			
			Toast.makeText(SmartCoachPlusActivity.this, Text,
					Toast.LENGTH_SHORT).show();
			

		}

	};

	// GPS location
	public class MyLocationListener implements LocationListener

	{

		@Override
		public void onLocationChanged(Location loc)
		// this is where i get the co-ordinates
		{
			Longi = (float) loc.getLongitude();
			Lati = (float) loc.getLatitude();
			Text = "Current Location= " + "Latitude =" + loc.getLatitude()
					+ "Longitude=" + loc.getLongitude();

		}

		@Override
		public void onProviderDisabled(String provider)

		{

			Toast.makeText(getApplicationContext(), "GPS Disabled",
					Toast.LENGTH_SHORT).show();

		}

		@Override
		public void onProviderEnabled(String provider)

		{

			Toast.makeText(getApplicationContext(), "GPS Enabled",
					Toast.LENGTH_SHORT).show();

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras)

		{

		}
	}

	public void CopyDB(InputStream inputStream, OutputStream outputStream)
			throws IOException {
		// ---copy 1K bytes at a time---
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();
	}

	public void scheduleAlarm(View V) {

		Calendar c = Calendar.getInstance();
		System.out.println("Current time => " + c.getTime());
		int hr = c.getInstance().get(c.HOUR_OF_DAY);

		c.set(c.MINUTE, c.get(c.MINUTE) + 1);

		Intent intentAlarm = new Intent(this, AlarmReciever.class);

		// create the object
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

		// set the alarm for particular time
		alarmManager.set(AlarmManager.RTC_WAKEUP, 1, PendingIntent
				.getBroadcast(this, 0, intentAlarm,
						PendingIntent.FLAG_UPDATE_CURRENT));
		Toast.makeText(this, "Alarm Scheduled for Tommrrow", Toast.LENGTH_LONG)
				.show();

	}

}
