package com.clakestudio.pc.everyday.showgoal;

import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.showdays.ShowDaysContract;
import com.clakestudio.pc.everyday.showdays.ShowDaysPresenter;

/**
 * Created by Jan on 9/12/2018.
 */

public class ShowGoalPresenter implements ShowGoalContract.Presenter {

    private ShowGoalContract.View view;
    private SettingsRepository settingsRepository;

    public ShowGoalPresenter(SettingsRepository settingsRepository, ShowGoalContract.View view) {
        this.view=view;
        view.setPresenter(this);
    }


    @Override
    public void start() {
    }

    @Override
    public String getGoal() {
        return settingsRepository.getGoal();
    }

    @Override
    public void loadCountdown() {

    }
}
