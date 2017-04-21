package com.fanhl.hbookerauthor;

import android.app.Application;

import com.fanhl.hbookerauthor.io.jsoup.JsoupClient;
import com.fanhl.hbookerauthor.model.LoginState;
import com.fanhl.hbookerauthor.util.RxSP;

/**
 * Created by fanhl on 2017/4/6.
 */

public class App extends Application {
    private JsoupClient client;

    private LoginState loginState = LoginState.NotLogin;

    @Override
    public void onCreate() {
        super.onCreate();

        RxSP.init(this);
    }

    public JsoupClient getClient() {
        if (client == null) {
            client = new JsoupClient();
        }
        return client;
    }

    public boolean isLogged() {
        return loginState == LoginState.Logged;
    }
}
