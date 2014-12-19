package com.example.smcp;

import android.app.Activity;

public class QuestionsEntry extends Activity {

	String sleep;
	String sleephours;
	String sleeptimeentry;
	
	

	public QuestionsEntry(String sleep, String sleephours, String sleeptimeentry){
		this.sleep = sleep;
		this.sleephours = sleephours;
		this.sleeptimeentry = sleeptimeentry;
	}
	
	public String getSleep() {
		return sleep;
	}
	public void setSleep(String sleep) {
		this.sleep = sleep;
	}
	public String getSleephours() {
		return sleephours;
	}
	public void setSleephours(String sleephours) {
		this.sleephours = sleephours;
	}
	public String getSleeptimeentry() {
		return sleeptimeentry;
	}

	public void setSleeptimeentry(String sleeptimeentry) {
		this.sleeptimeentry = sleeptimeentry;
	}
	
}
