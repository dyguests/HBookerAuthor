package com.fanhl.hbookerauthor.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.data.Book;
import com.fanhl.hbookerauthor.ui.common.ListAdapter;

/**
 * Created by fanhl on 2017/4/11.
 */

class BooksManagerAdapter extends ListAdapter<BooksManagerAdapter.ViewHolder, Book> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false));
    }

    public class ViewHolder extends ListAdapter.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(Object data) {
            super.bind(data);
        }
    }
}
