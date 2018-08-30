package com.clakestudio.pc.everyday.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


/**
 * Created by Jan on 8/30/2018.
 */

@Dao
public interface DayDao {

    @Query("SELECT * FROM Days")
    List<Day> getDays();


    @Query("SELECT * FROM Days WHERE dayId= :dayId")
    Day getDayById();

    @Query("SELECT * FROM Days WHERE date= :date")
    Day getDayByDate();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDay(Day day);

    @Update
    int updateDay(Day day);

    @Query("UPDATE Days SET title = :title, note = :note WHERE dayId = :dayId")
    void updateDay(String title, String note);

}
