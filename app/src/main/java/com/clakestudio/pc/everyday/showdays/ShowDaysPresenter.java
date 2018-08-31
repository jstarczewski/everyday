package com.clakestudio.pc.everyday.showdays;

import android.support.annotation.NonNull;

import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.data.DayRepository;
import com.clakestudio.pc.everyday.data.Daysable;

import java.lang.reflect.Executable;
import java.util.List;

/**
 * Created by Jan on 8/30/2018.
 */

public class ShowDaysPresenter implements ShowDaysContract.Presenter, Daysa {


    private final DayRepository dayRepository;

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

        dayRepository.getDays(new Daysable() {
            @Override
            public void onDaysLoader(List<Day> days) {

            }
        });

        /*
        (new Thread() {

            @Override
            public void run() {
                daysView.showDays((ArrayList<Day>) dayRepository.getDays());
            }
        }).start();*/

    }


    @Override
    public void addNewDay() {
        dayRepository.addNewDay(new Day("1", "30.08.2018", "Whats is you why", "This time mate"));
    }


}


