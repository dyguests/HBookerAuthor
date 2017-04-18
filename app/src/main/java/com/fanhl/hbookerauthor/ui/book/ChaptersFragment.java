package com.fanhl.hbookerauthor.ui.book;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.data.Book;

/**
 * Created by fanhl on 2017/4/18.
 */

public class ChaptersFragment extends Fragment {
    private Book data;

    public static ChaptersFragment newInstance(Book data) {

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

    }

    private void initData() {

    }

    private void refreshData() {

    }
}
