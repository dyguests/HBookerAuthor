package com.fanhl.hbookerauthor.ui.main;

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
import com.fanhl.hbookerauthor.io.rest.CookieHelper;
import com.fanhl.hbookerauthor.ui.common.BaseFragment;
import com.fanhl.hbookerauthor.ui.main.adapter.BooksManagerAdapter;
import com.fanhl.hbookerauthor.ui.main.widget.BookOperationsDialogFragment;
import com.fanhl.hbookerauthor.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
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

        adapter.setOnItemLongClickListener((position, holder) -> {
            Book data = (Book) ((BooksManagerAdapter.ViewHolder) holder).getData();
            BookOperationsDialogFragment.newInstance(data).show(getChildFragmentManager(),BookOperationsDialogFragment.TAG);
            return true;
        });
    }

    private void refreshData() {
        Observable
                .create((ObservableOnSubscribe<Document>) emitter -> {
                    try {
                        Connection.Response response = Jsoup.connect("http://author.hbooker.com/book_manage/view_list")
                                .cookies(CookieHelper.getCookie())
                                .execute();
                        CookieHelper.save(response.cookies());
                        Document document = response.parse();
                        emitter.onNext(document);
                        emitter.onComplete();
                    } catch (Exception e1) {
                        emitter.onError(e1);
                    }
                })
                .map(BooksParser::view_list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Book>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
                    }

                    @Override
                    public void onNext(List<Book> books) {
                        Log.d(TAG, "books:" + books);
                        adapter.replaceItems(books);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "e:", e);
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

}
