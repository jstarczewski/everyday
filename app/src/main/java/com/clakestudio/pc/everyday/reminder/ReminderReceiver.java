package com.clakestudio.pc.everyday.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.showdays.ShowDaysActivity;

/**
 * Created by Jan on 9/7/210018.
 * added some if clauses to
 */

public class ReminderReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "EVERYDAY_REMINDER";
    private static final int notificationId = 0;

    @Override
    public void onReceive(Context context, Intent intent) {

        SettingsRepository settingsRepository = SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(context));

        if (!settingsRepository.isReminderSet())
            cancelScheduledActions(context);
        else {
            fireNotification(context, getPreparedNotificationAsBuilder(context));
        }
    }

    private void cancelScheduledActions(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.cancel(PendingIntent.getBroadcast(context, 0, new Intent(context, ReminderReceiver.class), 0));
        }
    }

    private NotificationCompat.Builder getPreparedNotificationAsBuilder(Context context) {

        Intent notificationIntent = new Intent(context, ShowDaysActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

        return new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.logosplash)
                .setContentTitle("Focus time")
                .setContentText("It is time to focus on your goal")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

    }

    private void fireNotification(Context context, NotificationCompat.Builder builder) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationId, builder.build());
    }
}
