package com.fanhl.hbookerauthor.ui.main.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.data.Book;

/**
 * Created by fanhl on 2017/4/14.
 */

public class BookOperationsDialogFragment extends BottomSheetDialogFragment {
    public static final String TAG = BookOperationsDialogFragment.class.getSimpleName();

    private BookOperationsDialogFragment data;

    public static BookOperationsDialogFragment newInstance(@NonNull Book data) {
        Bundle args = new Bundle();

        BookOperationsDialogFragment fragment = new BookOperationsDialogFragment();
        fragment.setArguments(args);
        fragment.data = fragment;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_book_operations, container, false);
        return view;
    }

    //http://www.hbooker.com/book/book_detail/{bookId}
}
