package com.clakestudio.pc.everyday.test;

import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.info.InfoContract;
import com.clakestudio.pc.everyday.info.InfoPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class InfoPresenterTest {

    @Mock
    private InfoPresenter infoPresenter;

    @Mock
    private InfoContract.View view;

    @Mock
    private SettingsRepository settingsRepository;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        infoPresenter = new InfoPresenter(settingsRepository, view);

    }

    @Test
    public void setAppInfoUnderstood() {

        infoPresenter.setAppInfoUnderstood();
        Mockito.verify(settingsRepository).setInfoRead();
        Mockito.verify(view).showStartSetGoalActivity();

    }
}