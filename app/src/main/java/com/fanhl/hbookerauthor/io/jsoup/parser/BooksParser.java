package com.fanhl.hbookerauthor.io.jsoup.parser;

import com.fanhl.hbookerauthor.io.jsoup.response.BookListResponse;
import com.fanhl.hbookerauthor.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by fanhl on 2017/4/11.
 */

public class BooksParser {
    public static final String TAG = BooksParser.class.getSimpleName();

    public static BookListResponse view_list(ResponseBody responseBody) throws IOException {
        BookListResponse response = new BookListResponse();

        Log.d(TAG, "responseBody:" + responseBody);

        Document doc = Jsoup.parse(responseBody.string());

        doc.select("table");

        return response;
    }
}
