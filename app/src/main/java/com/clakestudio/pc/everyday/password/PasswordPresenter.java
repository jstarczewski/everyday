package com.clakestudio.pc.everyday.password;

import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

/**
 * Created by Jan on 9/6/2018.
 */

public class PasswordPresenter implements PasswordContract.Presenter {

    private SettingsRepository settingsRepository;
    private PasswordContract.View view;

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    PasswordPresenter(SettingsRepository settingsRepository, PasswordContract.View view) {
        this.settingsRepository = settingsRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void checkPasswordCorrectness(String password) {
        if (password.length() == 4 && password.equals(settingsRepository.getPassword()))
            view.showStartShowGoalActivity();
        else {
            if (password.length() == 4)
                view.showWrongPasswordToast();
        }
    }

    @Override
    public void startForgotPasswordActivity() {
        view.showStartForgotPasswordActivity();
    }
}
