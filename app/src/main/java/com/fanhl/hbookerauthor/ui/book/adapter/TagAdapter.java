package com.fanhl.hbookerauthor.ui.book.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.data.Tag;
import com.fanhl.hbookerauthor.ui.common.ListAdapter;

/**
 * Created by fanhl on 2017/4/17.
 */

public class TagAdapter extends ListAdapter<TagAdapter.ViewHolder, Tag> {

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_ADD = 1;

    @Override
    public int getItemViewType(int position) {
        if (position < list.size()) {
            return TYPE_NORMAL;
        } else {
            return TYPE_ADD;
        }
    }

    @Override
    public TagAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NORMAL:
                return new TagAdapter.NormalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_tag, parent, false));
            case TYPE_ADD:
                return new TagAdapter.AddViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_tag_add, parent, false));
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public abstract class ViewHolder extends ListAdapter.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class NormalViewHolder extends TagAdapter.ViewHolder {
        public NormalViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(Object data) {
            super.bind(data);
            ((TextView) itemView).setText(((Tag) data).getValue());
        }
    }

    public class AddViewHolder extends TagAdapter.ViewHolder {
        public AddViewHolder(View itemView) {
            super(itemView);
        }
    }
}
