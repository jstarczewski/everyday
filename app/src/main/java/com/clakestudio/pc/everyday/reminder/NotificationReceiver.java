package com.clakestudio.pc.everyday.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.utils.SplashActivity;

/**
 * Created by Jan on 9/7/2018.
 */

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeatingIntent = new Intent(context, SplashActivity.class);
        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 24, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(context, "Ovo")
                .setSmallIcon(R.drawable.logosplash)
                .setContentTitle("Alarm")
                .setContentText("elooo")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notificationManager.notify(24, notification);
        /*

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "NOT_MSG")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.logosplash)
                .setContentTitle("Alarm")
                .setContentText("elooo")
                .setAutoCancel(true);


            notificationManager.notify(24, builder.build());*/

    }
}
