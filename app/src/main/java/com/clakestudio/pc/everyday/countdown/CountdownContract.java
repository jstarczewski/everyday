package com.clakestudio.pc.everyday.countdown;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 9/10/2018.
 */

public interface CountdownContract {

    interface View extends BaseView<Presenter> {

        void startCountdownTimer(int min);

        void showStartAddDayActivity();

        void showUpdateTextViewCountDown(int timeLeft);

        void showFireMediaPlayer();

        void showStopCountdownTimer();


    }

    interface Presenter extends BasePresenter {

        int getFocusDuration();

        void startAddDayActivity();

        void updateRemainingTime(int timeLeft);

        void stop();

        void skipCountdown();

        void fireMediaPlayer();

        void stopCountdown();

    }


}
