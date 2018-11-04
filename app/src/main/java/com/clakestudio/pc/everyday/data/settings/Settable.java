package com.clakestudio.pc.everyday.data.settings;

/**
 * Created by Jan on 9/6/2018.
 * Interface created for class that will handle
 * settings within app. Currently settings are
 * stored in SharedPreferences
 */

public interface Settable {

    void setInfoRead();

    void setPassword(String password);

    void setFocusDurationTime(int time);

    void setGoal(String goal);

    boolean isInfoRead();

    boolean isPasswordSet();

    int getFocusDurationTime();

    boolean isGoalSet();

    String getPassword();

    String getGoal();

    boolean isReminderSet();

    int getCurrentDay();

    void increaseCurrentDay();

    void setReminderTime(long time);

    long getReminderTime();
}
