package com.clakestudio.pc.everyday.reminder;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;


import com.clakestudio.pc.everyday.notification.AlarmReceiver;

import java.util.Calendar;


/**
 * Created by Jan on 9/7/2018.
 * <p>
 * This fragment is not sticking to MVP architecture because
 * it is easier to implement it the <rubbish>
 * <p>
 * <not the way it should>
 * <I know>
 * <That im using commenting wrong>
 * </That>
 * </I>
 * </not>
 * <p>
 * </rubbish> way
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    Calendar calendar;
    Intent intent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    private Context context;

    private static final String CHANNEL_ID = "REMINDER";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        calendar = Calendar.getInstance();

        return new TimePickerDialog(getContext(), this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), DateFormat.is24HourFormat(getContext()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
/*
        setCalendar(hourOfDay,minute);
       createNotificationChannel();
        intent = new Intent(getContext(), NotificationService.class);
        pendingIntent = PendingIntent.getService(getContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);*/
/*
        setCalendar(hourOfDay, minute);
        intent = new Intent(getActivity(), NotificationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);*/


        /**
         *
         * Seting the alarmmanager
         *
         *
         * */
        setCalendar(hourOfDay, minute);
        context = getContext();
        intent = new Intent(context, AlarmReceiver.class);
        boolean alarmRunning = (PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE) != null);
        if (!alarmRunning) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            // alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 15000, pendingIntent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        }


    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID, "REMINDER",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);

        }

    }

    private void setCalendar(int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 10);

    }
}
