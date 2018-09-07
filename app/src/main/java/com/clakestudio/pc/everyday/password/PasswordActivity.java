package com.clakestudio.pc.everyday.password;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;
import com.clakestudio.pc.everyday.utils.BaseActivity;

public class PasswordActivity extends BaseActivity {

    private PasswordPresenter passwordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        PasswordFragment passwordFragment = (PasswordFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (passwordFragment==null) {
            passwordFragment = PasswordFragment.newInstance();
            BaseActivity.addFragmentToActivity(getSupportFragmentManager(), passwordFragment, R.id.contentFrame);
        }
        passwordPresenter = new PasswordPresenter(SettingsRepository.getInstance(SharedPreferencesSettings.getInstance(getApplicationContext())), passwordFragment);
    }
}
