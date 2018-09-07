package com.clakestudio.pc.everyday.password;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clakestudio.pc.everyday.R;

public class PasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        PasswordFragment passwordFragment = (PasswordFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);


    }
}
