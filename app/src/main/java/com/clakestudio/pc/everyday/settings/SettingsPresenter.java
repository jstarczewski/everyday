package com.clakestudio.pc.everyday.settings;


import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;

import java.util.Calendar;

/**
 * Created by Jan on 9/6/2018.
 */

public class SettingsPresenter implements SettingsContract.Presenter {


    private SettingsContract.View view;
    private SettingsRepository settingsRepository;
    private Calendar calendar;

    public SettingsPresenter(SettingsRepository settingsRepository, SettingsContract.View view) {
        this.settingsRepository = settingsRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }


    @Override
    public int getCurrentHour() {
        return 0;
    }

    @Override
    public int getCurrentMinute() {
        return 0;
    }
}
