package com.clakestudio.pc.everyday.forgotpassword;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 9/7/2018.
 */

public interface ForgotPasswordContract {

    interface View extends BaseView<Presenter> {

        void startSettingsActivity();

        void showToastAboutGoalIncorrectness();

        void checkGoal();
    }

    interface Presenter extends BasePresenter {

        void checkGoalCorrectness(String goal);

    }

}
