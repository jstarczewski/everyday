package com.clakestudio.pc.everyday.setgoal;


import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

/**
 * Created by Jan on 9/6/2018.
 */

public class SetGoalPresenter implements SetGoalContract.Presenter {

    private SettingsRepository settingsRepository;
    private SetGoalContract.View view;

    public SetGoalPresenter(SettingsRepository settingsRepository, SetGoalContract.View view) {
        this.settingsRepository = settingsRepository;
        this.view = view;
        view.setPresenter(this);

    }

    @Override
    public void start() {

    }

    @Override
    public void setGoal(String string) {
        if (string.isEmpty())
            view.showEmptyGoalToast();
        else {
            settingsRepository.setGoal(string);
            view.determineGoalTextViewVisibility();
            view.showGoalForThreeSeconds();
        }
    }

    @Override
    public void startSettingsActivity() {
        view.showStartSettingsActivity();
    }


}
