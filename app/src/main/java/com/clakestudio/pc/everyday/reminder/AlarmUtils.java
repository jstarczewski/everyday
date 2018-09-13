package com.clakestudio.pc.everyday.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * Created by Jan on 9/13/2018.
 */

public final class AlarmUtils {

    public static void setAlarm(Context context, long time, PendingIntent pendingIntent) {

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            //    alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pendingIntent);
        else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pendingIntent);
        else {
            if (alarmManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);
                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pendingIntent);
                }
            }
        }
    }
    public static void cancelAlarm(Context context, PendingIntent pendingIntent) {
       AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager!=null)
            alarmManager.cancel(pendingIntent);
    }

}
