package com.clakestudio.pc.everyday.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.settings.Settings;
import com.clakestudio.pc.everyday.reminder.AfterDismissListener;
import com.clakestudio.pc.everyday.reminder.ui.TimePickerFragment;
import com.clakestudio.pc.everyday.showdays.ShowDaysActivity;

public class SettingsFragment extends Fragment implements SettingsContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener, AfterDismissListener {

//    private final String EMPTY = "";

    AlertDialog alertDialog;

    private SettingsContract.Presenter presenter;
    private Toolbar dialogToolbar;
    private Switch sPassword;
    private Switch sReminder;
    private EditText etDialogChangeGoal;
    private EditText etDialogChangePassword;

    private CheckBox cbOneMin;
    private CheckBox cbThreeMin;
    private CheckBox cbFiveMin;
    private CheckBox cbTenMin;

    TimePickerFragment timePickerFragment;


    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        sPassword = view.findViewById(R.id.sPassword);
        sReminder = view.findViewById(R.id.sFocusReminder);


        Button btChangeGoal = view.findViewById(R.id.btChangeGoal);
        btChangeGoal.setOnClickListener(this);


        cbOneMin = view.findViewById(R.id.cbDurationOne);
        cbThreeMin = view.findViewById(R.id.cbDurationThree);
        cbFiveMin = view.findViewById(R.id.cbDurationFive);
        cbTenMin = view.findViewById(R.id.cbDurationTen);


        // TimePickerDialog
        timePickerFragment = new TimePickerFragment();
        timePickerFragment.setCancelable(false);
        timePickerFragment.setAfterDismissListener(this);

        // Alert dialog
        alertDialog = new AlertDialog.Builder(view.getContext()).create();
        View alertDialogView = LayoutInflater.from(alertDialog.getContext()).inflate(R.layout.dialog_change, container, false);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        Button btCancel = alertDialogView.findViewById(R.id.btCancel);
        dialogToolbar = alertDialogView.findViewById(R.id.toolbar);
        btCancel.setOnClickListener(this);
        etDialogChangeGoal = alertDialogView.findViewById(R.id.etChangeGoal);
        etDialogChangePassword = alertDialogView.findViewById(R.id.etChangePassword);
        Button btConfirmChange = alertDialogView.findViewById(R.id.btConfirmChange);
        btConfirmChange.setOnClickListener(this);
        alertDialog.setView(alertDialogView);

//        btShowTimePicker = (Button) view.findViewById(R.id.btTurnOnOffNotification);

        presenter.start();

        sPassword.setOnCheckedChangeListener(this);
        sReminder.setOnCheckedChangeListener(this);

        cbOneMin.setOnCheckedChangeListener(this);
        cbThreeMin.setOnCheckedChangeListener(this);
        cbFiveMin.setOnCheckedChangeListener(this);
        cbTenMin.setOnCheckedChangeListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*        btShowTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
*/

    }
// TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setPresenter(SettingsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showSetReminderTimeDialog() {
        if (getActivity() != null && getActivity().getSupportFragmentManager() != null)
            timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void showStartChangePasswordDialog() {
        etDialogChangePassword.setVisibility(View.VISIBLE);
        etDialogChangeGoal.setVisibility(View.GONE);
        setDialogInfo(getString(R.string.change_password), getString(R.string.enter_new_password_here));
        alertDialog.show();
    }

    @Override
    public void showStartChangeGoalDialog() {
        etDialogChangeGoal.setVisibility(View.VISIBLE);
        etDialogChangePassword.setVisibility(View.GONE);
        setDialogInfo(getString(R.string.change_your_goal), getString(R.string.enter_your_goal_here));
        alertDialog.show();
    }


    @Override
    public void showChangeFocusDurationTime(int id) {

        cbOneMin.setChecked(false);
        cbThreeMin.setChecked(false);
        cbFiveMin.setChecked(false);
        cbTenMin.setChecked(false);

        switch (id) {
            case 1: {
                cbOneMin.setChecked(true);
                break;
            }
            case 3: {
                cbThreeMin.setChecked(true);
                break;
            }
            case 5: {
                cbFiveMin.setChecked(true);
                break;
            }
            case 10: {
                cbTenMin.setChecked(true);
                break;
            }
        }
    }

    @Override
    public void setDialogInfo(String toolbarTitle, String editTextHint) {
        etDialogChangePassword.setHint(editTextHint);
        etDialogChangeGoal.setHint(editTextHint);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialogToolbar.setTitle(toolbarTitle);
        }
    }

    @Override
    public void showDismissDialog() {
        alertDialog.dismiss();
        presenter.refresh();
    }

    @Override
    public void showSetPasswordSwitchOnOrOff(boolean value) {
        sPassword.setChecked(value);
    }

    @Override
    public void showSetReminderSwitchOnOrOff(boolean value) {
        sReminder.setChecked(value);
    }

    @Override
    public void showShowDaysActivity() {
        startActivity(new Intent(getContext(), ShowDaysActivity.class));
        if (getActivity() != null)
            getActivity().finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btChangeGoal: {
                presenter.startChangeGoalDialog();
                break;
            }
            case R.id.btConfirmChange: {
                presenter.save(dialogToolbar.getTitle().toString(), etDialogChangePassword.getText().toString(), etDialogChangeGoal.getText().toString());
                break;
            }
            case R.id.btCancel: {
                presenter.save(dialogToolbar.getTitle().toString(), Settings.NOT_SET.toString(), etDialogChangeGoal.getText().toString());
            }
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


        switch (buttonView.getId()) {

            case R.id.sFocusReminder: {
                presenter.saveIsReminderSet(isChecked);
                break;
            }
            case R.id.sPassword: {
                presenter.saveIsPasswordSet(isChecked);
                break;
            }
            default: {
                presenter.saveFocusDurationTime(isChecked, Integer.valueOf(buttonView.getTag().toString()));
            }
            /*
           case R.id.cbDurationOne: {
                presenter.saveFocusDurationTime(1);
                break;
            }
            case R.id.cbDurationThree: {
                presenter.saveFocusDurationTime(3);
                break;
            }
            case R.id.cbDurationFive: {
                presenter.saveFocusDurationTime(5);
                break;
            }
            case R.id.cbDurationTen: {
                presenter.saveFocusDurationTime(10);
                break;
            }*/


        }
    }

    @Override
    public void afterDismiss() {
        presenter.refresh();
    }

}

