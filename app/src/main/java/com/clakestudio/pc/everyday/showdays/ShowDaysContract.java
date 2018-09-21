package com.clakestudio.pc.everyday.showdays;

import android.support.annotation.NonNull;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;
import com.clakestudio.pc.everyday.data.Day;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 8/30/2018.
 */

public interface ShowDaysContract {


    interface View extends BaseView<Presenter> {


        void showDays(List<Day> days);

        void showStartAddDayActivityToAddDay(int currentDay);

        void showStartAddDayActivityToEditDay(int dayId, String tittle, String note);

        void showStartSettingsActivity();

        void showDayAlreadyAddedToast();
    }

    interface Presenter extends BasePresenter {

        void checkIfDayAlreadyAdded(String date);

        void loadDays();

        void addNewDay();

        void editCurrentDay(Day day, int index, int size);

        void loadShowSettingsActivity();

        List<Day> getDays();

        void addDay(boolean isFirstDay, ArrayList<Day> days);

    }

}
