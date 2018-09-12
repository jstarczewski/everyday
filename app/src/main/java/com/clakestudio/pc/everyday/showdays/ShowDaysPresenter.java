package com.clakestudio.pc.everyday.showdays;

import android.support.annotation.NonNull;

import com.clakestudio.pc.everyday.data.Accessible;
import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.data.DayRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 8/30/2018.
 */

public class ShowDaysPresenter implements ShowDaysContract.Presenter {


    private final DayRepository dayRepository;

    private List<Day> days;
    private final ShowDaysContract.View daysView;

    public ShowDaysPresenter(@NonNull DayRepository dayRepository, @NonNull ShowDaysContract.View daysView) {
        this.dayRepository = dayRepository;
        this.daysView = daysView;
        daysView.setPresenter(this);
    }


    @Override
    public void start() {
        loadDays();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadDays() {
        daysView.showDays();
    }


    @Override
    public void addNewDay() {

        //dayRepository.addNewDay(new Day("1", "30.08.2018", "Whats is you why", "This time mate"));
        daysView.showAddNewDay();
    }

    @Override
    public void editCurrentDay(Day day) {
        daysView.showEditCurrentDay(Integer.valueOf(day.getDayId()));
    }

    @Override
    public void loadShowSettingsActivity() {
        daysView.showSettingsActivity();
    }

    @Override
    public List<Day> getDays() {
        return dayRepository.getDays();
    }

}



