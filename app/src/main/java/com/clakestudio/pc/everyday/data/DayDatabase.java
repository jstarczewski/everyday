package com.clakestudio.pc.everyday.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Jan on 8/30/2018.
 */


@Database(entities = {Day.class}, version = 1)
public abstract class DayDatabase extends RoomDatabase {

    public abstract DayDao dayDao();

    private static DayDatabase INSTANCE;

    private static final Object slcok = new Object();

    public static DayDatabase getInstance(Context context) {
        synchronized (slcok) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DayDatabase.class, "Days.db").build();
            }
            return INSTANCE;
        }
    }
}
