package com.example.smcp;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class AlarmReciever extends BroadcastReceiver {
	
	private static final String TAG = AlarmReciever.class.getSimpleName();
	public static final int SLEEP_QUESTION = 0;
	public static final int STRESS_QUESTION = 1;
	
	public static final int WEIGHT_QUESTION = 2;
	public static final int LAST_QUESTION= 3;
	public static void setAlarm(int request,  int hour, int minute, Context context, int Question ) {
		 Calendar alarm = Calendar.getInstance();
		// alarm.setTimeInMillis(System.currentTimeMillis());
		// alarm.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		 alarm.set(Calendar.HOUR_OF_DAY, hour);
		 alarm.set(Calendar.MINUTE, minute);
		 long alarmTime = alarm.getTimeInMillis();
		 //Also change the time to 24 hours.
		 AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		 Intent intent = new Intent(context, AlarmReciever.class);
		 intent.putExtra("question",Question);
		 PendingIntent pending = PendingIntent.getBroadcast(context, request, intent, 0);
		 manager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime, AlarmManager.INTERVAL_DAY , pending);
		 }
	
	@Override
	public void onReceive(Context context, Intent intent) {
		int Question = intent.getIntExtra("question", 0);
		Log.d(TAG, "ALARM HAPPENED "+Question);
		Intent dialogActivityIntent  = new Intent(context, QuestionDialogShow.class);//change to activty that shows dialog
		dialogActivityIntent.putExtra("question", Question);
		PendingIntent dialogIntent = PendingIntent.getActivity(context,
                0, dialogActivityIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
		
		if (Question == LAST_QUESTION && QuestionDialogShow.overeatingClickedToday(context)) {
			return;
		}
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		Notification n = builder.setSmallIcon(R.drawable.ic_launcher)
		.setContentTitle("SmartCoach Reminder")
		.setContentText("SmartCoach+ has a Question")
		.setContentIntent(dialogIntent)
		.build();
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		notificationManager.notify(0, n);
		Log.d(TAG, "alarm happened");
			// set title
			
		
	}

}
