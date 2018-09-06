package com.clakestudio.pc.everyday.data.settings;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jan on 9/6/2018.
 */

public class SharedPreferencesSettings {

    /**
     * Could have been an dependency inejction but passing only a Context makes it easier to store data
     * This gonna be an singleton ->
     *                                 -> lets whether it works :0
     * The cons are problems with testing
     *
     * */

    private Context context;
    private SharedPreferencesSettings sharedPreferencesSettings;
    private SharedPreferences sharedPreferences;

    public SharedPreferencesSettings getInstance(Context context) {
        if (sharedPreferencesSettings==null) {
            sharedPreferencesSettings = new SharedPreferencesSettings(context);
        }
        return sharedPreferencesSettings;
    }
    private SharedPreferencesSettings(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

}
