package com.clakestudio.pc.everyday.settings;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.Toast;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.settings.Settings;
import com.clakestudio.pc.everyday.reminder.ui.TimePickerFragment;

public class SettingsFragment extends Fragment implements SettingsContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener, TimePickerDialog.OnKeyListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    AlertDialog alertDialog;

    private SettingsContract.Presenter presenter;
    private Button btShowTimePicker;
    private EditText etNewPasswordOrGoal;
    private Toolbar dialogToolbar;
    private Switch sPassword;
    private Switch sReminder;
    private ConstraintLayout clGoal;
    private Button btConfirmChange;
    private Button btChangeGoal;
    private Button btCancel;

    private CheckBox cbOneMin;
    private CheckBox cbThreeMin;
    private CheckBox cbFiveMin;
    private CheckBox cbTenMin;

    TimePickerFragment timePickerFragment;

    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        sPassword = (Switch) view.findViewById(R.id.sPassword);
        sReminder = (Switch) view.findViewById(R.id.sFocusReminder);


        clGoal = (ConstraintLayout) view.findViewById(R.id.clChangeGoal);
        btChangeGoal = (Button) view.findViewById(R.id.btChangeGoal);
        btChangeGoal.setOnClickListener(this);


        cbOneMin = (CheckBox) view.findViewById(R.id.cbDurationOne);
        cbThreeMin = (CheckBox) view.findViewById(R.id.cbDurationThree);
        cbFiveMin = (CheckBox) view.findViewById(R.id.cbDurationFive);
        cbTenMin = (CheckBox) view.findViewById(R.id.cbDurationTen);


        timePickerFragment = new TimePickerFragment();
        timePickerFragment.setCancelable(false);
        timePickerFragment.setSettingsPresenter((SettingsPresenter) presenter);

        // Alert dialog
        alertDialog = new AlertDialog.Builder(view.getContext()).create();
        View alertDialogView = LayoutInflater.from(alertDialog.getContext()).inflate(R.layout.dialog_change, null);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        btCancel = (Button) alertDialogView.findViewById(R.id.btCancel);
        dialogToolbar = (Toolbar) alertDialogView.findViewById(R.id.toolbar);
        btCancel.setOnClickListener(this);
        etNewPasswordOrGoal = (EditText) alertDialogView.findViewById(R.id.etChange);
        btConfirmChange = (Button) alertDialogView.findViewById(R.id.btConfirmChange);
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
        mListener = null;
    }

    @Override
    public void setPresenter(SettingsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showSetReminderTimeDialog() {
        timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void showChangePasswordDialog() {

        setDialogInfo(getString(R.string.change_password), getString(R.string.enter_new_password_here));
        alertDialog.show();
    }

    @Override
    public void showChangeGoalDialog() {
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
        etNewPasswordOrGoal.setHint(editTextHint);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialogToolbar.setTitle(toolbarTitle);
        }
    }

    @Override
    public void showDismissDialog() {
        alertDialog.dismiss();
        presenter.start();
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
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btChangeGoal: {
                showChangeGoalDialog();
                break;
            }
            case R.id.btConfirmChange: {
                Toast.makeText(getContext(), dialogToolbar.getTitle().toString(), Toast.LENGTH_SHORT).show();
                presenter.save(dialogToolbar.getTitle().toString(), etNewPasswordOrGoal.getText().toString());
                break;
            }
            case R.id.btCancel: {
                presenter.save(dialogToolbar.getTitle().toString(), Settings.NOT_SET.toString());
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
                if (isChecked)
                    presenter.saveNewFocusDurationTime(Integer.valueOf(buttonView.getTag().toString()));
                if (!isChecked && presenter.getFocusDurationTime() == Integer.valueOf(buttonView.getTag().toString()))
                    buttonView.setChecked(true);
            }
/*
           case R.id.cbDurationOne: {
                presenter.saveNewFocusDurationTime(1);
                break;
            }
            case R.id.cbDurationThree: {
                presenter.saveNewFocusDurationTime(3);
                break;
            }
            case R.id.cbDurationFive: {
                presenter.saveNewFocusDurationTime(5);
                break;
            }
            case R.id.cbDurationTen: {
                presenter.saveNewFocusDurationTime(10);
                break;
            }*/


        }
    }



    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        return false;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

