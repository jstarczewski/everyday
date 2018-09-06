package com.clakestudio.pc.everyday.data.settings;

import android.content.Context;

/**
 * Created by Jan on 9/6/2018.
 */

public class SettingsRepository {

    private Settable settable;
    private static SettingsRepository settingsRepository;

    public static SettingsRepository getInstance(Settable settable) {

        if (settingsRepository == null) {
            settingsRepository = new SettingsRepository(settable);
        }
        return settingsRepository;
    }

    private SettingsRepository(Settable settable) {
        this.settable = settable;
    }


    public void setInfoRead() {
        settable.setInfoRead();
    }


    public void setPassword() {
        settable.setPassword();
    }


    public void setFocusDurationTime(int time) {
        settable.setFocusDurationTime(time);
    }


    public void setGoal() {
        settable.setGoal();
    }


    public boolean isInfoRead() {
        return settable.isInfoRead();
    }


    public boolean isPasswordSet() {
        return settable.isPasswordSet();
    }

    public int getFocusDurationTime() {
        return settable.getFocusDurationTime();
    }
}
