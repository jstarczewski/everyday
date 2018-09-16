package com.clakestudio.pc.everyday.forgotpassword;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.settings.SettingsActivity;

public class ForgotPasswordFragment extends Fragment implements ForgotPasswordContract.View, View.OnClickListener {

    private ForgotPasswordContract.Presenter presenter;
    private EditText etGoal;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    public static ForgotPasswordFragment newInstance() {
        return new ForgotPasswordFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        etGoal = (EditText) view.findViewById(R.id.etEnterGoal);
        Button btCheckGoal = (Button) view.findViewById(R.id.btCheckGoal);
        btCheckGoal.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setPresenter(ForgotPasswordContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showStartSettingsActivity() {
        startActivity(new Intent(getContext(), SettingsActivity.class));
        getActivity().finish();
    }

    @Override
    public void showToastAboutGoalIncorrectness() {
        Toast.makeText(getContext(), R.string.incorrect_goal, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        presenter.checkGoalCorrectness(etGoal.getText().toString());
    }

}
