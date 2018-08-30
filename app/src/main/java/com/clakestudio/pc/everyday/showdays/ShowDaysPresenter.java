package com.clakestudio.pc.everyday.showdays;

import android.support.annotation.NonNull;

import com.clakestudio.pc.everyday.data.DayRepository;

/**
 * Created by Jan on 8/30/2018.
 */

public class ShowDaysPresenter implements ShowDaysContract.Presenter {


    private final DayRepository dayRepository;

    private final ShowDaysContract.View daysView;

    public ShowDaysPresenter(@NonNull DayRepository dayRepository, @NonNull ShowDaysContract.View daysView) {
        this.dayRepository = dayRepository;
        this.daysView = daysView;
        daysView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadDays() {

    }

    @Override
    public void addNewDay() {

    }
}
