package com.fanhl.hbookerauthor.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

        private final LinearLayout container;
        private final ImageView coverImg;

        public ViewHolder(View itemView) {
            super(itemView);
            container = ((LinearLayout) itemView.findViewById(R.id.container));
            coverImg = ((ImageView) itemView.findViewById(R.id.coverImg));

//            Palette.from(coverImg.getDrawingCache()).generate(palette -> {
//                container.setBackgroundColor(palette.getLightVibrantSwatch().getRgb());
//            });
        }

        @Override
        public void bind(Object data) {
            super.bind(data);
            Book book = (Book) data;

//            // https://novel-cdn.kuangxiangit.com/images/default.jpg
//            Picasso.with(coverImg.getContext())
//                    .load("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2971967025,2497343324&fm=80&w=179&h=119&img.JPEG")
//                    .into(new Target() {
//                        @Override
//                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                            coverImg.setImageBitmap(bitmap);
//                            Palette.from(bitmap).generate(palette -> {
//                                if (palette.getVibrantSwatch() != null) {
//                                    container.setBackgroundColor(palette.getVibrantSwatch().getRgb());
//                                }
//                            });
//
//                        }
//
//                        @Override
//                        public void onBitmapFailed(Drawable errorDrawable) {
//
//                        }
//
//                        @Override
//                        public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                        }
//                    });
        }
    }
}
