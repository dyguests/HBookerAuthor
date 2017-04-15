package com.fanhl.hbookerauthor.io.jsoup.service;

import com.fanhl.hbookerauthor.common.Constant;
import com.fanhl.hbookerauthor.io.jsoup.CookieHelper;
import com.fanhl.hbookerauthor.io.retrofit.data.request.LoginForm;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import io.reactivex.Observable;

/**
 * Created by fanhl on 2017/4/15.
 */

public class AccountService {
    public Observable<Document> login(LoginForm loginForm) {
        return post("/login/doLogin", loginForm);
    }

    private Observable<Document> post(String path, LoginForm loginForm) {
        return Observable.create(emitter -> {
            try {
                Connection connection = Jsoup.connect(Constant.BASE_URL + path)
                        .method(Connection.Method.POST);
                addBody(loginForm, connection);
                Connection.Response response = connection.execute();
                CookieHelper.save(response.cookies());
                Document document = response.parse();
                emitter.onNext(document);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    private void addBody(LoginForm loginForm, Connection connection) {
        connection
                .data("email", loginForm.getEmail())
                .data("passwd", loginForm.getPassword());
    }
}
