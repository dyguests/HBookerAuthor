package com.fanhl.hbookerauthor.io.jsoup;

import com.fanhl.hbookerauthor.io.jsoup.service.AccountService;
import com.fanhl.hbookerauthor.io.jsoup.service.BookService;

/**
 * Created by fanhl on 2017/4/15.
 */
public class JsoupClient {
    public AccountService getAccountService() {
        return new AccountService();
    }

    public BookService getBookService() {
        return null;
    }
}
