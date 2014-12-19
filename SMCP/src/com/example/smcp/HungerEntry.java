package com.example.smcp;

public class HungerEntry {
	
	private String date;
	private int level;
	
	
	public HungerEntry(String date, int level) {
		this.date = date;
		this.level = level;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	

}
