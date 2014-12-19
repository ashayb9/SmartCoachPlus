package com.example.smcp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerExample extends Activity{
	private Spinner spinner1;
    private Button btnSubmit;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder);
 
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<Time> list = new ArrayList<Time>();
       // for(int i = 6; i < 11; i++){
        	list.add(new Time(6, 0));
        	list.add(new Time(7,0));
        	list.add(new Time(8,0));
        	list.add(new Time(9,0));
        	list.add(new Time(10,0));
        	list.add(new Time(11,0));
        
      
       
       
        
        
        
        
        ArrayAdapter<Time> dataAdapter = new ArrayAdapter<Time>
                     (this, android.R.layout.simple_spinner_item,list);
                      
        dataAdapter.setDropDownViewResource
                     (android.R.layout.simple_spinner_dropdown_item);
                      
        spinner1.setAdapter(dataAdapter);
        
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int selected = prefs.getInt("sleep_question_time", 0);
        spinner1.setSelection(selected);
         
        // Spinner item selection Listener 
        addListenerOnSpinnerItemSelection();
         
        // Button click Listener
        addListenerOnButton();
         
 
    }
 
    // Add spinner data
     
    public void addListenerOnSpinnerItemSelection(){
         
                spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
     
    //get the selected dropdown list value
     
    public void addListenerOnButton() {
 
        spinner1 = (Spinner) findViewById(R.id.spinner1);
         
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
 
        btnSubmit.setOnClickListener(new OnClickListener() {
 
            public void onClick(View v) {
 
                Toast.makeText(SpinnerExample.this,
                        "Click on the time above to change reminder for daily sleep quality entry : " +
                        "\n" + String.valueOf(spinner1.getSelectedItem()) ,
                        Toast.LENGTH_LONG).show();
            }
 
        });
 
    }
}
