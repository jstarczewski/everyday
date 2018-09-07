package com.clakestudio.pc.everyday.settings;

import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.data.settings.SharedPreferencesSettings;

/**
 * Created by Jan on 9/6/2018.
 */

public class SettingsPresenter implements SettingsContract.Presenter {


    private SettingsContract.View view;
    private SettingsRepository settingsRepository;

    public SettingsPresenter(SettingsRepository settingsRepository, SettingsContract.View view) {
        this.settingsRepository = settingsRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
