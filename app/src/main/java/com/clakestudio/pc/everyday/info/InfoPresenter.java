package com.clakestudio.pc.everyday.info;

import android.support.annotation.NonNull;

import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

/**
 * Created by Jan on 9/3/2018.
 */

public class InfoPresenter implements InfoContract.Presenter {

    private InfoContract.View infoView;
    private SettingsRepository settingsRepository;

    public InfoPresenter(@NonNull SettingsRepository settingsRepository, @NonNull InfoContract.View infoView) {
        this.settingsRepository = settingsRepository;
        this.infoView = infoView;
        infoView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void setAppInfoUnderstood() {
        settingsRepository.setInfoRead();
        infoView.showStartSetGoalActivity();
    }

}
