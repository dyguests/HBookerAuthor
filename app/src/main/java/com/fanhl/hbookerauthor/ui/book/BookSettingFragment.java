package com.fanhl.hbookerauthor.ui.book;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.data.Book;
import com.fanhl.hbookerauthor.data.Tag;
import com.fanhl.hbookerauthor.io.jsoup.response.BookSettingResponse;
import com.fanhl.hbookerauthor.ui.book.adapter.TagAdapter;
import com.fanhl.hbookerauthor.ui.book.widget.TagPickerDialogFragment;
import com.fanhl.hbookerauthor.ui.common.BaseFragment;
import com.fanhl.hbookerauthor.util.Log;
import com.google.android.flexbox.FlexboxLayoutManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fanhl on 2017/4/18.
 */

public class BookSettingFragment extends BaseFragment {
    public static final String TAG = BookSettingFragment.class.getSimpleName();

    private RecyclerView tagRecyclerView;

    private TagAdapter tagAdapter;

    private Book data;
    private BookSettingResponse bookSettingResponse;

    public static BookSettingFragment newInstance(Book data) {

        Bundle args = new Bundle();

        BookSettingFragment fragment = new BookSettingFragment();
        fragment.setArguments(args);
        fragment.data = data;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_book, container, false);
        assignViews(view);
        initData();
        refreshData();
        return view;
    }

    private void assignViews(View view) {
        this.tagRecyclerView = (RecyclerView) view.findViewById(R.id.tagRecyclerView);
        tagRecyclerView.setItemAnimator(new DefaultItemAnimator());
        tagRecyclerView.setLayoutManager(new FlexboxLayoutManager());
    }

    private void initData() {
        tagAdapter = new TagAdapter();
        tagRecyclerView.setAdapter(tagAdapter);

        tagAdapter.setOnItemClickListener((position, holder) -> {
            TagAdapter.ViewHolder tagHolder = (TagAdapter.ViewHolder) holder;
            if (tagHolder instanceof TagAdapter.NormalViewHolder) {
                Tag tag = (Tag) tagHolder.getData();
                // FIXME: 2017/4/17
            } else if (tagHolder instanceof TagAdapter.AddViewHolder) {
                TagPickerDialogFragment.newInstance(bookSettingResponse.getTags(), bookSettingResponse.getMyTags())
                        .show(getChildFragmentManager(), TagPickerDialogFragment.TAG);
            }
        });
    }

    private void refreshData() {
        getApp().getClient().getBookService()
                .book_info(data.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookSettingResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BookSettingResponse bookSettingResponse) {
                        bindData(bookSettingResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError 获取bookSettingResponse失败", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    private void bindData(BookSettingResponse bookSettingResponse) {
        this.bookSettingResponse = bookSettingResponse;
        tagAdapter.replaceItems(bookSettingResponse.getMyTags());
    }
}
