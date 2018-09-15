package com.clakestudio.pc.everyday;

import com.clakestudio.pc.everyday.data.DayRepository;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by Jan on 9/15/2018.
 */

public class EverydayApplication extends DaggerApplication {

    @Inject
    DayRepository dayRepository;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
