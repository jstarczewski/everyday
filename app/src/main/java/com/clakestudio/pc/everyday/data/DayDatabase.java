package com.clakestudio.pc.everyday.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Jan on 8/30/2018.
 */


@Database(entities = {Day.class}, version = 1)
public abstract class DayDatabase extends RoomDatabase {

    public abstract DayDao dayDao();


}
