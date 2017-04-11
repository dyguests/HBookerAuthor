package com.fanhl.hbookerauthor.ui.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.data.Book;
import com.fanhl.hbookerauthor.io.jsoup.parser.BooksParser;
import com.fanhl.hbookerauthor.io.jsoup.response.BookListResponse;
import com.fanhl.hbookerauthor.ui.common.BaseFragment;
import com.fanhl.hbookerauthor.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fanhl on 2017/4/11.
 */
public class BooksManagerFragment extends BaseFragment {
    public static final String TAG = BooksManagerFragment.class.getSimpleName();
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private BooksManagerAdapter adapter;

    public static BooksManagerFragment newInstance() {

        Bundle args = new Bundle();

        BooksManagerFragment fragment = new BooksManagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books_manager, container, false);
        assignViews(view);
        initData();
        refreshData();
        return view;
    }

    private void assignViews(View view) {
        this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        swipeRefreshLayout.setOnRefreshListener(this::refreshData);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        adapter = new BooksManagerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void refreshData() {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));

        new AsyncTask<Object, Object, List<Book>>() {
            @Override
            protected List<Book> doInBackground(Object... params) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                List<Book> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    list.add(new Book());
                }
                return list;
            }

            @Override
            protected void onPostExecute(List<Book> books) {
                swipeRefreshLayout.setRefreshing(false);
                adapter.replaceItems(books);
            }
        }.execute();
//
        getApp().getClient().getBookService()
                .getView_list()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(BooksParser::view_list)
                .subscribe(new Observer<BookListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BookListResponse responseBody) {
                        Log.d(TAG, "responseBody:" + responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

}
