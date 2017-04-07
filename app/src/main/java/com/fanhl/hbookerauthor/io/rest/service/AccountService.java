package com.fanhl.hbookerauthor.io.rest.service;

import com.fanhl.hbookerauthor.io.rest.data.request.LoginForm;

import io.reactivex.Observable;
import okhttp3.Response;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by fanhl on 2017/4/6.
 */
public interface AccountService {

    @FormUrlEncoded
    @POST("/login/doLogin/")
    Observable<Response> login(@Body LoginForm loginForm);
}
