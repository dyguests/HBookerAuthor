package com.fanhl.hbookerauthor.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanhl.hbookerauthor.R;

/**
 * Created by fanhl on 2017/4/11.
 */

public class BooksManagerFragment extends Fragment {
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
        refreshData();
        return view;
    }

    private void refreshData() {
        
    }
}
