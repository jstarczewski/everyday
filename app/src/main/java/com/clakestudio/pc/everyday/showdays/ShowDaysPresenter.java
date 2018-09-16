package com.clakestudio.pc.everyday.showdays;

import android.support.annotation.NonNull;
import android.util.Log;

import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.data.DayRepository;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Jan on 8/30/2018.
 */

public class ShowDaysPresenter implements ShowDaysContract.Presenter {


    private final DayRepository dayRepository;
    private static final String pattern = "dd MM yyyy";
    private final ShowDaysContract.View view;
    private SettingsRepository settingsRepository;

    public ShowDaysPresenter(@NonNull DayRepository dayRepository, SettingsRepository settingsRepository, @NonNull ShowDaysContract.View view) {
        this.dayRepository = dayRepository;
        this.view = view;
        this.settingsRepository = settingsRepository;
        view.setPresenter(this);
    }


    @Override
    public void start() {
        loadDays();
    }

    @Override
    public void checkIfDayAlreadyAdded(String date) {
        String currentDate = (new SimpleDateFormat(pattern)).format(Calendar.getInstance().getTime());
        Log.e("dates", currentDate + " --- " + date);
        if (date != null && currentDate.equals(date)) {
            view.showDayAlreadyAddedToast();
        } else {
            addNewDay();
        }
    }

    @Override
    public void loadDays() {
        view.showDays();
    }


    @Override
    public void addNewDay() {
        //dayRepository.addNewDay(new Day("1", "30.08.2018", "Whats is you why", "This time mate"));
        view.showStartAddDayActivityToAddDay(settingsRepository.getCurrentDay());
    }

    @Override
    public void editCurrentDay(Day day, int index, int size) {
        if (index == size)
            view.showStartAddDayActivityToEditDay(Integer.valueOf(day.getDayId()), day.getTitle(), day.getNote());
        else {
            view.showDayAlreadyAddedToast();
        }
    }

    @Override
    public void loadShowSettingsActivity() {
        view.showStartSettingsActivity();
    }

    @Override
    public List<Day> getDays() {
        return dayRepository.getDays();
    }

    @Override
    public void addDay(boolean isFirstDay, String date) {
        if (isFirstDay)
            addNewDay();
        else {
            checkIfDayAlreadyAdded(date);
        }
    }

}



