package com.example.smcp;

public class Time {
	
	public int hour;
	public int minute;
	
	public Time(int hour, int minute){
		this.hour =hour;
		this.minute = minute;
	}
	
	
	public String toString(){
		return String.format("%02d:%02d %s", hour%12, minute, hour > 12 ?"PM":"AM");
	}
	

}
