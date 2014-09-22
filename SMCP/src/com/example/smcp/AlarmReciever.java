package com.example.smcp;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class AlarmReciever extends BroadcastReceiver {
	
	private static final String TAG = AlarmReciever.class.getSimpleName();

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent dialogActivityIntent  = new Intent(context, QuestionDialogShow.class);//change to activty that shows dialog
		PendingIntent dialogIntent = PendingIntent.getActivity(context,
                0, dialogActivityIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
		
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		Notification n = builder.setSmallIcon(R.drawable.ic_launcher)
		.setContentTitle("SmartCoach Reminder")
		.setContentText("SmartCoach has a Question")
		.setContentIntent(dialogIntent)
		.build();
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		notificationManager.notify(0, n);
		Log.d(TAG, "alarm happened");
			// set title
			
		
	}

}
