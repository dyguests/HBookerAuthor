package com.fanhl.hbookerauthor.ui.book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.data.Book;

/**
 * 作品页面
 */
public class BookActivity extends AppCompatActivity {

    public static final String EXTRA_DATA = "EXTRA_DATA";

    private Book data;

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
    }

    private void assignViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    private void initData() {
        getSupportActionBar().setTitle(data.getTitle());
    }
}
