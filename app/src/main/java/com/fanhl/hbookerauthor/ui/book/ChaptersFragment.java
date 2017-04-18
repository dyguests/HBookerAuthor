package com.fanhl.hbookerauthor.ui.book;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.data.Book;
import com.fanhl.hbookerauthor.ui.common.BaseFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 章节列表
 * Created by fanhl on 2017/4/18.
 */
public class ChaptersFragment extends BaseFragment {
    private Book data;

    public static ChaptersFragment newInstance(@NonNull Book data) {

        Bundle args = new Bundle();

        ChaptersFragment fragment = new ChaptersFragment();
        fragment.setArguments(args);
        fragment.data = data;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chapters, container, false);
        assignViews(view);
        initData();
        refreshData();
        return view;
    }

    private void assignViews(View view) {
// FIXME: 2017/4/18 see !!!!!!!!!StickyDemo project!!!!!!!!!
    }

    private void initData() {

    }

    private void refreshData() {
        getApp().getClient().getBookService()
                .view_chapter_info(data.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Book.Volume>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Book.Volume> book) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
