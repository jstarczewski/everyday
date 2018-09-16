package com.clakestudio.pc.everyday.showgoal;

import com.clakestudio.pc.everyday.data.AsyncAccessor;
import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

import java.util.List;

/**
 * Created by Jan on 9/12/2018.
 */

public class ShowGoalPresenter implements ShowGoalContract.Presenter {

    private ShowGoalContract.View view;
    private SettingsRepository settingsRepository;

    public ShowGoalPresenter(SettingsRepository settingsRepository, ShowGoalContract.View view) {
        this.settingsRepository = settingsRepository;
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void start() {
        loadGoal();
        loadCountdown();
    }

    @Override
    public void stop() {

    }

    @Override
    public void loadGoal() {
        view.showGoal(settingsRepository.getGoal());
    }

    @Override
    public void loadCountdown() {
        view.showStartCountdown();
    }

    @Override
    public void startDaysActivity() {
        view.showStartDaysActivity();
    }

}
