package com.example.smcp;

import android.app.Activity;
import android.os.Bundle;

public class OvereatingEntry extends Activity {
	String date;
	float latitude;
	float longitude;
	String locationlabel;
	String EatingAnswer;

	

	String note;
	
	String OvereatingStress;
	String HungerLvl;
	


	
	public OvereatingEntry(String date, float lat, float lon,String locationlabel,String note,String EatingAns,String OStress,String Hungerlvl){
		this.date = date;
		this.latitude = lat;
		this.longitude = lon;
		this.locationlabel = locationlabel;
		this.note = note;
	    this.OvereatingStress = OStress;
	    this.HungerLvl = Hungerlvl; 
	    this.EatingAnswer = EatingAns;
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
		this.date = date;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getLocationlabel() {
		return locationlabel;
	}

	public void setLocationlabel(String locationlabel) {
		this.locationlabel = locationlabel;
	}
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	

	public String getOvereatingStress() {
		return OvereatingStress;
	}

	public void setOvereatingStress(String overeatingStress) {
		OvereatingStress = overeatingStress;
	}

	public String getHungerLvl() {
		return HungerLvl;
	}

	public void setHungerLvl(String hungerLvl) {
		HungerLvl = hungerLvl;
	}
	public String getEatingAnswer() {
		return EatingAnswer;
	}

	public void setEatingAnswer(String eatingAnswer) {
		EatingAnswer = eatingAnswer;
	}

}
