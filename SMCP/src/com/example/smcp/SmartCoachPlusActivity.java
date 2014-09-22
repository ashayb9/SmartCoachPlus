package com.example.smcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SmartCoachPlusActivity extends Activity {
	Button Overeating;
	String Text;
	private String Newnote;
	float Longi;
	float Lati;
	String formattedDate;
	Database db = new Database(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart_coach_plus);
		TextView setreminder = (TextView) findViewById(R.id.SetReminder);
		setreminder.setOnClickListener(reminder);
		Overeating = (Button) findViewById(R.id.OvereatingBtn);
		Overeating.setOnClickListener(myhandler);
		
		// GPS initialization
		LocationManager mLocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
		if (mLocManager != null) {
			/* Location is found using Network provider */
			Location location1 = mLocManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if (location1 != null) {
				Lati = (float) location1.getLatitude();
				Longi = (float) location1.getLongitude();
			}
		}
        LocationListener mLocListener = new MyLocationListener();
        mLocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocListener);
        scheduleAlarm(null);
	}

	View.OnClickListener reminder = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Intent intent = new Intent(getApplicationContext(),SpinnerExample.class);
			startActivity(intent);

		}
	};
	
		
		  
		
		
	
	
	View.OnClickListener myhandler = new View.OnClickListener() {
		public void onClick(View v) {
						
			Calendar c = Calendar.getInstance();
			System.out.println("Current time => " + c.getTime());
			int hr = c.getInstance().get(c.HOUR_OF_DAY);
			
            
			System.out.println("Hour of the day ==" + hr);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			formattedDate = df.format(c.getTime());

			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					SmartCoachPlusActivity.this);
			alertDialog.setTitle("Hey!");

			// Setting Dialog Message
			alertDialog.setMessage("What are you doing right now?");
			final EditText input = new EditText(SmartCoachPlusActivity.this);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT);
			input.setLayoutParams(lp);
			alertDialog.setView(input);
			alertDialog.setNeutralButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Newnote = input.getText().toString();
							dialog.dismiss();
							if (Newnote != null) {
								OvereatingEntry insertData = new OvereatingEntry(
										formattedDate, Lati, Longi, Newnote);
								
								db.open();
								db.insertRecordPhase1(insertData);
								db.close();
								Toast.makeText(SmartCoachPlusActivity.this,"New Episode Recorded",
										Toast.LENGTH_SHORT).show();
							}
						}
					});
			alertDialog.create().show();

			Toast.makeText(SmartCoachPlusActivity.this, Text,
					Toast.LENGTH_SHORT).show();
//			String destPath = db.getPath() ;
//			 InputStream is = null;
//			try {
//				is = new FileInputStream(new File(destPath));
//				 try {
//						CopyDB( is, new FileOutputStream(destPath));
//					} catch (FileNotFoundException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
	
			 
		}

	};
	
	
	
	
	
	// GPS location
	public class MyLocationListener implements LocationListener

	{

		@Override
		public void onLocationChanged(Location loc)
		// this is where i get the co-ordinates
		{
			Longi = (float) loc.getLongitude();
			Lati = (float) loc.getLatitude();
			Text = "Current Location= " + "Latitude =" + loc.getLatitude()
					+ "Longitude=" + loc.getLongitude();

		}

		@Override
		public void onProviderDisabled(String provider)

		{

			Toast.makeText(getApplicationContext(), "GPS Disabled",
					Toast.LENGTH_SHORT).show();

		}

		@Override
		public void onProviderEnabled(String provider)

		{

			Toast.makeText(getApplicationContext(), "GPS Enabled",
					Toast.LENGTH_SHORT).show();

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras)

		{

		}
	}
	

	
	public void CopyDB(InputStream inputStream , OutputStream outputStream) throws IOException {
		 //---copy 1K bytes at a time---
		 byte[] buffer = new byte[1024];
		 int length;
		 while ((length = inputStream.read(buffer)) > 0) {
		 outputStream.write(buffer, 0, length);
		 }
		 inputStream.close();
		 outputStream.close();
		 }
	 public void scheduleAlarm(View V)
	    {
	                 
		 Calendar c = Calendar.getInstance();
			System.out.println("Current time => " + c.getTime());
			int hr = c.getInstance().get(c.HOUR_OF_DAY);
			
		c.set(c.MINUTE, c.get(c.MINUTE)+1);
			
			Intent intentAlarm = new Intent(this, AlarmReciever.class);

			// create the object
			AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

			// set the alarm for particular time
			alarmManager.set(AlarmManager.RTC_WAKEUP, 1, PendingIntent
					.getBroadcast(this, 0, intentAlarm,
							PendingIntent.FLAG_UPDATE_CURRENT));
			Toast.makeText(this, "Alarm Scheduled for Tommrrow",
					Toast.LENGTH_LONG).show();
		
	    }

}
