package com.clakestudio.pc.everyday.reminder.ui;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;


import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.reminder.AfterDismissListener;
import com.clakestudio.pc.everyday.reminder.AlarmUtils;
import com.clakestudio.pc.everyday.reminder.ReminderReceiver;
import com.clakestudio.pc.everyday.settings.SettingsPresenter;
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

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener, DialogInterface.OnDismissListener {

    private Calendar calendar;
    private SettingsRepository settingsRepository;
    private AfterDismissListener afterDismissListener;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        calendar = Calendar.getInstance();
        settingsRepository = SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(getContext()));

        return new TimePickerDialog(getContext(), this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), DateFormat.is24HourFormat(getContext()));
    }

    /**
     * Try solving this problem with  simple interface
     */

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        afterDismissListener.afterDismiss();
     /*   if (settingsPresenter != null) {
            settingsPresenter.start();
        }*/
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


        setCalendar(hourOfDay, minute);
        Context context = getContext();
        if (context != null) {
            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, ReminderReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            alarmManager.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 60 * 1000 * 5, pendingIntent);
            settingsRepository.setReminderTime(calendar.getTimeInMillis());
        }

    }

    private void setCalendar(int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 10);

    }


    public void setAfterDismissListener(AfterDismissListener afterDismissListener) {
        this.afterDismissListener = afterDismissListener;
    }


}
