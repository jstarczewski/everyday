package com.clakestudio.pc.everyday.forgotpassword;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 9/7/2018.
 */

public interface ForgotPasswordContract {

    interface View extends BaseView<Presenter> {

        void showStartSettingsActivity();

        void showToastAboutGoalIncorrectness();

    }

    interface Presenter extends BasePresenter {

        void checkGoalCorrectness(String goal);

    }

}
