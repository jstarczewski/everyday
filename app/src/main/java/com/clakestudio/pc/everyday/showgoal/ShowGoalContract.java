package com.clakestudio.pc.everyday.showgoal;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 9/12/2018.
 */

public interface ShowGoalContract {

    interface View extends BaseView<Presenter> {

        void showShowDaysActivity();

        void startCountDownTimer();

        void showGoal();
    }

    interface Presenter extends BasePresenter {

        String getGoal();

        void loadCountdown();

    }

}
