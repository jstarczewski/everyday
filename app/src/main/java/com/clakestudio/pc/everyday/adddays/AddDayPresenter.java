package com.clakestudio.pc.everyday.adddays;

import android.support.annotation.NonNull;
import android.util.Log;

import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.data.DayRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Jan on 9/1/2018.
 */

public class AddDayPresenter implements AddDayContract.Presenter {

    private static final String pattern = "dd MM yyyy";

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
    public void loadCurrentDayInfo(int day) {
        String date = (new SimpleDateFormat(pattern)).format(Calendar.getInstance().getTime());
        view.showCurrentDayInfo("Day " + day + " / " + date);
    }

    @Override
    public void saveDay(String[] dayInfoArray) {
        Random random = new Random();
        dayRepository.addNewDay(new Day(dayInfoArray[0], dayInfoArray[1], dayInfoArray[2], dayInfoArray[3]));
        view.showDays();
    }
}
