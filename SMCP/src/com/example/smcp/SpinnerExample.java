package com.example.smcp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
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
        List<String> list = new ArrayList<String>();
        list.add("09.00 AM");
        list.add("06.00 AM");
        list.add("07.00 AM");
        list.add("08.00 AM");
        list.add("10.00 AM");
        list.add("11.00 AM");
        list.add("12.00 PM");
      
       
       
        
        
        
        
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                     (this, android.R.layout.simple_spinner_item,list);
                      
        dataAdapter.setDropDownViewResource
                     (android.R.layout.simple_spinner_dropdown_item);
                      
        spinner1.setAdapter(dataAdapter);
         
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
                        "Click above to change Reminder time : " +
                        "\n" + String.valueOf(spinner1.getSelectedItem()) ,
                        Toast.LENGTH_LONG).show();
            }
 
        });
 
    }
}
