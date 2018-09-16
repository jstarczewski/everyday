package com.clakestudio.pc.everyday.data;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Jan on 8/30/2018.
 */

public class DayRepository {

    private final DayDao dayDao;
    private static DayRepository INSTANCE = null;

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

    public void getDays(AsyncAccessor asyncAccessor) {
        AsyncGetDays asyncGetDays = new AsyncGetDays(dayDao, asyncAccessor);
        asyncGetDays.execute();
    }

    public Day getDayById(String dayId) {
        return dayDao.getDayById(dayId);
    }

    public Day getDayByDate(String date) {
        return dayDao.getDayByDate(date);
    }

    public void addNewDay(final Day day) {
        AsyncAddNewDay asyncAddNewDay = new AsyncAddNewDay(dayDao);
        asyncAddNewDay.execute(day);
    }

    public int deleteDayById(String dayId) {
        return dayDao.deleteByDayId(dayId);
    }

    public void updateDay(String title, String note, String dayId) {
        dayDao.updateDay(title, note, dayId);
    }

}

/**
 * Do not know whether this approach with acynctask is good -> gonna do more research in case of memory leaks etc
 * - > created weak references
 * - > basically this asyncTasks runs only in ShowDaysActivity and dayInfo are passed to other activities by Intent
 * -> the passing data by Intent approach is actually not the best solution because we are simply decomposing objects, but in this particular
 * -> use case (aka application) we do that once or twice so it is not that annoying
 **/


class AsyncGetDays extends AsyncTask<Void, Void, List<Day>> {

    private WeakReference<AsyncAccessor> asyncAccessorWeakReference;
    private WeakReference<DayDao> dayDaoWeakReference;

    AsyncGetDays(DayDao dayDao, AsyncAccessor asyncAccessor) {
        asyncAccessorWeakReference = new WeakReference<>(asyncAccessor);
        dayDaoWeakReference = new WeakReference<>(dayDao);
    }

    @Override
    protected List<Day> doInBackground(Void... voids) {
        return this.dayDaoWeakReference.get().getDayList();
    }

    @Override
    protected void onPostExecute(List<Day> days) {
        asyncAccessorWeakReference.get().getDays(days);
    }
}

/**
 * Weak reference, not a long running task
 *
 * */


class AsyncAddNewDay extends AsyncTask<Day, Void, Void> {

    private WeakReference<DayDao> dayDaoWeakReference;

    public AsyncAddNewDay(DayDao dayDao) {
        dayDaoWeakReference = new WeakReference<>(dayDao);
    }

    @Override
    protected Void doInBackground(Day... days) {
        dayDaoWeakReference.get().insertDay(days[0]);
        return null;
    }
}
