package com.clakestudio.pc.everyday.settings;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 9/6/2018.
 */

public interface SettingsContract {

    interface View extends BaseView<Presenter> {

        void showTimePicker();

    }

    interface Presenter extends BasePresenter {



    }

}
