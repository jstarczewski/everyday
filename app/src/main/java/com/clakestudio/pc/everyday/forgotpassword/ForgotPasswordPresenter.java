package com.clakestudio.pc.everyday.forgotpassword;

import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

/**
 * Created by Jan on 9/7/2018.
 */

public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {

    private SettingsRepository settingsRepository;
    private ForgotPasswordContract.View view;

    public ForgotPasswordPresenter(SettingsRepository settingsRepository, ForgotPasswordContract.View view) {
        this.settingsRepository = settingsRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void checkGoalCorrectness(String goal) {
        if (settingsRepository.isGoalSet() && goal.equals(settingsRepository.getGoal())) {
            view.startSettingsActivity();
        } else {
            view.showToastAboutGoalIncorrectness();
        }
    }
}
