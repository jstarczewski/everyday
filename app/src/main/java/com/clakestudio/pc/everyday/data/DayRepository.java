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
    private Boolean isUpdated = false;

    @Inject
    public DayRepository(DayDao dayDao) {
        this.dayDao = dayDao;
    }


    public List<Day> getDays() {
        return dayDao.getDayList();


     /*   (new Thread() {

            @Override
            public void run() {
                daysList = dayDao.getDayList();
                isUpdated = true;
            }
        }).start();*/
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
