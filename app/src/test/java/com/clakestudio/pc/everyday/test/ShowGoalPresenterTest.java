package com.clakestudio.pc.everyday.test;

import com.clakestudio.pc.everyday.data.DayRepository;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.showgoal.ShowGoalContract;
import com.clakestudio.pc.everyday.showgoal.ShowGoalPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class ShowGoalPresenterTest {


    @Mock
    ShowGoalContract.View view;

    @Mock
    ShowGoalPresenter presenter;

    @Mock
    SettingsRepository settingsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new ShowGoalPresenter(settingsRepository, view);
        Mockito.when(settingsRepository.getGoal()).thenReturn("Goal");
    }

    @Test
    public void setMockGoalTest() {
        assertEquals(settingsRepository.getGoal(), "Goal");
    }


    @Test
    public void loadGoal() {
        presenter.loadGoal();
        Mockito.verify(view).showGoal(settingsRepository.getGoal());
    }

    @Test
    public void loadCountdown() {
        presenter.loadCountdown();
        Mockito.verify(view).showStartCountdown();
    }

    @Test
    public void startDaysActivity() {
       presenter.startDaysActivity();
       Mockito.verify(view).showStartDaysActivity();
    }
}