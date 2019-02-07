package com.clakestudio.pc.everyday.test;

import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.setgoal.SetGoalContract;
import com.clakestudio.pc.everyday.setgoal.SetGoalPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class SetGoalPresenterTest {

    @Mock
    private SetGoalPresenter setGoalPresenter;

    @Mock
    private SetGoalContract.View view;

    @Mock
    private SettingsRepository settingsRepository;

    private String emptyGoal = "";
    private String notEmptyGoal = "Goal";

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        setGoalPresenter = new SetGoalPresenter(settingsRepository, view);

    }

    @Test
    public void setGoalEmpty() {

        setGoalPresenter.setGoal(emptyGoal);
        Mockito.verify(view).showEmptyGoalToast();

    }

    @Test
    public void setGoalNotEmpty() {

        setGoalPresenter.setGoal(notEmptyGoal);
        Mockito.verify(settingsRepository).setGoal(notEmptyGoal);
        Mockito.verify(view).determineGoalTextViewVisibility();
        Mockito.verify(view).showGoalForThreeSeconds();

    }

    @Test
    public void startSettingsActivity() {

        setGoalPresenter.startSettingsActivity();
        Mockito.verify(view).showStartSettingsActivity();
    }
}