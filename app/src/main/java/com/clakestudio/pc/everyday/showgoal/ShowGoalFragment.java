package com.clakestudio.pc.everyday.showgoal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.showdays.ShowDaysActivity;

public class ShowGoalFragment extends Fragment implements ShowGoalContract.View {

    private ShowGoalContract.Presenter presenter;
    private CountDownTimer countDownTimer;
    private TextView tvGoal;

    public ShowGoalFragment() {
        // Required empty public constructor
    }

    public static ShowGoalFragment newInstance() {
        return new ShowGoalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_show_goal, container, false);

        tvGoal = (TextView) view.findViewById(R.id.tvGoal);
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
    public void setPresenter(ShowGoalContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**
         * As long as countDownTimer is an Android component it is moved into lifecycle and UI layer
         *
         * */


        countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                presenter.startDaysActivity();
            }
        };
        presenter.start();

    }

    @Override
    public void showStartDaysActivity() {
        startActivity(new Intent(getContext(), ShowDaysActivity.class));
        getActivity().finish();
    }

    @Override
    public void showStartCountdown() {
        if (countDownTimer != null)
            countDownTimer.start();
    }

    @Override
    public void showGoal(String goal) {
        tvGoal.setText(goal);
    }

}
