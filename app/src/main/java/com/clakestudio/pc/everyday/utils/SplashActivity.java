package com.clakestudio.pc.everyday.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clakestudio.pc.everyday.countdown.CountdownActivity;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.info.InfoActivity;
import com.clakestudio.pc.everyday.setgoal.SetGoalActivity;
import com.clakestudio.pc.everyday.settings.SettingsActivity;
import com.clakestudio.pc.everyday.showdays.ShowDaysActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingsRepository settingsRepository = SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(this));

        if (!settingsRepository.isInfoRead()) {
            startActivity(new Intent(this, InfoActivity.class));
        } else if (!settingsRepository.isGoalSet()) {
            startActivity(new Intent(this, SetGoalActivity.class));
        }
        else if(!settingsRepository.isPasswordSet()) {
            startActivity(new Intent(this, CountdownActivity.class));
        } else {
            startActivity(new Intent(this, ShowDaysActivity.class));
        }
        finish();
    }
}
