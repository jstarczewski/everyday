package com.clakestudio.pc.everyday.data;

import android.os.AsyncTask;

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

    /**
     * Synchronization with the main thread if you post back results to the user interface -> we are not updating the UI
     * No default for canceling the thread -> now a long task, does not take much time gonna test what is going to happen after lots of data
     * No default thread pooling -> I should create one
     * No default for handling configuration changes in Android -> that is a problem but in this activity we cannot change the orientation
     */


    public void setAccessible(Accessible accessible) {
        this.accessible = accessible;
    }

    public void getAccessToDays() {

        GetAccessDays getAccessDays = new GetAccessDays(dayDao, accessible);
        getAccessDays.execute();
/*
        new Thread() {
            @Override
            public void run() {
                accessible.getAccessDays(getDays());
            }
        }.start();*/



    }


    @Override
    public void run() {

    }
}

/**
 *
 * Do not know wheter this approach with acynctask is good -> gonna do more research in case of memory leaks etc
 *
 *
 * **/



class GetAccessDays extends AsyncTask<List<Day>, Void, List<Day>> {

    private Accessible accessible;
    private DayDao dayDao;

    GetAccessDays(DayDao dayDao, Accessible accessible) {
        this.dayDao = dayDao;
        this.accessible = accessible;
    }

    @Override
    protected List<Day> doInBackground(List<Day>... days) {
       return dayDao.getDayList();
    }

    @Override
    protected void onPostExecute(List<Day> days) {
        accessible.getAccessDays(days);
    }
}
