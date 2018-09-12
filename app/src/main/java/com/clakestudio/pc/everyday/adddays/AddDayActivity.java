package com.clakestudio.pc.everyday.adddays;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.DayDatabase;
import com.clakestudio.pc.everyday.data.DayRepository;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.utils.BaseActivity;

public class AddDayActivity extends BaseActivity {

    private AddDayPresenter addDayPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_day);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AddDayFragment addDayFragment = (AddDayFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (addDayFragment == null) {
            addDayFragment = AddDayFragment.newInstance();
            BaseActivity.addFragmentToActivity(getSupportFragmentManager(), addDayFragment, R.id.contentFrame);
        }
        int dayId = getIntent().getExtras().getInt("dayId", -1);
            addDayPresenter = new AddDayPresenter(DayRepository.getInstance(DayDatabase.getInstance(getApplicationContext()).dayDao()),
                    SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(this)), addDayFragment);
/*
        if (dayId!=-1) {
            addDayPresenter.loadCurrentDayInfo(getIntent().getExtras().getString("title"), getIntent().getExtras().getString("note"), dayId);
        }
        else {
            addDayPresenter.loadCurrentDayInfo(getIntent().getExtras().getString("title"), getIntent().getExtras().getString("note"), 0);
        }
*/
    }


}
