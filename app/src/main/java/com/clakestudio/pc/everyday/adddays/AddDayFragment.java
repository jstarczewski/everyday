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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.showdays.ShowDaysActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddDayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddDayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDayFragment extends Fragment implements AddDayContract.View {

    private AddDayContract.Presenter addDayPresenter;
    private FloatingActionButton floatingActionButton;
    private EditText etTitle;
    private EditText etNote;
    private String toolbarTitle;

    public AddDayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddDayFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        etTitle = (EditText) v.findViewById(R.id.etTitle);
        etNote = (EditText) v.findViewById(R.id.etNote);
        floatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // addDayPresenter.loadCurrentDayInfo(getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE).getInt("currentDay", 0):;

        int dayId = getActivity().getIntent().getExtras().getInt("dayId");
        String title = getActivity().getIntent().getExtras().getString("title");
        String note = getActivity().getIntent().getExtras().getString("note");
        if (title!=null && note!=null)
            addDayPresenter.loadCurrentDayInfo(dayId, title, note);

        addDayPresenter.loadCurrentDayInfo(dayId);

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
    }

    @Override
    public void setPresenter(AddDayContract.Presenter presenter) {
        this.addDayPresenter = presenter;
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
    public void showDays() {
        startActivity(new Intent(getContext(), ShowDaysActivity.class));
        if (getActivity() != null)
            getActivity().finish();
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
/*
class AsyncGetDayData extends AsyncTask<AddDayContract.Presenter, Void, Day> {

    @Override
    protected Day doInBackground(AddDayContract.Presenter... presenters) {
        return presenters[0].loadCurrentDayInfo();
    }

    @Override
    protected void onPostExecute(Day day) {
        super.onPostExecute(day);
    }
}*/