package com.clakestudio.pc.everyday.settings;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 9/6/2018.
 */

public interface SettingsContract {

    interface View extends BaseView<Presenter> {

        void showTimePicker();

        void showSwitchPasswordOnOff();

        void showFocusReminderOnOff();

        void showPasswordChangeOption();

        void showGoalChangeOption();

        void showReminderTimeChangeOption();

        void showChangePasswordDialog();

        void showChangeGoalDialog();

        void showChangeFocusDurationTime();

        void setDialogInfo(String toolbarTitle, String editTextHint);
    }

    interface Presenter extends BasePresenter {

        int getCurrentHour();

        int getCurrentMinute();

        void saveIsPasswordSet(boolean isSet);

        void saveIsReminderSet(boolean isSet);

        void saveNewPassword(String password);

        void saveNewGoal(String goal);

        void saveNewFocusDurationTime();

        void saveFocusReminderTime();

        void checkIfPasswordIsSet();

        void checkIfReminderIsSet();
    }

}
