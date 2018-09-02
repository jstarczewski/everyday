package com.clakestudio.pc.everyday.data;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Jan on 8/30/2018.
 */

public class DayRepository implements Runnable {

    private final DayDao dayDao;
    private List<Day> daysList;
    private Accessible accessible;
    private Boolean isUpdated = false;
    private static DayRepository INSTANCE = null;

  //  @Inject
    private DayRepository(DayDao dayDao) {
        this.dayDao = dayDao;
    }

    public static DayRepository getInstance(DayDao dayDao) {

        if (INSTANCE == null) {
            INSTANCE = new DayRepository(dayDao);
        }
        return INSTANCE;
    }

    private List<Day> getDays() {
        return dayDao.getDayList();
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

    public void setAccessible(Accessible accessible) {
        this.accessible = accessible;
    }

    public void getAccessToDays() {
        new Thread() {
            @Override
            public void run() {
                accessible.getAccessDays(getDays());
            }
        }.start();
    }


    @Override
    public void run() {

    }
}
