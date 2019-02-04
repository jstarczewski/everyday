package com.clakestudio.pc.everyday.test;

import com.clakestudio.pc.everyday.adddays.AddDayContract;
import com.clakestudio.pc.everyday.adddays.AddDayPresenter;
import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.data.DayRepository;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class AddDayPresenterTest {

    @Mock
    private AddDayContract.View view;

    @Mock
    private AddDayPresenter presenter;

    @Mock
    private DayRepository dayRepository;

    @Mock
    private SettingsRepository settingsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new AddDayPresenter(dayRepository, settingsRepository, view);

    }

    @Test
    public void loadDayInfo() {

        presenter.loadDayInfo(0, "Title", "Note");
        String date = presenter.provideDateString();
        Mockito.verify(view).showCurrentDayInfo("Day " + 0 + " / " + date, "Title", "Note");
        // this will fail
        // Mockito.verify(view).showCurrentDayInfo("Day " + 0 + " / " + date, "Title", "Note wrong");

    }

    @Test
    public void saveDayNewDay() {

        presenter.setIsNewDay(true);
        Day day = new Day("0", presenter.provideDateString(), "Title", "Note");
        presenter.saveDay(day);
        Mockito.verify(dayRepository).addNewDay(day);
        Mockito.verify(settingsRepository).incrementCurrentDayCount();
        Mockito.verify(view).showStartShowDaysActivity();

    }

    @Test
    public void saveDayNotNewDay() {


        Day day = new Day("0", presenter.provideDateString(), "Title", "Note");
        presenter.saveDay(day);
        Mockito.verify(dayRepository).addNewDay(day);
        Mockito.verify(settingsRepository, Mockito.never()).incrementCurrentDayCount();
        Mockito.verify(view).showStartShowDaysActivity();

    }

    @Test
    public void setIsNewDay() {
        presenter.setIsNewDay(true);
        assertTrue(presenter.isNewDay());
    }
}