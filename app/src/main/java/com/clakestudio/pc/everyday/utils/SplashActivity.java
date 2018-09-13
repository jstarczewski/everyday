package com.clakestudio.pc.everyday.utils;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.info.InfoActivity;
import com.clakestudio.pc.everyday.password.PasswordActivity;
import com.clakestudio.pc.everyday.reminder.AlarmUtils;
import com.clakestudio.pc.everyday.reminder.ReminderReceiver;
import com.clakestudio.pc.everyday.setgoal.SetGoalActivity;
import com.clakestudio.pc.everyday.showgoal.ShowGoalActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingsRepository settingsRepository = SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(this));

        if (settingsRepository.isReminderSet()) {
            Intent notificationIntent = new Intent(this, ReminderReceiver.class);
            PendingIntent sender = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmUtils.setAlarm(this, settingsRepository.getReminderTime(), sender);
        }


        if (!settingsRepository.isInfoRead()) {
            startActivity(new Intent(this, InfoActivity.class));
        } else if (!settingsRepository.isGoalSet()) {
            startActivity(new Intent(this, SetGoalActivity.class));
        }
        else if(settingsRepository.isPasswordSet()) {
            startActivity(new Intent(this, PasswordActivity.class));
        } else {
            startActivity(new Intent(this, ShowGoalActivity.class));
        }
        finish();
    }
}
