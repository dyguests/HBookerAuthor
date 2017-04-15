package com.fanhl.hbookerauthor.io.jsoup.service;

import com.fanhl.hbookerauthor.io.retrofit.data.request.LoginForm;

import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by fanhl on 2017/4/15.
 */

public class AccountService extends BaseSerivce {
    public Observable<Document> login(LoginForm loginForm) {
        Map<String, String> data = new HashMap<>();
        data.put("email", loginForm.getEmail());
        data.put("passwd", loginForm.getPassword());

        return post("/login/doLogin", data);
    }
}
