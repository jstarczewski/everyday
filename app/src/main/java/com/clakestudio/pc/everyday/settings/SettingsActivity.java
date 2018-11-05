package com.clakestudio.pc.everyday.settings;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.utils.BaseActivity;

public class SettingsActivity extends BaseActivity implements TimePickerDialog.OnTimeSetListener {

    private SettingsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SettingsFragment settingsFragment = (SettingsFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (settingsFragment == null) {
            settingsFragment = SettingsFragment.newInstance();
            BaseActivity.addFragmentToActivity(getSupportFragmentManager(), settingsFragment, R.id.contentFrame);

        }
        presenter = new SettingsPresenter(SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(getApplicationContext())), settingsFragment);
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.startShowDaysActivity();
    }

}
