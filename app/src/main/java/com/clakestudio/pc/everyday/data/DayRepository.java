package com.clakestudio.pc.everyday.data;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Jan on 8/30/2018.
 */

public class DayRepository {

    private final DayDao dayDao;

    @Inject
    public DayRepository(DayDao dayDao) {
        this.dayDao = dayDao;
    }

    public List<Day> getDayList() {
        return dayDao.getDayList();
    }

    public Day getDayById(String dayId) {
        return dayDao.getDayById(dayId);
    }

    public Day getDayByDate(String date) {
        return dayDao.getDayByDate(date);
    }

    public void addNewDay(Day day) {
        dayDao.insertDay(day);
    }

    public int deleteDayById(String dayId) {
        return dayDao.deleteByDayId(dayId);
    }

    public void updateDay(String title, String note) {
        dayDao.updateDay(title,note);
    }

}
