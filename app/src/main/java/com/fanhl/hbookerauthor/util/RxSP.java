package com.fanhl.hbookerauthor.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.f2prateek.rx.preferences2.Preference;
import com.f2prateek.rx.preferences2.RxSharedPreferences;

/**
 * RxSharedPreferences
 *
 * Created by fanhl on 2017/4/11.
 */
public class RxSP {
    public static final String KEY_TOKEN = "KEY_TOKEN";
    public static final String STRING_EMPTY = "";

    private static RxSharedPreferences rxPreferences;

    public static void init(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        rxPreferences = RxSharedPreferences.create(preferences);
    }


    @Nullable
    public static Preference<String> getString(String key) {
        return rxPreferences.getString(key);
    }

    @NonNull
    public static Preference<String> getStringDefaultEmpty(String key) {
        return rxPreferences.getString(key, STRING_EMPTY);
    }
}
