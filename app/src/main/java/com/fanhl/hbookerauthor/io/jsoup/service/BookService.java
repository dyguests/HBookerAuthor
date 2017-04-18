package com.fanhl.hbookerauthor.io.jsoup.service;

import com.fanhl.hbookerauthor.data.Book;
import com.fanhl.hbookerauthor.io.jsoup.parser.BooksParser;
import com.fanhl.hbookerauthor.io.jsoup.response.BookSettingResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by fanhl on 2017/4/15.
 */

public class BookService extends BaseSerivce {
    public Observable<List<Book>> view_list() {
        return get("/book_manage/view_list").map(BooksParser::view_list);
    }

    public Observable<BookSettingResponse> book_info(String bookId) {
        return get("/book_manage/mod_book_info?book_id=" + bookId).map(BooksParser::book_info);
    }

    public Observable<List<Book.Volume>> view_chapter_info(String bookId) {
        return get("/book_manage/view_chapter_info?book_id=" + bookId).map(BooksParser::view_chapter_info);
    }
}
