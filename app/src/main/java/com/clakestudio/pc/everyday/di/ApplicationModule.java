package com.clakestudio.pc.everyday.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Jan on 9/15/2018.
 */

@Module
public abstract class ApplicationModule {
    @Binds
    abstract Context bindContext(Application application);
}
