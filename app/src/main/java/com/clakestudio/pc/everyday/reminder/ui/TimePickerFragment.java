package com.clakestudio.pc.everyday.reminder.ui;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;


import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.reminder.AfterDismissListener;
import com.clakestudio.pc.everyday.reminder.ReminderReceiver;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener, DialogInterface.OnDismissListener {

    private Calendar calendar;
    private SettingsRepository settingsRepository;
    private AfterDismissListener afterDismissListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        calendar = Calendar.getInstance();
        settingsRepository = SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(getContext()));
        return new TimePickerDialog(getContext(), this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), DateFormat.is24HourFormat(getContext()));
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        afterDismissListener.afterDismiss();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        setCalendar(hourOfDay, minute);
        prepareScheduledAction(getContext());

    }

    private void setCalendar(int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 10);

    }

    private void prepareScheduledAction(Context context) {
        if (context != null) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, ReminderReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            if (alarmManager != null) {
                alarmManager.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            }
            settingsRepository.setReminderTime(calendar.getTimeInMillis());
        }
    }

    public void setAfterDismissListener(AfterDismissListener afterDismissListener) {
        this.afterDismissListener = afterDismissListener;
    }


}
