package com.fanhl.hbookerauthor.io.rest.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by fanhl on 2017/4/6.
 */
public interface AccountService {
    /**
     * @param email
     * @param passwd
     * @return
     */
    @FormUrlEncoded
    @POST("/login/doLogin/")
    Observable<ResponseBody> login(@Field("email") String email,
                                   @Field("passwd") String passwd);
}
