package com.clakestudio.pc.everyday.adddays;

import android.support.annotation.NonNull;

import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.data.DayRepository;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jan on 9/1/2018.
 */

public class AddDayPresenter implements AddDayContract.Presenter {

    private static final String pattern = "dd MM yyyy";

    private DayRepository dayRepository;
    private AddDayContract.View view;
    private SettingsRepository settingsRepository;
    private boolean isNewDay = false;

    public AddDayPresenter(@NonNull DayRepository dayRepository, SettingsRepository settingsRepository, @NonNull AddDayContract.View view) {
        this.dayRepository = dayRepository;
        this.view = view;
        this.settingsRepository = settingsRepository;
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
        String date = (new SimpleDateFormat(pattern)).format(Calendar.getInstance().getTime());
        //  if (title.equals("") && note.equals("")) {
        //     view.showNewDayInfo("Day " + dayId + " / " + date);
        // } else {
        view.showCurrentDayInfo("Day " + dayId + " / " + date, title, note);
        //
        //  }
    }

    @Override
    public void saveDay(String[] dayInfoArray) {
        dayRepository.addNewDay(new Day(dayInfoArray[0], dayInfoArray[1], dayInfoArray[2], dayInfoArray[3]));
        if (isNewDay)
            settingsRepository.incrementCurrentDayCount();
        view.showStartShowDaysActivity();
    }
    @Override
    public void setIsNewDay(boolean newDay) {
        isNewDay = newDay;
    }
}
