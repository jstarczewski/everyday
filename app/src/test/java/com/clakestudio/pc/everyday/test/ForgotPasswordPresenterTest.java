package com.clakestudio.pc.everyday.test;

import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.forgotpassword.ForgotPasswordContract;
import com.clakestudio.pc.everyday.forgotpassword.ForgotPasswordPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class ForgotPasswordPresenterTest {

    @Mock
    private
    SettingsRepository settingsRepository;

    @Mock
    private
    ForgotPasswordContract.View view;

    @Mock
    private
    ForgotPasswordPresenter presenter;

    private String correctGoal = "Goal";
    private String notCorrectGoal = "Not Goal";


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        presenter = new ForgotPasswordPresenter(settingsRepository, view);

    }

    @Test
    public void checkGoalCorrectnessNotCorrectGoal() {

        Mockito.when(settingsRepository.isGoalSet()).thenReturn(true);
        Mockito.when(settingsRepository.getGoal()).thenReturn(notCorrectGoal);
        presenter.checkGoalCorrectness(correctGoal);
        Mockito.verify(view).showToastAboutGoalIncorrectness();

    }

    @Test
    public void checkGoalCorrectnessNotCorrectIsGoalSet() {

        Mockito.when(settingsRepository.isGoalSet()).thenReturn(false);
        Mockito.when(settingsRepository.getGoal()).thenReturn(correctGoal);
        presenter.checkGoalCorrectness(correctGoal);
        Mockito.verify(view).showToastAboutGoalIncorrectness();
    }

    @Test
    public void checkGoalCorrectnessAllNotCorrect() {

        Mockito.when(settingsRepository.isGoalSet()).thenReturn(false);
        Mockito.when(settingsRepository.getGoal()).thenReturn(notCorrectGoal);
        presenter.checkGoalCorrectness(correctGoal);
        Mockito.verify(view).showToastAboutGoalIncorrectness();
    }


    @Test
    public void checkGoalCorrectnessCorrect() {

        Mockito.when(settingsRepository.isGoalSet()).thenReturn(true);
        Mockito.when(settingsRepository.getGoal()).thenReturn(correctGoal);
        presenter.checkGoalCorrectness(correctGoal);
        Mockito.verify(view).showStartSettingsActivity();

    }
}