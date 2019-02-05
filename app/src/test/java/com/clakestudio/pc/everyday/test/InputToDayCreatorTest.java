package com.clakestudio.pc.everyday.test;

import com.clakestudio.pc.everyday.adddays.InputToDayCreator;
import com.clakestudio.pc.everyday.data.Day;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputToDayCreatorTest {

    private String rightDoubleDayData;
    private String rightDayData;
    private String rightTripleDayData;
    private String rightOneNumberMonth;
    private String title;
    private String note;

    @Before
    public void setUp() {
        rightDayData = "Day 2 / 26 11 2018";
        rightDoubleDayData = "Day 12 / 26 11 2018";
        rightTripleDayData =  "Day 123 / 26 11 2018";
        rightOneNumberMonth = "Day 123 / 26 1 2018";
        title = "Title";
        note = "Note";
    }


    @Test
    public void createOneNumberDayData() {
        Day day =  InputToDayCreator.create(rightDayData, title, note);
        assertEquals(day.getDayId(), "2");
        assertEquals(day.getDate(), "26 11 2018");
        assertEquals(day.getTitle(), "Title");
        assertEquals(day.getNote(), "Note");
    }

    @Test
    public void createTwoNumberDayData() {
        Day day =  InputToDayCreator.create(rightDoubleDayData, title, note);
        assertEquals(day.getDayId(), "12");
        assertEquals(day.getDate(), "26 11 2018");
        assertEquals(day.getTitle(), "Title");
        assertEquals(day.getNote(), "Note");
    }


    @Test
    public void createTripleNumberDayData() {
        Day day =  InputToDayCreator.create(rightTripleDayData, title, note);
        assertEquals(day.getDayId(), "123");
        assertEquals(day.getDate(), "26 11 2018");
        assertEquals(day.getTitle(), "Title");
        assertEquals(day.getNote(), "Note");
    }

    @Test
    public void createOneNumberMonth() {
        Day day =  InputToDayCreator.create(rightOneNumberMonth, title, note);
        assertEquals(day.getDayId(), "123");
        assertEquals(day.getDate(), "26 1 2018");
        assertEquals(day.getTitle(), "Title");
        assertEquals(day.getNote(), "Note");
    }

}