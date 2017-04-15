package com.fanhl.hbookerauthor.io.retrofit.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by fanhl on 2017/4/10.
 */

public interface BookService {

    @GET("/book_manage/view_list")
    Observable<ResponseBody> getView_list();
}
