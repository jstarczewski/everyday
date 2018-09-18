package com.clakestudio.pc.everyday.showdays;

import android.util.Log;

import com.clakestudio.pc.everyday.data.AsyncAccessor;
import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.data.DayRepository;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Jan on 8/30/2018.
 */

public class ShowDaysPresenter implements ShowDaysContract.Presenter, AsyncAccessor {


    private final DayRepository dayRepository;
    private static final String pattern = "dd MM yyyy";
    private final ShowDaysContract.View view;
    private SettingsRepository settingsRepository;

    public ShowDaysPresenter(DayRepository dayRepository, SettingsRepository settingsRepository, ShowDaysContract.View view) {
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
    public void stop() {
        view.stop();
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

        /**
         * Public interface AsyncAccessor easily lets us to get access to list from database and
         * does not include android packages into presenter
         *
         * */
        dayRepository.getDays(this);
    }


    @Override
    public void addNewDay() {
        //dayRepository.addNewDay(new Day("1", "30.08.2018", "Whats is you why", "This time mate"));
        view.showStartAddDayActivityToAddDay(settingsRepository.getCurrentDay() + 1);
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
//          return dayRepository.getDays();
        return null;
    }

    @Override
    public void addDay(boolean isFirstDay, ArrayList<Day> days) {
        if (isFirstDay)
            addNewDay();
        else {
            if (days != null)
                checkIfDayAlreadyAdded(days.get(days.size() - 1).getDate());
        }
    }

    @Override
    public void getDays(List<Day> dayList) {
        view.showDays(dayList);
    }
}



