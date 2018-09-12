package com.clakestudio.pc.everyday.adddays;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;
import com.clakestudio.pc.everyday.data.Day;

/**
 * Created by Jan on 9/1/2018.
 */

public class AddDayContract {

    interface View extends BaseView<Presenter> {

        void showCurrentDayInfo(String dayInfo, String tittle, String note);

        void showNewDayInfo(String dayInfo);

        void showDays();

    }

    interface Presenter extends BasePresenter {

        void loadCurrentDayInfo(int dayId);

        void loadCurrentDayInfo(int dayId, String title, String note);

        void saveDay(String[] dayInfoArray);

    }

}
