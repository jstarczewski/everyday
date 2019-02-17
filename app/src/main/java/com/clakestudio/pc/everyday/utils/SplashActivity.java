package com.clakestudio.pc.everyday.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.info.InfoActivity;
import com.clakestudio.pc.everyday.password.PasswordActivity;
import com.clakestudio.pc.everyday.setgoal.SetGoalActivity;
import com.clakestudio.pc.everyday.showgoal.ShowGoalActivity;

public class SplashActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "EVERYDAY_REMINDER";
    private static final String CHANNEL_NAME = "EVERYDAY_CHANNEL";
    private static final String CHANNEL_DESCRIPTION = "EVERYDAY_NOTIFICATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        createNotificationChannel();
        SettingsRepository settingsRepository = SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(this));

        if (!settingsRepository.isInfoRead()) {
            startActivity(new Intent(this, InfoActivity.class));
        } else if (!settingsRepository.isGoalSet()) {
            startActivity(new Intent(this, SetGoalActivity.class));
        } else if (settingsRepository.isPasswordSet()) {
            startActivity(new Intent(this, PasswordActivity.class));
        } else {
            startActivity(new Intent(this, ShowGoalActivity.class));
        }
        finish();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(CHANNEL_DESCRIPTION);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
