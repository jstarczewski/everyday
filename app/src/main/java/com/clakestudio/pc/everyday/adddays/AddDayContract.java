package com.clakestudio.pc.everyday.adddays;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 9/1/2018.
 */

public class AddDayContract {

    interface View extends BaseView<Presenter> {

        void showCurrentDayInfo();

        void showDays();

    }

    interface Presenter extends BasePresenter {

        void loadCurrentDayInfo();

        void saveDay(String dayId, String date, String title, String note);

    }

}
