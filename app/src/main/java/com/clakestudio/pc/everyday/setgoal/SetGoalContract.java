package com.clakestudio.pc.everyday.setgoal;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 9/6/2018.
 */

public interface SetGoalContract {

    interface View extends BaseView<Presenter> {

        void showSettingsActivity();

        void determineGoalTextViewVisibility();

        void showGoalForThreeSeconds();

    }

    interface Presenter extends BasePresenter {

        void setPassword(String string);


    }


}
