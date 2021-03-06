package com.fanhl.hbookerauthor.io.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;

import com.fanhl.hbookerauthor.common.Constant;
import com.fanhl.hbookerauthor.io.retrofit.service.AccountService;
import com.fanhl.hbookerauthor.io.retrofit.service.BookService;
import com.fanhl.hbookerauthor.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fanhl on 2017/4/6.
 */
public class RetrofitClient {
    public static final String TAG = RetrofitClient.class.getSimpleName();

    private final Retrofit retrofit;

    private AccountService accountService;
    private BookService bookService;

    List<Cookie> cookies = new ArrayList<>();

    public RetrofitClient(Context context) {
        retrofit = new Retrofit.Builder()
                .client(getOkHttpClient(context))
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(getConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient getOkHttpClient(Context context) {
        //okhttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    //本拦截器用于加headers
                    Request request = chain.request();
                    request = request.newBuilder()
//                            .addHeader("cookie", RxSP.getToken())
//                            .addHeader("cookie", CookieHelper.get())
                            .build();
                    Log.d(TAG, "request Cookie:" + request.header("cookie"));
                    return chain.proceed(request);
                })
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Response response = chain.proceed(request);

                    String cookie = response.header("Set-Cookie");
                    Log.d(TAG, "response Set-Cookie:" + cookie);
//                    CookieHelper.set(cookie);

//                    RxSP.setToken(cookie);
                    return response;
                });
        builder.cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                RetrofitClient.this.cookies.addAll(cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                return cookies;
            }
        });
        return builder.build();
    }

    @NonNull
    private Converter.Factory getConverterFactory() {
        return GsonConverterFactory.create(getGson());
    }

    private Gson getGson() {
        return new GsonBuilder()
//                .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
//                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
//                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
//                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
//                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
//                .registerTypeAdapter(long.class, new LongDefault0Adapter())
//                //                    .serializeNulls()
                .create();
    }

    public AccountService getAccountService() {
        if (accountService == null) {
            accountService = retrofit.create(AccountService.class);
        }
        return accountService;
    }

    public BookService getBookService() {
        if (bookService == null) {
            bookService = retrofit.create(BookService.class);
        }
        return bookService;
    }
}
