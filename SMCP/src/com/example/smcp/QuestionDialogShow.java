package com.example.smcp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuestionDialogShow extends ActionBarActivity {

	AlertDialog sleepTime;
	AlertDialog sleepQuality;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_dialog_show);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Hey!");

		// set dialog message
		alertDialogBuilder.setMessage("How many Hours did you sleep");
		EditText input = new EditText(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		input.setLayoutParams(lp);
		alertDialogBuilder.setView(input);
		alertDialogBuilder.setNeutralButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						dialog.dismiss();
						sleepQuality.show();
					}
				});
		// AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		// alertDialog.show();
		sleepTime = alertDialogBuilder.create();

		AlertDialog.Builder sleepQualityDialogBuilder = new AlertDialog.Builder(this);

		// set title
		sleepQualityDialogBuilder.setTitle("Hey!");

		// set dialog message
		sleepQualityDialogBuilder.setMessage("How Well Did You Sleep");
		TextView Scale = new TextView(this);
		Scale.setText("A scale from 1-very bad to 5-excellent");
		EditText input1 = new EditText(this);
		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(Scale);
		layout.addView(input1);
		input.setLayoutParams(lp2);
		sleepQualityDialogBuilder.setView(input1);
		
		sleepQualityDialogBuilder.setView(layout);
		sleepQualityDialogBuilder.setNeutralButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						dialog.dismiss();
					}
				});
		sleepQuality = sleepQualityDialogBuilder.create();
		
		sleepTime.show();

	}

}
