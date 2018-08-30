package com.clakestudio.pc.everyday.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Jan on 8/30/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int fragmentFrameId) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragmentFrameId, fragment);
        fragmentTransaction.commit();

    }

}
