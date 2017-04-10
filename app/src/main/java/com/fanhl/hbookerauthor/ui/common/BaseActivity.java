package com.fanhl.hbookerauthor.ui.common;

import android.support.v7.app.AppCompatActivity;

import com.fanhl.hbookerauthor.App;

/**
 * Created by fanhl on 2017/4/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected App getApp() {
        return (App) getApplication();
    }
}
