package com.fanhl.hbookerauthor.ui.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.data.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanhl on 2017/4/11.
 */
public class BooksManagerFragment extends Fragment {
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
    }

    private void initData() {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new BooksManagerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void refreshData() {
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
                adapter.replaceItems(books);
            }
        }.execute();
    }
}
