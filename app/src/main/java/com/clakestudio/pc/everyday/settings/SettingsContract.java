package com.clakestudio.pc.everyday.settings;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

/**
 * Created by Jan on 9/6/2018.
 */

public interface SettingsContract {

    interface View extends BaseView<Presenter> {

        void showSetReminderTimeDialog();

        void showStartChangePasswordDialog();

        void showStartChangeGoalDialog();

        void showChangeFocusDurationTime(int id);

        // method helps to eliminate some of duplications, does not communicate with presenter
        void setDialogInfo(String toolbarTitle, String editTextHint);

        void showDismissDialog();

        void showSetPasswordSwitchOnOrOff(boolean value);

        void showSetReminderSwitchOnOrOff(boolean value);

        void showShowDaysActivity();

    }

    interface Presenter extends BasePresenter {

        void saveIsPasswordSet(boolean isSet);

        void saveIsReminderSet(boolean isSet);

        void saveNewPassword(String password);

        void saveNewGoal(String goal);

        void saveFocusDurationTime(boolean isChecked, int time);

        void saveFocusReminderTime();

        void checkIfPasswordIsSet();

        void checkIfReminderIsSet();

        void setPasswordToNotSet();

        int getFocusDurationTime();

        void determineCheckBoxVisibility();

        void save(String saveWhat, String value, String value2, String pattern);

        void startShowDaysActivity();

        void startChangeGoalDialog();

        void startChangePasswordDialog();

        void refresh();

    }

}
