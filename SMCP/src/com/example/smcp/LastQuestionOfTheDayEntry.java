package com.example.smcp;

import android.app.Activity;

public class LastQuestionOfTheDayEntry{
	
	String newdate;
	
	String newnotes;
	String newstress;
	
public LastQuestionOfTheDayEntry(String newdate, String newnotes, String newstress){
	this.newdate = newdate;
	this.newnotes = newnotes;
	this.newstress = newstress;
}
	public String getNewdate() {
		return newdate;
	}
	public void setNewdate(String newdate) {
		this.newdate = newdate;
	}
	public String getNewnotes() {
		return newnotes;
	}
	public void setNewnotes(String newnotes) {
		this.newnotes = newnotes;
	}
	public String getNewstress() {
		return newstress;
	}
	public void setNewstress(String newstress) {
		this.newstress = newstress;
	}
}
