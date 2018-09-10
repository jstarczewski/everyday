package com.clakestudio.pc.everyday.reminder.ui;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;


import com.clakestudio.pc.everyday.reminder.ReminderReceiver;
import com.clakestudio.pc.everyday.utils.SplashActivity;

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


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        calendar = Calendar.getInstance();

        return new TimePickerDialog(getContext(), this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), DateFormat.is24HourFormat(getContext()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        setCalendar(hourOfDay, minute);
        Context context = getContext();
        Intent intent = new Intent(context, ReminderReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);

        AlarmManager am = null;
        if (context != null) {
            am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {        // KITKAT and later
                if (am != null) {
                    am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
                }
            } else {
                if (am != null) {
                    am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
                }
            }
            intent = new Intent("android.intent.action.ALARM_CHANGED");
            intent.putExtra("alarmSet", true);
            if (context != null) {
                context.sendBroadcast(intent);
            }
          /*  SimpleDateFormat fmt = new SimpleDateFormat("E HH:mm");
            Settings.System.putString(context.getContentResolver(),
                    Settings.System.NEXT_ALARM_FORMATTED,
                    fmt.format(c.getTime()));*/
        } else {
            Intent showIntent = new Intent(context, SplashActivity.class);
            PendingIntent showOperation = PendingIntent.getActivity(context, 0, showIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), showOperation);
            if (am != null) {
                am.setAlarmClock(alarmClockInfo, sender);
            }
        }

    }

    private void setCalendar(int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 10);

    }
}