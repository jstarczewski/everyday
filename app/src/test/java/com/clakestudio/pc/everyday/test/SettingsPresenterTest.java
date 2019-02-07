package com.clakestudio.pc.everyday.test;

import com.clakestudio.pc.everyday.data.settings.Settings;
import com.clakestudio.pc.everyday.data.settings.SettingsRepository;
import com.clakestudio.pc.everyday.settings.SettingsContract;
import com.clakestudio.pc.everyday.settings.SettingsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class SettingsPresenterTest {

    @Mock
    private SettingsPresenter settingsPresenter;

    @Mock
    private SettingsRepository settingsRepository;

    @Mock
    private SettingsContract.View view;

    private String password = "Password";
    private String goal = "Goal";
    private int time = 3;
    private String patternCorrect = "Pattern";
    private String saveWhatCorrect = "Pattern";
    private String saveWhatIncorrect = "Not Pattern";

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        settingsPresenter = new SettingsPresenter(settingsRepository, view);

    }

    @Test
    public void saveIsPasswordSetFalse() {

        settingsPresenter.saveIsPasswordSet(false);
        // checking the mock inside the method setPassworToNotSet()
        Mockito.verify(settingsRepository).setPassword(Settings.NOT_SET.toString());

    }


    @Test
    public void saveIsPasswordSetTrue() {

        settingsPresenter.saveIsPasswordSet(true);
        Mockito.verify(view).showStartChangePasswordDialog();

    }

    @Test
    public void saveIsReminderSetTrue() {

        settingsPresenter.saveIsReminderSet(false);
        Mockito.verify(settingsRepository).setReminderTime(-1);
    }


    @Test
    public void saveIsReminderSetFalse() {

        settingsPresenter.saveIsReminderSet(true);
        Mockito.verify(view).showSetReminderTimeDialog();


    }

    @Test
    public void saveNewPassword() {

        settingsPresenter.saveNewPassword(password);
        Mockito.verify(settingsRepository).setPassword(password);

    }

    @Test
    public void saveNewGoal() {

        settingsPresenter.saveNewGoal(goal);
        Mockito.verify(settingsRepository).setGoal(goal);


    }

    @Test
    public void saveFocusDurationTimeIsCheckedFalse() {

        settingsPresenter.saveFocusDurationTime(false, time);
        Mockito.verify(view, Mockito.never()).showChangeFocusDurationTime(time);
        Mockito.verify(settingsRepository, Mockito.never()).setReminderTime(time);

    }


    @Test
    public void saveFocusDurationTimeIsCheckedTrue() {

        settingsPresenter.saveFocusDurationTime(true, time);
        Mockito.verify(settingsRepository).setFocusDurationTime(time);
        Mockito.verify(view).showChangeFocusDurationTime(time);

    }

    @Test
    public void checkIfPasswordIsSet() {

        Mockito.when(settingsRepository.isPasswordSet()).thenReturn(true);
        settingsPresenter.checkIfPasswordIsSet();
        Mockito.verify(settingsRepository).isPasswordSet();
        Mockito.verify(view).showSetPasswordSwitchOnOrOff(settingsRepository.isPasswordSet());
    }

    @Test
    public void checkIfReminderIsSet() {

        Mockito.when(settingsRepository.isReminderSet()).thenReturn(true);
        settingsPresenter.checkIfReminderIsSet();
        Mockito.verify(settingsRepository).isReminderSet();
        Mockito.verify(view).showSetReminderSwitchOnOrOff(settingsRepository.isReminderSet());

    }

    @Test
    public void setPasswordToNotSet() {

        settingsPresenter.setPasswordToNotSet();
        Mockito.verify(settingsRepository).setPassword(Settings.NOT_SET.toString());

    }

    @Test
    public void getFocusDurationTime() {

        Mockito.when(settingsRepository.getFocusDurationTime()).thenReturn(time);
        assertEquals(settingsRepository.getFocusDurationTime(), time);

    }

    @Test
    public void determineCheckBoxVisibility() {


        Mockito.when(settingsRepository.getFocusDurationTime()).thenReturn(time);
        settingsPresenter.determineCheckBoxVisibility();
        Mockito.verify(settingsRepository).getFocusDurationTime();
        Mockito.verify(view).showChangeFocusDurationTime(settingsPresenter.getFocusDurationTime());

    }

    @Test
    public void saveSaveWhatEqualsPattern() {

        settingsPresenter.save(saveWhatIncorrect, password, goal, patternCorrect);
        Mockito.verify(settingsRepository).setGoal(goal);
        Mockito.verify(view).showDismissDialog();

    }

    @Test
    public void saveSaveWhatEqualsPatternPasswordEqualsNotSet() {

        settingsPresenter.save(saveWhatIncorrect, Settings.NOT_SET.toString(), goal, patternCorrect);
        Mockito.verify(settingsRepository, Mockito.never()).setGoal(goal);
        Mockito.verify(view).showDismissDialog();

    }


    @Test
    public void saveSaveWhatNotEqualsPattern() {

        settingsPresenter.save(saveWhatCorrect, password, goal, patternCorrect);
        Mockito.verify(settingsRepository).setPassword(password);
        Mockito.verify(view).showDismissDialog();


    }

    @Test
    public void startShowDaysActivity() {

        settingsPresenter.startShowDaysActivity();
        Mockito.verify(view).showShowDaysActivity();

    }

    @Test
    public void startChangeGoalDialog() {

        settingsPresenter.startChangeGoalDialog();
        Mockito.verify(view).showStartChangeGoalDialog();

    }

    @Test
    public void startChangePasswordDialog() {

        settingsPresenter.startChangePasswordDialog();
        Mockito.verify(view).showStartChangePasswordDialog();

    }


    /*

    Does not need to be tested, because all methods inside are tested

    @Test
    public void refresh() {


    }*/
}