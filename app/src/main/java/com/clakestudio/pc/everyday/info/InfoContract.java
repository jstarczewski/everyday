package com.clakestudio.pc.everyday.info;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 9/3/2018.
 */

public class InfoContract {

    interface View extends BaseView<Presenter> {

        void showSetGoalActivity();

    }

    interface Presenter extends BasePresenter {


    }
}
