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

    PasswordPresenter(SettingsRepository settingsRepository, PasswordContract.View view) {
        this.settingsRepository = settingsRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public boolean isPasswordCorrect(String password) {
        // no need to check whether password is null cuz when it is gonna be false
        return password.equals(settingsRepository.getPassword());
    }
}
