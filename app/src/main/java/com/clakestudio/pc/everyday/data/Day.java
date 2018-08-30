package com.clakestudio.pc.everyday.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Jan on 8/30/2018.
 */

@Entity (tableName = "days")
public final class Day {

    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "entryId") // dayId
    private String dayNumber;

    @NonNull
    @ColumnInfo (name = "date")
    private String date;


    @NonNull
    @ColumnInfo (name = "titel")
    private String titel;

    @NonNull
    @ColumnInfo (name = "note")
    private String note;



}
