package com.example.smcp;

import android.app.Activity;

public class StressEntry extends Activity {
	String stress;
	String stresstime;
	public String getStresstime() {
		return stresstime;
	}

	public void setStresstime(String stresstime) {
		this.stresstime = stresstime;
	}

	public StressEntry(String stress, String stresstime){
		this.stress = stress;
		this.stresstime = stresstime;
	}
	
	public String getStress() {
		return stress;
	}

	public void setStress(String stress) {
		this.stress = stress;
	}
	
}
