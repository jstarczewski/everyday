package com.clakestudio.pc.everyday.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.utils.SplashActivity;

/**
 * Created by Jan on 9/8/2018.
 */

public class BackgroundService extends Service {


    private boolean isRunning;
    private Context context;
    private Thread backgroundThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        this.context = this;
        this.isRunning = false;
        this.backgroundThread = new Thread(myTask);
    }

    private Runnable myTask = new Runnable() {
        public void run() {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Intent i = new Intent(context, SplashActivity.class);
            Notification notification = new Notification.Builder(context)
                    .setContentTitle("Jazda")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setContentIntent(PendingIntent.getActivity(context, 100, i, PendingIntent.FLAG_UPDATE_CURRENT))
                    .build();

            manager.notify(100, notification);

            stopSelf();
        }
    };

    @Override
    public void onDestroy() {
        this.isRunning = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!this.isRunning) {
            this.isRunning = true;
            this.backgroundThread.start();
        }
        return START_STICKY;
    }


}
