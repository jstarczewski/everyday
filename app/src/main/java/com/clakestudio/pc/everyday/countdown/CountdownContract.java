package com.clakestudio.pc.everyday.countdown;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 9/10/2018.
 */

public interface CountdownContract {

    interface View extends BaseView<Presenter> {

        void startCountdownTimer(int min);

        void startAddDayActivity();

        void updateTextViewCountDown(int timeLeft);

        void fireMediaPlayer();

    }

    interface Presenter extends BasePresenter {

        int getFocusDuration();

        void loadAddDayActivity();

        void updateRemainingTime(int timeLeft);

    }


}
