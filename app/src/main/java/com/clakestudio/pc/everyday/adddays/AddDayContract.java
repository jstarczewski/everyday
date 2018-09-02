package com.clakestudio.pc.everyday.adddays;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 9/1/2018.
 */

public class AddDayContract {

    interface View extends BaseView<Presenter> {

        void showCurrentDayInfo(String dayInfo);

        void showDays();

    }

    interface Presenter extends BasePresenter {

        void loadCurrentDayInfo(int day);

        void saveDay(String[] dayInfoArray);

    }

}
