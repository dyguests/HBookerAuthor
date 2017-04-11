package com.fanhl.hbookerauthor.io.rest;

import android.content.Context;
import android.support.annotation.NonNull;

import com.fanhl.hbookerauthor.common.Constant;
import com.fanhl.hbookerauthor.io.rest.service.AccountService;
import com.fanhl.hbookerauthor.io.rest.service.BookService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fanhl on 2017/4/6.
 */
public class HBookerClient {

    private final Retrofit retrofit;

    private AccountService accountService;
    private BookService bookService;

    public HBookerClient(Context context) {
        retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(Constant.HTTP_SERVER_URL)
                .addConverterFactory(getConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        //okhttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    //本拦截器用于加headers
                    Request request = chain.request();
                    request = request.newBuilder()
//                            .addHeader("cookie", "ticket=" + "FXIME ticket")// FIXME: 2017/3/17
                            .addHeader("cookie", "hbooker_author_session=388m85a67vuggph7m55pf4rr0t00vim9")// FIXME: 2017/3/17
                            .addHeader("User-Agent", "FIXME User-Agent")// FIXME: 2017/3/17
                            .build();
                    return chain.proceed(request);
                });

//        //add fanhl 2017/1/11 超过时间设置
//        builder
//                .readTimeout(Constant.TIMEOUT_TIME, TimeUnit.SECONDS)
//                .connectTimeout(Constant.TIMEOUT_TIME, TimeUnit.SECONDS);

//        //请求失败后禁止retry
//        builder.retryOnConnectionFailure(false);

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
