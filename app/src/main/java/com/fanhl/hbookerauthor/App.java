package com.fanhl.hbookerauthor;

import android.app.Application;

import com.fanhl.hbookerauthor.util.RxSP;

/**
 * Created by fanhl on 2017/4/6.
 */

public class App extends Application {
//    private HBookerClient client;

    @Override
    public void onCreate() {
        super.onCreate();

        RxSP.init(this);
    }

//    public HBookerClient getClient() {
//        if (client == null) {
//            client = new HBookerClient(this);
//        }
//        return client;
//    }
}
