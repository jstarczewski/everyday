package com.clakestudio.pc.everyday.di;

import com.clakestudio.pc.everyday.showdays.ShowDaysActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Jan on 9/15/2018.
 */

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = ShowDaysModule.class)
    abstract ShowDaysActivity showDaysActivity();

}
