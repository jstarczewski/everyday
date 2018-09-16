package com.clakestudio.pc.everyday.password;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.clakestudio.pc.everyday.showgoal.ShowGoalActivity;

public class PasswordFragment extends Fragment implements PasswordContract.View, View.OnClickListener, TextWatcher {

    private PasswordPresenter presenter;
    private EditText etPassword;

    public PasswordFragment() {
        // Required empty public constructor
    }

    public static PasswordFragment newInstance() {
        return new PasswordFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password, container, false);
        Button btForgotPassword = view.findViewById(R.id.btForgotPassword);
        etPassword = view.findViewById(R.id.etPassword);
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
        this.presenter = (PasswordPresenter) presenter;
    }

    @Override
    public void onClick(View v) {
        presenter.startForgotPasswordActivity();
    }

    @Override
    public void showStartShowGoalActivity() {
        startActivity(new Intent(getActivity(), ShowGoalActivity.class));
        if (getActivity() != null)
            getActivity().finish();
    }

    @Override
    public void showStartForgotPasswordActivity() {
        startActivity(new Intent(getActivity(), ForgotPasswordActivity.class));
        if (getActivity() != null)
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
        presenter.checkPasswordCorrectness(etPassword.getText().toString());
    }

}
