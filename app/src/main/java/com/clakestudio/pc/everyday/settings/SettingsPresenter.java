package com.clakestudio.pc.everyday.settings;


import com.clakestudio.pc.everyday.data.settings.Settings;
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
          checkIfPasswordIsSet();
          checkIfReminderIsSet();
    }


    @Override
    public int getCurrentHour() {
        return 0;
    }

    @Override
    public int getCurrentMinute() {
        return 0;
    }

    @Override
    public void saveIsPasswordSet(boolean isSet) {
        if (!isSet)
            settingsRepository.setPassword(Settings.NOT_SET.toString());
    }

    @Override
    public void saveIsReminderSet(boolean isSet) {
        if (!isSet)
            settingsRepository.setReminder(false);
    }

    @Override
    public void saveNewPassword(String password) {
        settingsRepository.setPassword(password);
    }

    @Override
    public void saveNewGoal(String goal) {
        settingsRepository.setGoal(goal);
    }

    @Override
    public void saveNewFocusDurationTime() {

    }

    @Override
    public void saveFocusReminderTime() {

    }

    /**
     *
     *
     * Bugs -> when switch is off first turned on we set password
     *
     * Then when turning on acitivty we check if password is set to show change password panel
     *
     * when changing switch we insert not set password
     *
     *
     * */

    @Override
    public void checkIfPasswordIsSet() {
        if (!settingsRepository.isPasswordSet())
            view.showPasswordChangeOption();
    }

    @Override
    public void checkIfReminderIsSet() {
        if (settingsRepository.isReminderSet())
            view.showReminderTimeChangeOption();
    }
}
