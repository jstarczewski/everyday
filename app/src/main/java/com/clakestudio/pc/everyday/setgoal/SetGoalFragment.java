package com.clakestudio.pc.everyday.setgoal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.settings.SettingsActivity;

public class SetGoalFragment extends Fragment implements SetGoalContract.View, View.OnClickListener {

    private EditText etGoal;
    private Button btSetGoal;
    private TextView tvGoal;

    private SetGoalPresenter presenter;

    public SetGoalFragment() {
        // Required empty public constructor
    }

    public static SetGoalFragment newInstance() {
        return new SetGoalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_set_goal, container, false);

        etGoal = view.findViewById(R.id.etGoal);
        btSetGoal = view.findViewById(R.id.btSetGoal);
        tvGoal = view.findViewById(R.id.tvGoal);
        btSetGoal.setOnClickListener(this);

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
    public void setPresenter(SetGoalContract.Presenter presenter) {
        this.presenter = (SetGoalPresenter) presenter;
    }

    @Override
    public void showStartSettingsActivity() {
        startActivity(new Intent(getActivity(), SettingsActivity.class));
        if (getActivity() != null)
            getActivity().finish();
    }

    @Override
    public void determineGoalTextViewVisibility() {
        if (tvGoal.getVisibility() == View.VISIBLE) {
            etGoal.setVisibility(View.VISIBLE);
            btSetGoal.setVisibility(View.VISIBLE);
            tvGoal.setVisibility(View.GONE);
        } else {
            tvGoal.setVisibility(View.VISIBLE);
            btSetGoal.setVisibility(View.GONE);
            etGoal.setVisibility(View.GONE);
        }

    }

    @Override
    public void showGoalForThreeSeconds() {
        tvGoal.setText(etGoal.getText().toString());
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                presenter.startSettingsActivity();
            }
        }.start();
    }

    @Override
    public void showEmptyGoalToast() {
        Toast.makeText(getContext(), "Goal cannot be empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        presenter.setGoal(etGoal.getText().toString());
    }

}
