package com.clakestudio.pc.everyday.countdown;

import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

/**
 * Created by Jan on 9/10/2018.
 */

public class CountdownPresenter implements CountdownContract.Presenter {

    private SettingsRepository settingsRepository;
    private CountdownContract.View view;

    public CountdownPresenter(SettingsRepository settingsRepository ,CountdownContract.View view) {
        this.settingsRepository = settingsRepository;
        this.view=view;
        view.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public int getFocusDuration() {
        return settingsRepository.getFocusDurationTime();
    }

    @Override
    public void loadAddDayActivity() {
        view.startAddDayActivity();
    }

    @Override
    public void updateRemainingTime(int timeLeft) {
        view.updateTextViewCountDown(timeLeft);
    }
}
