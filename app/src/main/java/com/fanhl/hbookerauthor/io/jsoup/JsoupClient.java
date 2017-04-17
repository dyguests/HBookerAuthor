package com.fanhl.hbookerauthor.io.jsoup;

import com.fanhl.hbookerauthor.io.jsoup.service.AccountService;
import com.fanhl.hbookerauthor.io.jsoup.service.BookService;

/**
 * Created by fanhl on 2017/4/15.
 */
public class JsoupClient {

    private AccountService accountService;
    private BookService bookService;

    public AccountService getAccountService() {
        if (accountService == null) {
            accountService = new AccountService();
        }
        return accountService;
    }

    public BookService getBookService() {
        if (bookService == null) {
            bookService = new BookService();
        }
        return bookService;
    }
}
