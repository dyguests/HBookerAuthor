package com.fanhl.hbookerauthor.io.jsoup.service;

import com.fanhl.hbookerauthor.common.Constant;
import com.fanhl.hbookerauthor.io.jsoup.CookieHelper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by fanhl on 2017/4/15.
 */

public abstract class BaseSerivce {
    protected Observable<Document> get(String path) {
        return Observable.create(emitter -> {
            try {
                Connection.Response response = Jsoup.connect(Constant.BASE_URL + path)
                        .cookies(CookieHelper.getCookie())
                        .execute();
                CookieHelper.save(response.cookies());
                Document document = response.parse();
                emitter.onNext(document);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    protected Observable<Document> post(String path, Map<String, String> data) {
        return Observable.create(emitter -> {
            try {
                Connection connection = Jsoup.connect(Constant.BASE_URL + path)
                        .method(Connection.Method.POST)
                        .cookies(CookieHelper.getCookie());
                if (data != null) {
                    connection.data(data);
                }
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
}
