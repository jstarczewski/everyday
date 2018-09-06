package com.clakestudio.pc.everyday.info;

import android.support.annotation.NonNull;

import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.showdays.ShowDaysContract;

/**
 * Created by Jan on 9/3/2018.
 */

public class InfoPresenter implements InfoContract.Presenter {

    private InfoContract.View view;
    private SettingsRepository settingsRepository;

    public InfoPresenter(@NonNull SettingsRepository settingsRepository, @NonNull InfoContract.View view) {
        this.settingsRepository = settingsRepository;
        this.view = view;
    }

    @Override
    public void start() {
        
    }
}
