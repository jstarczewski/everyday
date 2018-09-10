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
public class CountdownFragment extends Fragment implements CountdownContract.View {

    private CountdownContract.Presenter presenter;
    private CountDownTimer countDownTimer;
    private TextView tvCountdown;
    private int countdownDuration;
    private MediaPlayer mediaPlayer;

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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countdownDuration = presenter.getFocusDuration();
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.sound);
        startCountdownTimer(countdownDuration);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
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
        countDownTimer = new CountDownTimer(min * 1000, 1000) {
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
    public void startAddDayActivity() {
        startActivity(new Intent(getActivity(), AddDayActivity.class));
        getActivity().finish();
    }

    @Override
    public void updateTextViewCountDown(int timeLeft) {
        tvCountdown.setText(timeLeft);
    }

    @Override
    public void fireMediaPlayer() {
        if (mediaPlayer != null)
            mediaPlayer.start();
        presenter.loadAddDayActivity();
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
