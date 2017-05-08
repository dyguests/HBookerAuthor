package com.fanhl.hbookerauthor.ui.common;

import android.support.v7.app.AppCompatActivity;

import com.fanhl.hbookerauthor.App;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by fanhl on 2017/4/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected App getApp() {
        return (App) getApplication();
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }
}