package com.fanhl.hbookerauthor.io.jsoup.service;

import com.fanhl.hbookerauthor.data.Book;
import com.fanhl.hbookerauthor.io.jsoup.parser.BooksParser;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by fanhl on 2017/4/15.
 */

public class BookService extends BaseSerivce {
    public Observable<List<Book>> view_list() {
        return get("/book_manage/view_list").map(BooksParser::view_list);
    }
}
