package com.example.smcp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class CustomOnItemSelectedListener implements OnItemSelectedListener{
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
            long id) {
		Time t = (Time)parent.getItemAtPosition(pos);
         int h = t.hour;
         int m = t.minute;
         
        
        	 AlarmReciever.setAlarm(7686, h, m,  parent.getContext(), AlarmReciever.SLEEP_QUESTION);
         
         SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(parent.getContext());
        prefs.edit().putInt("sleep_question_time", pos).commit();
         Toast.makeText(parent.getContext(),
                "Reminder to enter daily sleep quality is Set at : \n" + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_LONG).show();
        
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
 
    }
}
