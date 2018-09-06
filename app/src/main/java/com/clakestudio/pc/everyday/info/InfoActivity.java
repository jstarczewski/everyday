package com.clakestudio.pc.everyday.info;

import android.os.Bundle;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.utils.BaseActivity;

public class InfoActivity extends BaseActivity {

    private InfoPresenter infoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        InfoFragment infoFragment = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (infoFragment == null) {

            infoFragment = InfoFragment.newInstance();
            BaseActivity.addFragmentToActivity(getSupportFragmentManager(), infoFragment, R.id.contentFrame);
        }

        infoPresenter = new InfoPresenter(SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(getApplicationContext())), infoFragment);

    }
}
