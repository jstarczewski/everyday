package com.clakestudio.pc.everyday.adddays;

import android.support.annotation.NonNull;

import com.clakestudio.pc.everyday.data.DayRepository;

/**
 * Created by Jan on 9/1/2018.
 */

public class AddDayPresenter implements AddDayContract.Presenter {

    private DayRepository dayRepository;
    private AddDayContract.View view;

    public AddDayPresenter(@NonNull DayRepository dayRepository, @NonNull AddDayContract.View view) {
        this.dayRepository = dayRepository;
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void loadCurrentDayInfo() {

    }

    @Override
    public void saveDay(String dayId, String date, String title, String note) {

    }
}
