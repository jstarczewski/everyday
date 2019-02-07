package com.clakestudio.pc.everyday.adddays;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.data.DayRepository;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jan on 9/1/2018.
 */

public class AddDayPresenter implements AddDayContract.Presenter {

    private DayRepository dayRepository;
    private AddDayContract.View view;
    private SettingsRepository settingsRepository;
    private boolean isNewDay = false;
    private SimpleDateFormat simpleDateFormat;
    private Calendar calendar;

    public AddDayPresenter(@NonNull DayRepository dayRepository, SettingsRepository settingsRepository, @NonNull AddDayContract.View view, SimpleDateFormat simpleDateFormat, Calendar calendar) {
        this.dayRepository = dayRepository;
        this.view = view;
        this.settingsRepository = settingsRepository;
        this.simpleDateFormat = simpleDateFormat;
        this.calendar = calendar;
        view.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void stop() {
        view.stop();
    }

    @Override
    public void loadDayInfo(int dayId, String title, String note) {
        String date = simpleDateFormat.format(calendar.getTime());
        //  if (title.equals("") && note.equals("")) {
        //     view.showNewDayInfo("Day " + dayId + " / " + date);
        // } else {
        /**
         * Translation -> Day = Dzień
         * */
        view.showCurrentDayInfo("Day " + dayId + " / " + date, title, note);
        //
        //  }
    }

    @Override
    public void saveDay(Day day) {
        dayRepository.addNewDay(day);
        if (isNewDay)
            settingsRepository.incrementCurrentDayCount();
        view.showStartShowDaysActivity();
    }

    @Override
    public void setIsNewDay(boolean newDay) {
        isNewDay = newDay;
    }

    public boolean isNewDay() {
        return isNewDay;
    }

    @VisibleForTesting
    public String provideDateString() {
        return simpleDateFormat.format(calendar.getTime());
    }
}
