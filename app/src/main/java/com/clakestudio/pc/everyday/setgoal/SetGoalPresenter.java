package com.clakestudio.pc.everyday.setgoal;

import android.support.annotation.NonNull;

import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Jan on 9/6/2018.
 */

public class SetGoalPresenter implements SetGoalContract.Presenter {

    private SettingsRepository settingsRepository;
    private SetGoalContract.View setGoalView;

    public SetGoalPresenter(@NonNull SettingsRepository settingsRepository, @NonNull SetGoalContract.View setGoalView) {
        this.settingsRepository = settingsRepository;
        this.setGoalView = setGoalView;
        setGoalView.setPresenter(this);

    }

    @Override
    public void start() {

    }

    @Override
    public void setGoal(String string) {
        settingsRepository.setGoal(string);
        setGoalView.determineGoalTextViewVisibility();
        setGoalView.showGoalForThreeSeconds();
    }


}
