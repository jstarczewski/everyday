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


    private static final String INFO_READ = "INFO_READ";
    private static final String PASSWORD = "PASSWORD";
    private static final String FOCUS_DURATION = "FOCUS_DURATION";
    private static final String GOAL = "GOAL";
    private static final String NOT_SET = "NOT_SET";


    private Context context;
    private static SharedPreferencesSettings sharedPreferencesSettings;
    private SharedPreferences sharedPreferences;

    public static SharedPreferencesSettings getInstance(Context context) {
        if (sharedPreferencesSettings == null) {
            sharedPreferencesSettings = new SharedPreferencesSettings(context);
        }
        return sharedPreferencesSettings;
    }

    private SharedPreferencesSettings(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    @Override
    public void setInfoRead() {
        sharedPreferences.edit().putBoolean(INFO_READ, true).apply();
    }

    @Override
    public void setPassword(String password) {
        sharedPreferences.edit().putString(PASSWORD, password).apply();
    }

    @Override
    public void setFocusDurationTime(int time) {
        sharedPreferences.edit().putInt(FOCUS_DURATION, time).apply();
    }

    @Override
    public void setGoal(String goal) {
        sharedPreferences.edit().putString(GOAL, goal).apply();
    }

    @Override
    public boolean isInfoRead() {
        return (sharedPreferences.getBoolean(INFO_READ, false));
    }

    @Override
    public boolean isPasswordSet() {
        return (sharedPreferences.getString(PASSWORD, NOT_SET).equals(NOT_SET));
    }

    @Override
    public int getFocusDurationTime() {
        return sharedPreferences.getInt(FOCUS_DURATION, 3);
    }

    @Override
    public boolean isGoalSet() {
        return (sharedPreferences.getString(GOAL,   NOT_SET).equals(NOT_SET));
    }

    @Override
    public String getPassword() {
        return sharedPreferences.getString(PASSWORD, NOT_SET);
    }

}
