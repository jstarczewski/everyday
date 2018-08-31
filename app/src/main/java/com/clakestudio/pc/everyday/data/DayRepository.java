package com.clakestudio.pc.everyday.data;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Jan on 8/30/2018.
 */

public class DayRepository {

    private final DayDao dayDao;
    private List<Day> daysList;

    @Inject
    public DayRepository(DayDao dayDao) {
        this.dayDao = dayDao;
    }

    public List<Day> getDayList() {
        (new Thread() {

            @Override
            public void run() {

                Log.e("Elo", "No i nie chuj");
                daysList = dayDao.getDayList();

            }


        }).start();
        if (daysList == null || daysList.isEmpty())
            Log.e("Elo", "No i chuj");
        return daysList;
    }


    public Day getDayById(String dayId) {
        return dayDao.getDayById(dayId);
    }

    public Day getDayByDate(String date) {
        return dayDao.getDayByDate(date);
    }

    public void addNewDay(final Day day) {
        (new Thread() {
            @Override
            public void run() {
                Log.e("Inserting", "inserting");
                dayDao.insertDay(day);
            }
        }).start();
    }

    public int deleteDayById(String dayId) {
        return dayDao.deleteByDayId(dayId);
    }

    public void updateDay(String title, String note, String dayId) {
        dayDao.updateDay(title, note, dayId);
    }

}
