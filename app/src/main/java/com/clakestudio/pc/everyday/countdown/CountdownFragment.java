package com.clakestudio.pc.everyday.countdown;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.adddays.AddDayActivity;

public class CountdownFragment extends Fragment implements CountdownContract.View, View.OnClickListener {

    private CountdownContract.Presenter presenter;
    private CountDownTimer countDownTimer;
    private TextView tvCountdown;
    private MediaPlayer mediaPlayer;
    private int dayId = 0;

    public CountdownFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CountdownFragment newInstance() {
        return new CountdownFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_countdown, container, false);
        tvCountdown = view.findViewById(R.id.tvCountdown);
        Button btSkip = view.findViewById(R.id.btSkip);
        btSkip.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.sound);

        if (getActivity() != null && getActivity().getIntent() != null) {
            dayId = getActivity().getIntent().getExtras().getInt("dayId", 0);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
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
    public void setPresenter(CountdownContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void stop() {

    }

    @Override
    public void startCountdownTimer(int min) {
        countDownTimer = new CountDownTimer(min * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                presenter.updateRemainingTime((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                /**
                 * Don't know whether it is good to call view methods directly
                 * */
                presenter.fireMediaPlayer();
            }
        }.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void showStartAddDayActivity() {
        Intent intent = new Intent(getContext(), AddDayActivity.class);
        intent.putExtra("dayId", dayId);
        startActivity(intent);
        if (getActivity() != null)
            getActivity().finish();
    }

    @Override
    public void showUpdateTextViewCountDown(int timeLeft) {
        tvCountdown.setText(String.valueOf(timeLeft));
    }

    @Override
    public void showFireMediaPlayer() {
        if (mediaPlayer != null)
            mediaPlayer.start();
        presenter.startAddDayActivity();
    }

    @Override
    public void showStopCountdownTimer() {
        countDownTimer.cancel();
    }

    @Override
    public void onClick(View v) {
        presenter.skipCountdown();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.stopCountdown();
    }


}
