package com.example.smcp;

import android.app.Activity;
import android.os.Bundle;

public class OvereatingEntry extends Activity {
	String date;
	float latitude;
	float longitude;
	String note;	
	
	public OvereatingEntry(String date, float lat, float lon, String note){
		this.date = date;
		this.latitude = lat;
		this.longitude = lon;
	
		this.note = note;
	
		

	}
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.insertdata);
	
	 }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		date = date;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		longitude = longitude;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		note = note;
	}
	




}
