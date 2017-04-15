package com.fanhl.hbookerauthor.io.jsoup.service;

import com.fanhl.hbookerauthor.common.Constant;
import com.fanhl.hbookerauthor.io.jsoup.CookieHelper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
}
