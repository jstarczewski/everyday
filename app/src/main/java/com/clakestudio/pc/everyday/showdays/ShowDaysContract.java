package com.clakestudio.pc.everyday.showdays;

import android.support.annotation.NonNull;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;
import com.clakestudio.pc.everyday.data.Day;

import java.util.ArrayList;

/**
 * Created by Jan on 8/30/2018.
 */

public interface ShowDaysContract {


    interface View extends BaseView<Presenter> {


        void showDays(ArrayList<Day> days);

        void showAddNewDay();


    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadDays();

        void addNewDay();


        // start with database

 //       void openDayDetails(@NonNull Day requestedDay);


    }

}
