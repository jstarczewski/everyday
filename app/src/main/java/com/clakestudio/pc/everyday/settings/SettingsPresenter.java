package com.clakestudio.pc.everyday.settings;


import android.util.Log;

import com.clakestudio.pc.everyday.data.settings.Settings;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;


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
        checkIfPasswordIsSet();
        checkIfReminderIsSet();
        determineCheckBoxVisibility();
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
        else {
            view.showChangePasswordDialog();
        }
    }

    @Override
    public void saveIsReminderSet(boolean isSet) {
        if (!isSet)
            settingsRepository.setReminder(false);
        else {
            view.showSetReminderTimeDialog();
        }
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
    public void saveNewFocusDurationTime(int time) {
        settingsRepository.setFocusDurationTime(time);
        view.showChangeFocusDurationTime(time);
    }

    @Override
    public void saveFocusReminderTime() {

    }

    /**
     * Bugs -> when switch is off first turned on we set password
     * <p>
     * Then when turning on acitivty we check if password is set to show change password panel
     * <p>
     * when changing switch we insert not set password
     */

    @Override
    public void checkIfPasswordIsSet() {
        view.showSetPasswordSwitchOnOrOff(settingsRepository.isPasswordSet());
    }

    @Override
    public void checkIfReminderIsSet() {
        view.showSetReminderSwitchOnOrOff(settingsRepository.isReminderSet());
    }

    @Override
    public void setPasswordToNotSet() {

    }

    @Override
    public int getFocusDurationTime() {
        return settingsRepository.getFocusDurationTime();
    }

    @Override
    public void determineCheckBoxVisibility() {
        view.showChangeFocusDurationTime(getFocusDurationTime());
    }

    @Override
    public void save(String saveWhat, String value) {
        if (saveWhat.equals("Change password"))
            saveNewPassword(value);
        else {
            saveNewGoal(value);
        }
        view.showDismissDialog();
    }

}
