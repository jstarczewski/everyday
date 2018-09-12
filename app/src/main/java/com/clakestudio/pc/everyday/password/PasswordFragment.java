package com.clakestudio.pc.everyday.password;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.forgotpassword.ForgotPasswordActivity;
import com.clakestudio.pc.everyday.showdays.ShowDaysActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PasswordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PasswordFragment extends Fragment implements PasswordContract.View, View.OnClickListener, TextWatcher {

    private PasswordPresenter passwordPresenter;
    private EditText etPassword;
    private Button btForgotPassword;

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
        btForgotPassword = (Button) view.findViewById(R.id.btForgotPassword);
        etPassword = (EditText) view.findViewById(R.id.etPassword);
        etPassword.addTextChangedListener(this);

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
        showForgotPasswordActivity();
    }

    @Override
    public void showShowDaysActivity() {
        startActivity(new Intent(getActivity(), ShowDaysActivity.class));
        getActivity().finish();
    }

    @Override
    public void showForgotPasswordActivity() {
        startActivity(new Intent(getActivity(), ForgotPasswordActivity.class));
        getActivity().finish();
    }

    @Override
    public void showWrongPasswordToast() {
        Toast.makeText(getContext(), "Wrong password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        passwordPresenter.checkPasswordCorrectness(etPassword.getText().toString());
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
