package com.example.smcp;

import android.app.Activity;

public class WeightEntry extends Activity{
String weight;
String weightentrytime;




public WeightEntry(String weightentry, String weightentrytime) {
	this.weight = weightentry;
	this.weightentrytime = weightentrytime;
}

public String getWeight() {
	return weight;
}

public void setWeight(String weight) {
	this.weight = weight;
}
public String getWeightentrytime() {
	return weightentrytime;
}

public void setWeightentrytime(String weightentrytime) {
	this.weightentrytime = weightentrytime;
}
}
