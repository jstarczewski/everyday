package com.clakestudio.pc.everyday.info;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.setgoal.SetGoalActivity;

public class InfoFragment extends Fragment implements InfoContract.View, View.OnClickListener {

    private InfoPresenter infoPresenter;


    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        Button btInfoUnderstood = view.findViewById(R.id.btInfoUnderstood);
        btInfoUnderstood.setOnClickListener(this);
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
    public void setPresenter(InfoContract.Presenter presenter) {
        this.infoPresenter = (InfoPresenter) presenter;
    }

    @Override
    public void stop() {

    }

    @Override
    public void showStartSetGoalActivity() {
        startActivity(new Intent(getActivity(), SetGoalActivity.class));
        if (getActivity()!=null)
            getActivity().finish();
    }

    @Override
    public void onClick(View v) {
        infoPresenter.setAppInfoUnderstood();
    }

}
