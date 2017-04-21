package com.fanhl.hbookerauthor.ui.guide;

import android.os.Bundle;
import android.os.Handler;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.ui.account.LoginActivity;
import com.fanhl.hbookerauthor.ui.common.BaseActivity;
import com.fanhl.hbookerauthor.ui.main.MainActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initData();
    }

    private void initData() {
        new Handler().postDelayed(() -> {
            if (getApp().isLogged()) {
                MainActivity.launch(SplashActivity.this);
            } else {
                LoginActivity.launch(SplashActivity.this);
            }
        }, 1500);
    }
}
