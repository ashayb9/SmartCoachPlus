package com.example.smcp;

import android.app.Activity;
import android.os.Bundle;

public class InsertData extends Activity {
	
	private getdata data;
	
	public InsertData(String date, float lat, float lon, String note){
		data = new getdata();
		data.Date = date;
		data.Latitude = lat;
		data.Longitude = lon;
		data.Note = note;
	

	}
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.insertdata);
	
	 }
	
	
	public getdata getData() {
		return data;
	}


	public void setData(getdata data) {
		this.data = data;
	}


public class getdata{
	String Date;
	float Latitude;
	float Longitude;
	String Note;
	
}

}
