package com.clakestudio.pc.everyday.setgoal;

import android.support.annotation.NonNull;

import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

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
    public void setPassword(String string) {
        settingsRepository.setPassword(string);
        setGoalView.showSettingsActivity();
    }

    @Override
    public void displayGoalForThreeSeconds() {
        setGoalView.determineGoalTextViewVisibility();
        Thread waitingThread = new Thread();
        waitingThread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setGoalView.determineGoalTextViewVisibility();
    }
}
