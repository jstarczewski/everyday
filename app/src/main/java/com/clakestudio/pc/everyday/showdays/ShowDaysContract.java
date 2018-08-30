package com.clakestudio.pc.everyday.showdays;

import android.support.annotation.NonNull;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 8/30/2018.
 */

public class ShowDaysContract {


    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadDays();

        void addNewDay();

        // start with database

 //       void openDayDetails(@NonNull Day requestedDay);



    }

}
