package com.clakestudio.pc.everyday.test;

import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.password.PasswordContract;
import com.clakestudio.pc.everyday.password.PasswordPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class PasswordPresenterTest {

    @Mock
    private SettingsRepository settingsRepository;

    @Mock
    private PasswordContract.View view;

    @Mock
    private PasswordPresenter passwordPresenter;

    private String passwordCorrect = "1111";
    private String passwordNotCorrect = "9979";
    private String passwordNotCorrectTooShort = "99";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        passwordPresenter = new PasswordPresenter(settingsRepository, view);
        Mockito.when(settingsRepository.getPassword()).thenReturn(passwordCorrect);
    }

    @Test
    public void checkPasswordCorrectnessLengthNotEqualsFour() {
        passwordPresenter.checkPasswordCorrectness(passwordNotCorrectTooShort);
        Mockito.verify(view, Mockito.never()).showWrongPasswordToast();
        Mockito.verify(view, Mockito.never()).showStartShowGoalActivity();
    }


    @Test
    public void checkPasswordCorrectnessLengthEqualsFourAndNotCorrect() {
        passwordPresenter.checkPasswordCorrectness(passwordNotCorrect);
        Mockito.verify(view).showWrongPasswordToast();
    }


    @Test
    public void checkPasswordCorrectnessLengthEqualsFourAndCorrect() {
        passwordPresenter.checkPasswordCorrectness(passwordCorrect);
        Mockito.verify(view).showStartShowGoalActivity();
    }

    @Test
    public void startForgotPasswordActivity() {
        passwordPresenter.startForgotPasswordActivity();
        Mockito.verify(view).showStartForgotPasswordActivity();
    }
}