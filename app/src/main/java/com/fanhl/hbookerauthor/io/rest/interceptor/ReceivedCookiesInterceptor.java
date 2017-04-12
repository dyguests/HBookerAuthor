package com.fanhl.hbookerauthor.io.rest.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    private Context context;

    public ReceivedCookiesInterceptor(Context context) {
        super();
        this.context = context;

    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        //这里获取请求返回的cookie
        List<String> list = originalResponse.headers("Set-Cookie");
        if (!list.isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();

            //最近在学习RxJava,这里用了RxJava的相关API大家可以忽略,用自己逻辑实现即可.大家可以用别的方法保存cookie数据
            list = originalResponse.headers("Set-Cookie");
            String[] array = list.toArray(new String[list.size()]);

            Observable.fromArray(array)
                    .map(s -> s.split(";")[0])
                    .subscribe(cookie -> cookieBuffer.append(cookie).append(";"));

            SharedPreferences sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("cookie", cookieBuffer.toString());
            editor.commit();
        }

        return originalResponse;
    }
}