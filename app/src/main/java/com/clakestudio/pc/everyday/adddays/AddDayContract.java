package com.clakestudio.pc.everyday.adddays;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;
import com.clakestudio.pc.everyday.data.Day;

/**
 * Created by Jan on 9/1/2018.
 */

public class AddDayContract {

    public interface View extends BaseView<Presenter> {

        void showCurrentDayInfo(String dayInfo, String tittle, String note);

        void showNewDayInfo(String dayInfo);

        void showStartShowDaysActivity();

    }

    public interface Presenter extends BasePresenter {

        void loadDayInfo(int dayId, String title, String note);

        void saveDay(Day day);

        void setIsNewDay(boolean isNewDay);
    }

}
