package com.clakestudio.pc.everyday.data.settings;

/**
 * Created by Jan on 9/6/2018.
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

    void setReminder(boolean isSet);

    boolean isReminderSet();
}
