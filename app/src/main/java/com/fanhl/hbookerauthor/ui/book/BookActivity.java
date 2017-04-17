package com.fanhl.hbookerauthor.ui.book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.data.Book;
import com.fanhl.hbookerauthor.data.Tag;
import com.fanhl.hbookerauthor.io.jsoup.response.BookSettingResponse;
import com.fanhl.hbookerauthor.ui.book.adapter.TagAdapter;
import com.fanhl.hbookerauthor.ui.common.BaseActivity;
import com.fanhl.hbookerauthor.util.Log;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作品页面
 */
public class BookActivity extends BaseActivity {
    public static final String TAG = BookActivity.class.getSimpleName();
    public static final String EXTRA_DATA = "EXTRA_DATA";

    private Book data;
    private RecyclerView tagRecyclerView;
    private TagAdapter tagAdapter;
    private BookSettingResponse bookSettingResponse;

    public static void launch(Context context, Book data) {
        Intent intent = new Intent(context, BookActivity.class);
        intent.putExtra(EXTRA_DATA, data);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        data = getIntent().getParcelableExtra(EXTRA_DATA);

        assignViews();
        initData();
        refreshData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void assignViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        tagRecyclerView = ((RecyclerView) findViewById(R.id.tagRecyclerView));
        tagRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        getSupportActionBar().setTitle(data.getTitle());

        tagAdapter = new TagAdapter();
        tagRecyclerView.setAdapter(tagAdapter);
        tagRecyclerView.setLayoutManager(new FlexboxLayoutManager());

        tagAdapter.setOnItemClickListener((position, holder) -> {
            TagAdapter.ViewHolder tagHolder = (TagAdapter.ViewHolder) holder;
            if (tagHolder instanceof TagAdapter.NormalViewHolder) {
                Tag tag = (Tag) tagHolder.getData();
                // FIXME: 2017/4/17
            } else if (tagHolder instanceof TagAdapter.AddViewHolder) {
                showAddTagDialog();
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

        if (false) {
            List<Tag> list = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                list.add(new Tag("Tag" + i));
            }
            tagAdapter.replaceItems(list);
        }
    }

    private void bindData(BookSettingResponse bookSettingResponse) {
        this.bookSettingResponse = bookSettingResponse;
        tagAdapter.replaceItems(bookSettingResponse.getMyTags());
    }

    private void showAddTagDialog() {

    }
}
