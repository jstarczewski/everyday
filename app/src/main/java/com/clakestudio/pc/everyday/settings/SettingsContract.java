package com.clakestudio.pc.everyday.settings;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 9/6/2018.
 */

public interface SettingsContract {

    interface View extends BaseView<Presenter> {

        void showSetReminderTimeDialog();

        void showChangePasswordDialog();

        void showChangeGoalDialog();

        void showChangeFocusDurationTime(int id);

        void setDialogInfo(String toolbarTitle, String editTextHint);

        void showDismissDialog();

        void showSetPasswordSwitchOnOrOff(boolean value);

        void showSetReminderSwitchOnOrOff(boolean value);

    }

    interface Presenter extends BasePresenter {

        int getCurrentHour();

        int getCurrentMinute();

        void saveIsPasswordSet(boolean isSet);

        void saveIsReminderSet(boolean isSet);

        void saveNewPassword(String password);

        void saveNewGoal(String goal);

        void saveNewFocusDurationTime(int time);

        void saveFocusReminderTime();

        void checkIfPasswordIsSet();

        void checkIfReminderIsSet();

        void setPasswordToNotSet();

        int getFocusDurationTime();

        void determineCheckBoxVisibility();

        void save(String saveWhat, String value);


    }

}
