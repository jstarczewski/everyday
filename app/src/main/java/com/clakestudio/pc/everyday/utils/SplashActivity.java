package com.clakestudio.pc.everyday.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clakestudio.pc.everyday.R;
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
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "REMINDER";
            String description = "DECRIPTION";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("EVERYDAY", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
