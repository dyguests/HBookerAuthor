package com.fanhl.hbookerauthor.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.data.Book;
import com.fanhl.hbookerauthor.ui.common.ListAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by fanhl on 2017/4/11.
 */

class BooksManagerAdapter extends ListAdapter<BooksManagerAdapter.ViewHolder, Book> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false));
    }

    public class ViewHolder extends ListAdapter.ViewHolder {

        private final LinearLayout container;
        private final ImageView coverImg;
        private final TextView titleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            container = ((LinearLayout) itemView.findViewById(R.id.container));
            coverImg = ((ImageView) itemView.findViewById(R.id.coverImg));
            titleTv = ((TextView) itemView.findViewById(R.id.titleTv));
        }

        @Override
        public void bind(Object data) {
            super.bind(data);
            Book book = (Book) data;

            Picasso.with(coverImg.getContext())
                    .load(book.getCover())
                    .into(coverImg);
            titleTv.setText(book.getTitle());
        }
    }
}
