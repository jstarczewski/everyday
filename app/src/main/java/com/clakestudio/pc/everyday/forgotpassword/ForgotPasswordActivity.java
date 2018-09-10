package com.clakestudio.pc.everyday.forgotpassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.utils.BaseActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ForgotPasswordPresenter passwordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ForgotPasswordFragment forgotPasswordFragment = (ForgotPasswordFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (forgotPasswordFragment == null) {
            forgotPasswordFragment = ForgotPasswordFragment.newInstance();
            BaseActivity.addFragmentToActivity(getSupportFragmentManager(), forgotPasswordFragment, R.id.contentFrame);

        }
        passwordPresenter = new ForgotPasswordPresenter(SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(getApplicationContext())), forgotPasswordFragment);
    }
}
