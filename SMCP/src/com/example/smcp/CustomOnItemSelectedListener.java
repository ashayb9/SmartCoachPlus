package com.example.smcp;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class CustomOnItemSelectedListener implements OnItemSelectedListener{
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
            long id) {
         
        Toast.makeText(parent.getContext(),
                "Reminder is Set at : \n" + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_LONG).show();
    }
 
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
 
    }
}
