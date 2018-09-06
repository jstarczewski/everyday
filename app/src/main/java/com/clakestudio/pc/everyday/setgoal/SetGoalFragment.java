package com.clakestudio.pc.everyday.setgoal;

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

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.showdays.ShowDaysContract;
import com.clakestudio.pc.everyday.showdays.ShowDaysPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SetGoalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SetGoalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetGoalFragment extends Fragment implements SetGoalContract.View, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText etGoal;
    private Button btSetGoal;

    // TODO: Rename and change types of parameters

    private SetGoalPresenter setGoalPresenter;

    public SetGoalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SetGoalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetGoalFragment newInstance() {
        return new SetGoalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_set_goal, container, false);

        EditText etGoal = (EditText)view.findViewById(R.id.etGoal);
        Button btSetGoal = (Button)view.findViewById(R.id.btSetGoal);
        btSetGoal.setOnClickListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event

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
        this.setGoalPresenter = (SetGoalPresenter) presenter;
    }

    @Override
    public void showSettingsActivity() {
        startActivity(new Intent(getActivity(), SettingsA));
    }

    @Override
    public void onClick(View v) {

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
