package com.fanhl.hbookerauthor.io.rest.service;

import com.fanhl.hbookerauthor.io.rest.data.request.LoginForm;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by fanhl on 2017/4/6.
 */
public interface AccountService {
    String LOGIN_DO_LOGIN = "/login/doLogin/";

    /**
     * @param loginForm
     * @return Cookie:
     * hbooker_author_session=XXXufrm933en8ho89m2bch9hnl7n689b
     */
    @POST(LOGIN_DO_LOGIN)
    Observable<ResponseBody> login(@Body LoginForm loginForm);
}
