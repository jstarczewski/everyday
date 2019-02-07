package com.clakestudio.pc.everyday.info;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 9/3/2018.
 */

public class InfoContract {

    public interface View extends BaseView<Presenter> {

        void showStartSetGoalActivity();


    }

    interface Presenter extends BasePresenter {

        void setAppInfoUnderstood();

    }
}
