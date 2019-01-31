package com.clakestudio.pc.everyday.test;

import com.clakestudio.pc.everyday.BaseView;
import com.clakestudio.pc.everyday.adddays.AddDayContract;
import com.clakestudio.pc.everyday.adddays.AddDayPresenter;
import com.clakestudio.pc.everyday.data.DayRepository;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
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
    }

    @Test
    public void saveDay() {
    }

    @Test
    public void setIsNewDay() {
        presenter.setIsNewDay(true);
        assertTrue(presenter.isNewDay());
    }
}