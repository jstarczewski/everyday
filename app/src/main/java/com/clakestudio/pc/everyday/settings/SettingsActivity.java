package com.clakestudio.pc.everyday.settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.utils.BaseActivity;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SettingsFragment settingsFragment = (SettingsFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (settingsFragment == null) {
            settingsFragment = SettingsFragment.newInstance();
            BaseActivity.addFragmentToActivity(getSupportFragmentManager(), settingsFragment, R.id.contentFrame);

        }
        SettingsPresenter presenter = new SettingsPresenter(SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(getApplicationContext())), settingsFragment);
    }
}
