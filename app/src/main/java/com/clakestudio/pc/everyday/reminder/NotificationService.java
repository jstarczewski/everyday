package com.clakestudio.pc.everyday.reminder;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.utils.SplashActivity;

/**
 * Created by Jan on 9/7/2018.
 */

public class NotificationService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */


    private static final String CHANNEL_ID = "REMINDER";

    public NotificationService() {
        super("NotificationFocusService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent i = new Intent(this, SplashActivity.class);
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Jazda")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(PendingIntent.getActivity(this, 100, i, PendingIntent.FLAG_UPDATE_CURRENT))
                .build();

       manager.notify(100, notification);
      //  manager.

    }

}
