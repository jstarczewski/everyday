package com.clakestudio.pc.everyday.showgoal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.utils.BaseActivity;

public class ShowGoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_goal);

        ShowGoalFragment showGoalFragment = (ShowGoalFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (showGoalFragment==null) {
            showGoalFragment = ShowGoalFragment.newInstance();
            BaseActivity.addFragmentToActivity(getSupportFragmentManager(), showGoalFragment, R.id.contentFrame);



        }

        ShowGoalPresenter showGoalPresenter = new ShowGoalPresenter(SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(this)), showGoalFragment);

    }
}
