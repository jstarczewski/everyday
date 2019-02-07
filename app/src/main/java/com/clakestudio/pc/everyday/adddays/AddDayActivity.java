package com.clakestudio.pc.everyday.adddays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.DayDatabase;
import com.clakestudio.pc.everyday.data.DayRepository;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.showdays.ShowDaysActivity;
import com.clakestudio.pc.everyday.utils.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddDayActivity extends BaseActivity {

    private AddDayPresenter addDayPresenter;
    private static final String pattern = "dd MM yyyy";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_day);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AddDayFragment addDayFragment = (AddDayFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (addDayFragment == null) {
            addDayFragment = AddDayFragment.newInstance();
            BaseActivity.addFragmentToActivity(getSupportFragmentManager(), addDayFragment, R.id.contentFrame);
        }
        addDayPresenter = new AddDayPresenter(DayRepository.getInstance(DayDatabase.getInstance(getApplicationContext()).dayDao()),
                SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(this))
                , addDayFragment
                , new SimpleDateFormat(pattern)
                , Calendar.getInstance()
        );
/*
        if (dayId!=-1) {
            addDayPresenter.loadCurrentDayInfo(getIntent().getExtras().getString("title"), getIntent().getExtras().getString("note"), dayId);
        }
        else {
            addDayPresenter.loadCurrentDayInfo(getIntent().getExtras().getString("title"), getIntent().getExtras().getString("note"), 0);
        }
*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, ShowDaysActivity.class));
        finish();
    }
}
