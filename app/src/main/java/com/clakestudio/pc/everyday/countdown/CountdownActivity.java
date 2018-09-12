package com.clakestudio.pc.everyday.countdown;

import android.os.Bundle;
import android.view.WindowManager;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.utils.BaseActivity;

public class CountdownActivity extends BaseActivity {


    private CountdownPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        CountdownFragment countdownFragment = (CountdownFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (countdownFragment==null) {
            countdownFragment = CountdownFragment.newInstance();
            BaseActivity.addFragmentToActivity(getSupportFragmentManager(), countdownFragment, R.id.contentFrame);
        }

        presenter = new CountdownPresenter(SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(getApplicationContext())), countdownFragment);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
