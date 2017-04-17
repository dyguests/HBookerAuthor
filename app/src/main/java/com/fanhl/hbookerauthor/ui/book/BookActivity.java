package com.fanhl.hbookerauthor.ui.book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.data.Book;
import com.fanhl.hbookerauthor.data.Tag;
import com.fanhl.hbookerauthor.ui.book.adapter.TagAdapter;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作品页面
 */
public class BookActivity extends AppCompatActivity {
    public static final String TAG = BookActivity.class.getSimpleName();
    public static final String EXTRA_DATA = "EXTRA_DATA";

    private Book data;
    private RecyclerView tagRecyclerView;
    private TagAdapter tagAdapter;

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

    private void assignViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        tagRecyclerView = ((RecyclerView) findViewById(R.id.tagRecyclerView));
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


        List<Tag> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Tag("Tag" + i));
        }
        tagAdapter.replaceItems(list);
    }

    private void showAddTagDialog() {

    }
}
