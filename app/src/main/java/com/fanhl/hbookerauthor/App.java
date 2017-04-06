package com.fanhl.hbookerauthor;

import android.app.Application;

import com.fanhl.hbookerauthor.io.rest.HBookerClient;

/**
 * Created by fanhl on 2017/4/6.
 */

public class App extends Application {
    private HBookerClient client;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public HBookerClient getClient() {
        if (client == null) {
            client = new HBookerClient(this);
        }
        return client;
    }
}
