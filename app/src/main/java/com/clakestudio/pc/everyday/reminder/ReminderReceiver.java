package com.clakestudio.pc.everyday.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.utils.SplashActivity;

/**
 * Created by Jan on 9/7/2018.
 * added some if clauses to
 */

public class ReminderReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "REMINDER";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeatingIntent = new Intent(context, SplashActivity.class);
        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.logosplash)
                .setContentTitle("Focus time !")
                .setContentText("It is time to focus on your goal")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .build();
        if (notificationManager != null) {
            notificationManager.notify(0, notification);
        }

    }
}
