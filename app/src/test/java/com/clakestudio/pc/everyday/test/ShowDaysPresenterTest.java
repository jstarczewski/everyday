package com.clakestudio.pc.everyday.test;

import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.data.DayRepository;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.showdays.ShowDaysContract;
import com.clakestudio.pc.everyday.showdays.ShowDaysPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;

public class ShowDaysPresenterTest {


    @Mock
    private SettingsRepository settingsRepository;

    @Mock
    private DayRepository dayRepository;

    @Mock
    private ShowDaysContract.View view;

    @Mock
    private ShowDaysPresenter presenter;

    private String dateToday = "16 02 2019";
    private String dateNotToday = "21 05 2014";
    private int currentDay = 0;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        String pattern = "dd MM yyyy";
        presenter = new ShowDaysPresenter(dayRepository, settingsRepository, view, new SimpleDateFormat(pattern), Calendar.getInstance());
        Mockito.when(settingsRepository.getCurrentDay()).thenReturn(currentDay);
    }

    @Test
    public void checkIfDayAlreadyAddedTrue() {
       presenter.checkIfDayAlreadyAdded(dateToday);
       Mockito.verify(view).showDayAlreadyAddedToast();
    }

    @Test
    public void checkIfDayAlreadyAddedFalse() {
        presenter.checkIfDayAlreadyAdded(dateNotToday);
        Mockito.verify(view, Mockito.never()).showDayAlreadyAddedToast();
    }

    @Test
    public void loadDays() {
        presenter.loadDays();
        Mockito.verify(dayRepository).getDays(presenter);
    }

    @Test
    public void addNewDay() {
        presenter.addNewDay();
        Mockito.verify(view).showStartAddDayActivityToAddDay(currentDay);
    }

    @Test
    public void editCurrentDayEqualSizeIsToday() {
        presenter.editCurrentDay(new Day("0", dateToday, "Title", "Note"), 0 , 0 );
        Mockito.verify(view).showStartAddDayActivityToEditDay(0, "Title", "Note");
    }

    @Test
    public void editCurrentDayEqualSizeIsNotToday() {
        presenter.editCurrentDay(new Day("0", dateNotToday, "Title", "Note"), 0, 0);
        Mockito.verify(view).showDayAlreadyAddedToast();
    }


    @Test
    public void editCurrentDayNotEqualSizeIsNotToday() {
        presenter.editCurrentDay(new Day("0", dateNotToday, "Title", "Note"), 2, 0);
        Mockito.verify(view).showDayAlreadyAddedToast();
    }

    @Test
    public void editCurrentDayNotEqualSizeIsToday() {
        presenter.editCurrentDay(new Day("0", dateToday, "Title", "Note"), 2 , 0 );
        Mockito.verify(view).showDayAlreadyAddedToast();
    }

    @Test
    public void loadShowSettingsActivity() {
        presenter.loadShowSettingsActivity();
        Mockito.verify(view).showStartSettingsActivity();
    }

    @Test
    public void getDays() {
       assertNull(presenter.getDays());
    }

    @Test
    public void addDayIsFirstDayAndDaysNotNull() {
        presenter.addDay(true, new ArrayList<Day>());
        Mockito.verify(view).showStartAddDayActivityToAddDay(currentDay);
    }

    @Test
    public void addDayIsFirstDayAndDaysNull() {
        presenter.addDay(true, null);
        Mockito.verify(view).showStartAddDayActivityToAddDay(currentDay);
    }

    @Test
    public void addDayIsNotFirstDayAndDaysNull() {
        presenter.addDay(false, null);
        Mockito.verify(view, Mockito.never()).showStartAddDayActivityToAddDay(currentDay);
        Mockito.verify(view, Mockito.never()).showDayAlreadyAddedToast();
        Mockito.verify(view, Mockito.never()).showStartAddDayActivityToAddDay(currentDay);
    }

    @Test
    public void addDayIsNotFirstDayAndDaysNotNull() {

        presenter.addDay(false, new ArrayList<Day>());
        Mockito.verify(view, Mockito.never()).showStartAddDayActivityToAddDay(currentDay);

        /**
         * Will trigger checkIfDayAlreadyAdded which is tested in other test method
         * */

    }

}