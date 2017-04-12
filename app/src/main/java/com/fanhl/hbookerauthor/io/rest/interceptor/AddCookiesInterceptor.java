package com.fanhl.hbookerauthor.io.rest.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {
    private Context context;

    public AddCookiesInterceptor(Context context) {
        super();
        this.context = context;

    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();
        SharedPreferences sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);

        //最近在学习RxJava,这里用了RxJava的相关API大家可以忽略,用自己逻辑实现即可
        Observable.just(sharedPreferences.getString("cookie", ""))
                .subscribe(s -> builder.addHeader("Cookie", s));

        return chain.proceed(builder.build());
    }
}
