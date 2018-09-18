package com.clakestudio.pc.everyday.showdays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.DayDatabase;
import com.clakestudio.pc.everyday.data.DayRepository;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.utils.BaseActivity;

public class ShowDaysActivity extends BaseActivity {

    private ShowDaysPresenter showDaysPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_days);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ShowDaysFragment showDaysFragment = (ShowDaysFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (showDaysFragment == null) {
            showDaysFragment = ShowDaysFragment.newInstance();
            BaseActivity.addFragmentToActivity(getSupportFragmentManager(), showDaysFragment, R.id.contentFrame);
        }



        showDaysPresenter = new ShowDaysPresenter((DayRepository.getInstance(DayDatabase.getInstance(getApplicationContext()).dayDao())),
                SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(this))
                ,showDaysFragment);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_days, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showDaysPresenter.loadShowSettingsActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
