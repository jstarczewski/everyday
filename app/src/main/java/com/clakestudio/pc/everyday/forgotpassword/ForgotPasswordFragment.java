package com.clakestudio.pc.everyday.forgotpassword;

import android.content.Context;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ForgotPasswordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ForgotPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForgotPasswordFragment extends Fragment implements ForgotPasswordContract.View, View.OnClickListener {

    private ForgotPasswordPresenter presenter;
    private EditText etGoal;
    private Button btCheckGoal

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

        etGoal = (EditText)view.findViewById(R.id.etEnterGoal);
        btCheckGoal = (Button)view.findViewById(R.id.btCheckGoal);
        btCheckGoal.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
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
    public void setPresenter(ForgotPasswordContract.Presenter presenter) {

    }

    @Override
    public void startSettingsActivity() {

    }

    @Override
    public void showToastAboutGoalIncorrectness() {
        Toast.makeText(getContext(), R.string.incorrect_goal, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkGoal() {
        presenter.checkGoalCorrectness(etGoal.getText().toString());
    }

    @Override
    public void onClick(View v) {
        checkGoal();
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
