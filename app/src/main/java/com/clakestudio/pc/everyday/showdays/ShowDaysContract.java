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


        void showDays();

        void showAddNewDay(int currentDay);

        void showEditCurrentDay(int dayId, String tittle, String note);

        void showSettingsActivity();

        void showDayAlreadyAddedToast();
    }

    interface Presenter extends BasePresenter {

        void checkIfDayAlreadyAdded();

        void result(int requestCode, int resultCode);

        void loadDays();

        void addNewDay();

        void editCurrentDay(Day day);
        // start with database

        //       void openDayDetails(@NonNull Day requestedDay);
        void loadShowSettingsActivity();

        List<Day> getDays();
    }

}
