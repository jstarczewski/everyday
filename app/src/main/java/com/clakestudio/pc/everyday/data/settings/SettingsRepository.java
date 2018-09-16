package com.clakestudio.pc.everyday.data.settings;


/**
 * Created by Jan on 9/6/2018.
 */

public class SettingsRepository {

    /**
     * Settings repository created to provide basic "settings" -> options that need to be stored something
     * Everything is currently stored in sharedPreferences but in case of so called online stored the module
     * only needs to implement Settable interface and implement methods properly
     *
     * */


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


    public void setPassword(String password) {
        settable.setPassword(password);
    }


    public void setFocusDurationTime(int time) {
        settable.setFocusDurationTime(time);
    }


    public void setGoal(String goal) {
        settable.setGoal(goal);
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

    public boolean isGoalSet() {
        return settable.isGoalSet();
    }

    public String getPassword() {
        return settable.getPassword();
    }

    public String getGoal() {
        return settable.getGoal();
    }

    public boolean isReminderSet() {
        return settable.isReminderSet();
    }

    public int getCurrentDay() {
        return settable.getCurrentDay();
    }

    public void incrementCurrentDayCount() {
        settable.increaseCurrentDay();
    }

    public void setReminderTime(long time) {
        settable.setReminderTime(time);
    }

    public long getReminderTime() {
        return settable.getReminderTime();
    }
}
