package com.clakestudio.pc.everyday.adddays;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.showdays.ShowDaysActivity;

public class AddDayFragment extends Fragment implements AddDayContract.View {

    private AddDayContract.Presenter addDayPresenter;
    private FloatingActionButton floatingActionButton;
    private EditText etTitle;
    private EditText etNote;
    private String toolbarTitle;

    public AddDayFragment() {
        // Required empty public constructor
    }

    public static AddDayFragment newInstance() {
        return new AddDayFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_add_day, container, false);

        etTitle = v.findViewById(R.id.etTitle);
        etNote = v.findViewById(R.id.etNote);
        floatingActionButton = getActivity().findViewById(R.id.fab);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // addDayPresenter.loadCurrentDayInfo(getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE).getInt("currentDay", 0):;

        if (getActivity() != null && getActivity().getIntent() != null) {
            int dayId = getActivity().getIntent().getExtras().getInt("dayId", 1);
            String title = getActivity().getIntent().getExtras().getString("title", "");
            String note = getActivity().getIntent().getExtras().getString("note", "");
            Boolean isNewDay = getActivity().getIntent().getExtras().getBoolean("isNewDay", false);
            Log.e("tag", " " + dayId + " -> " + title + " ->" + note);
            Log.e("isNewDay", "->"+isNewDay);
            addDayPresenter.setIsNewDay(isNewDay);
            addDayPresenter.loadDayInfo(dayId, title, note);
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDayPresenter.saveDay(DaysDataFormatter.getDayInfo(toolbarTitle, etTitle.getText().toString(), etNote.getText().toString()));
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        addDayPresenter.stop();

    }

    @Override
    public void setPresenter(AddDayContract.Presenter presenter) {
        this.addDayPresenter = presenter;
    }

    @Override
    public void stop() {

        /**
         * After some research seems like there is no need to set listeners to null
         *
         * */

        floatingActionButton.setOnClickListener(null);
    }

    @Override
    public void showCurrentDayInfo(String dayInfo, String tittle, String note) {
        AddDayActivity addDayActivity = (AddDayActivity) getActivity();
        if (addDayActivity != null && addDayActivity.getSupportActionBar() != null && addDayActivity.getSupportActionBar().getTitle() != null) {
            addDayActivity.getSupportActionBar().setTitle(dayInfo);
            toolbarTitle = dayInfo;
        }
        etTitle.setText(tittle);
        etNote.setText(note);
    }

    @Override
    public void showNewDayInfo(String dayInfo) {
        AddDayActivity addDayActivity = (AddDayActivity) getActivity();
        if (addDayActivity != null && addDayActivity.getSupportActionBar() != null && addDayActivity.getSupportActionBar().getTitle() != null) {
            addDayActivity.getSupportActionBar().setTitle(dayInfo);
            toolbarTitle = dayInfo;
        }
    }

    @Override
    public void showStartShowDaysActivity() {
        startActivity(new Intent(getContext(), ShowDaysActivity.class));
        if (getActivity() != null)
            getActivity().finish();
    }
}
