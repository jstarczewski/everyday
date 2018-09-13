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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.adddays.AddDayActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CountdownFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CountdownFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountdownFragment extends Fragment implements CountdownContract.View, View.OnClickListener {

    private CountdownContract.Presenter presenter;
    private CountDownTimer countDownTimer;
    private TextView tvCountdown;
    private Button btSkip;
    private int countdownDuration;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_countdown, container, false);
        tvCountdown = (TextView) view.findViewById(R.id.tvCountdown);
        btSkip = (Button)view.findViewById(R.id.btSkip);
        btSkip.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countdownDuration = presenter.getFocusDuration();
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
                fireMediaPlayer();
            }
        }.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void startAddDayActivity() {
        Intent intent = new Intent(getContext(), AddDayActivity.class);
        intent.putExtra("dayId", dayId);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void updateTextViewCountDown(int timeLeft) {
        tvCountdown.setText(String.valueOf(timeLeft));
    }

    @Override
    public void fireMediaPlayer() {
        if (mediaPlayer != null)
            mediaPlayer.start();
        presenter.loadAddDayActivity();
    }

    @Override
    public void stopCountdownTimer() {
        countDownTimer.cancel();
    }

    @Override
    public void onClick(View v) {
        presenter.skip();
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

    @Override
    public void onPause() {
        super.onPause();
        stopCountdownTimer();
    }


}
