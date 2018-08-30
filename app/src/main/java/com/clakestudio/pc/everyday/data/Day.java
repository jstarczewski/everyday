package com.clakestudio.pc.everyday.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Jan on 8/30/2018.
 */

@Entity(tableName = "Days")
public final class Day {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "dayId") // dayId
    private String dayId;

    @NonNull
    @ColumnInfo(name = "date")
    private String date;


    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "note")
    private String note;

    public Day(@NonNull String dayId, @NonNull String date, @NonNull String title, @NonNull String note) {
        this.dayId = dayId;
        this.date = date;
        this.title = title;
    }

    @NonNull
    public String getDayNumber() {
        return dayId;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getNote() {
        return note;
    }
}
