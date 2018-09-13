package com.clakestudio.pc.everyday.data.settings;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jan on 9/6/2018.
 */

public class SharedPreferencesSettings implements Settable {

    /**
     * Could have been an dependency inejction but passing only a Context makes it easier to store data
     * This gonna be an singleton ->
     * -> lets whether it works :0
     * The cons are problems with testing
     */

    /*
    private static final String INFO_READ = "INFO_READ";
    private static final String PASSWORD = "PASSWORD";
    private static final String FOCUS_DURATION = "FOCUS_DURATION";
    private static final String GOAL = "GOAL";
    private static final String NOT_SET = "NOT_SET";
    private static final String REMINDER = "REMINDER";
    */

    private static SharedPreferencesSettings sharedPreferencesSettings;
    private SharedPreferences sharedPreferences;

    public static SharedPreferencesSettings getInstance(Context context) {
        if (sharedPreferencesSettings == null) {
            sharedPreferencesSettings = new SharedPreferencesSettings(context);
        }
        return sharedPreferencesSettings;
    }

    private SharedPreferencesSettings(Context context) {
        this.sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    @Override
    public void setInfoRead() {
        sharedPreferences.edit().putBoolean(Settings.INFO_RED.toString(), true).apply();
    }

    @Override
    public void setPassword(String password) {
        sharedPreferences.edit().putString(Settings.PASSWORD.toString(), password).apply();
    }

    @Override
    public void setFocusDurationTime(int time) {
        sharedPreferences.edit().putInt(Settings.FOCUS_DURATION.toString(), time).apply();
    }

    @Override
    public void setGoal(String goal) {
        sharedPreferences.edit().putString(Settings.GOAL.toString(), goal).apply();
    }

    @Override
    public boolean isInfoRead() {
        return (sharedPreferences.getBoolean(Settings.INFO_RED.toString(), false));
    }

    @Override
    public boolean isPasswordSet() {
        return !(sharedPreferences.getString(Settings.PASSWORD.toString(), Settings.NOT_SET.toString()).equals(Settings.NOT_SET.toString()));
    }

    @Override
    public int getFocusDurationTime() {
        return sharedPreferences.getInt(Settings.FOCUS_DURATION.toString(), 3);
    }

    @Override
    public boolean isGoalSet() {
        return !(sharedPreferences.getString(Settings.GOAL.toString(), Settings.NOT_SET.toString()).equals(Settings.NOT_SET.toString()));
    }

    @Override
    public String getPassword() {
        return sharedPreferences.getString(Settings.PASSWORD.toString(), Settings.NOT_SET.toString());
    }

    @Override
    public String getGoal() {
        return sharedPreferences.getString(Settings.GOAL.toString(), Settings.NOT_SET.toString());
    }


    @Override
    public boolean isReminderSet() {
        return !(sharedPreferences.getLong(Settings.REMINDER_TIME.toString(), -1) == -1);
    }

    @Override
    public int getCurrentDay() {
        return sharedPreferences.getInt(Settings.CURRENT_DAY.toString(), 0);
    }

    @Override
    public void increaseCurrentDay() {
        sharedPreferences.edit().putInt(Settings.CURRENT_DAY.toString(), sharedPreferences.getInt(Settings.CURRENT_DAY.toString(), 0) + 1).apply();
    }

    @Override
    public void setReminderTime(long time) {
        sharedPreferences.edit().putLong(Settings.REMINDER_TIME.toString(), time).apply();
    }

    @Override
    public long getReminderTime() {
        return sharedPreferences.getLong(Settings.REMINDER_TIME.toString(), -1);
    }

}
