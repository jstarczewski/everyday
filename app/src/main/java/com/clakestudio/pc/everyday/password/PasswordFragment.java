package com.clakestudio.pc.everyday.password;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.clakestudio.pc.everyday.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PasswordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PasswordFragment extends Fragment implements PasswordContract.View, View.OnClickListener {

    private PasswordPresenter passwordPresenter;
    private EditText etPassword;
    private Button btForgotPassword;
    private Button btProcceed;

    public PasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PasswordFragment newInstance() {
        return new PasswordFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password, container, false);
        btForgotPassword = (Button)view.findViewById(R.id.btForgotPassword);
        etPassword = (EditText)view.findViewById(R.id.etPassword);
        btProcceed = (Button)view.findViewById(R.id.btProcceed);

        btProcceed.setOnClickListener(this);
        btForgotPassword.setOnClickListener(this);

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
    public void setPresenter(PasswordContract.Presenter presenter) {
        passwordPresenter = (PasswordPresenter) presenter;
    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals("btProcceed")) {
            if (passwordPresenter.isPasswordCorrect(etPassword.getText().toString()))
                showShowDaysActivity();
            else
                showWrongPasswordToast();
        }
        else {
            showForgotPasswordActivity();
        }
    }

    @Override
    public void showShowDaysActivity() {

    }

    @Override
    public void showForgotPasswordActivity() {

    }

    @Override
    public void showWrongPasswordToast() {

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
