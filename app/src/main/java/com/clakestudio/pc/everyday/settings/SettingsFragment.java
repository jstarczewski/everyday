package com.clakestudio.pc.everyday.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toolbar;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.reminder.ui.TimePickerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment implements SettingsContract.View {
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
    private ConstraintLayout clPassword;
    private ConstraintLayout clGoal;
    private ConstraintLayout clReminder;
    private Button btConfirmChange;

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

        clPassword = (ConstraintLayout) view.findViewById(R.id.clChangePassword);
        clGoal = (ConstraintLayout) view.findViewById(R.id.clChangeGoal);
        clReminder = (ConstraintLayout) view.findViewById(R.id.clChangeReminderTime);


        // Alert dialog
        alertDialog = new AlertDialog.Builder(view.getContext()).create();
        View alertDialogView = LayoutInflater.from(alertDialog.getContext()).inflate(R.layout.dialog_change, container);
        dialogToolbar = (Toolbar) alertDialogView.findViewById(R.id.toolbar);
        etNewPasswordOrGoal = (EditText) alertDialogView.findViewById(R.id.etChange);
        btConfirmChange = (Button) alertDialogView.findViewById(R.id.btConfirmChange);
        alertDialog.setView(alertDialogView);


//        btShowTimePicker = (Button) view.findViewById(R.id.btTurnOnOffNotification);

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
    public void showTimePicker() {

        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void showSwitchPasswordOnOff() {

    }

    @Override
    public void showFocusReminderOnOff() {

    }

    @Override
    public void showPasswordChangeOption() {
        if (clPassword.getVisibility() == View.VISIBLE)
            clPassword.setVisibility(View.GONE);
        else
            clPassword.setVisibility(View.VISIBLE);

    }

    @Override
    public void showGoalChangeOption() {

    }

    @Override
    public void showReminderTimeChangeOption() {
        if (clReminder.getVisibility() == View.VISIBLE)
            clReminder.setVisibility(View.GONE);
        else
            clReminder.setVisibility(View.VISIBLE);
    }

    @Override
    public void showChangePasswordDialog() {
        setDialogInfo("Change your goal", "Enter your new goal here");
        alertDialog.show();
    }

    @Override
    public void showChangeGoalDialog() {
        setDialogInfo("Change password", "Enter new password here");
        alertDialog.show();
    }

    @Override
    public void showChangeFocusDurationTime() {

    }

    @Override
    public void setDialogInfo(String toolbarTitle, String editTextHint) {
        etNewPasswordOrGoal.setHint(editTextHint);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialogToolbar.setTitle(toolbarTitle);
        }
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

