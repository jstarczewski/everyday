package com.clakestudio.pc.everyday.setgoal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.clakestudio.pc.everyday.BaseView;
import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.utils.BaseActivity;

public class SetGoalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);

        SetGoalFragment setGoalFragment = (SetGoalFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (setGoalFragment==null) {
            setGoalFragment = SetGoalFragment.newInstance();
            BaseActivity.addFragmentToActivity(getSupportFragmentManager(), setGoalFragment, R.id.contentFrame);
        }


    }
}
