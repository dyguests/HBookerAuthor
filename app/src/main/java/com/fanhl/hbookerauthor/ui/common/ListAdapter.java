package com.fanhl.hbookerauthor.ui.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanhl on 2016/5/30.
 */
public abstract class ListAdapter<CVH extends ListAdapter.ViewHolder, ITEM> extends ClickableAdapter<CVH> implements Listable<ITEM> {
    protected final List<ITEM> list;

    public ListAdapter() {
        list = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(CVH holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //----------------数据增删 start-------------------

    @Override
    public void addItem(ITEM item) {
        int position = list.size();
        list.add(item);
        notifyItemInserted(position);
    }

    @Override
    public void addItems(List<ITEM> items) {
        int positionStart = list.size();
        list.addAll(items);
        notifyItemRangeInserted(positionStart, items.size());
    }

    @Override
    public void clear() {
        int itemCount = list.size();
        list.clear();
        notifyItemRangeRemoved(0, itemCount);
    }

    @Override
    public void replaceItems(List<ITEM> items) {
        int oldSize = list.size();
        list.clear();
        list.addAll(items);
        int newSize = list.size();

        if (newSize == oldSize) {
            notifyItemRangeChanged(0, oldSize);
        } else if (newSize > oldSize) {
            notifyItemRangeChanged(0, oldSize);
            notifyItemRangeInserted(oldSize, newSize - oldSize);
        } else {
            notifyItemRangeChanged(0, newSize);
            notifyItemRangeRemoved(newSize, oldSize - newSize);
        }
    }

    @Override
    public void addFirstItem(ITEM item) {
        throw new RuntimeException("没空实现 先不写");
    }

    @Override
    public void addFirstItems(List<ITEM> items) {
        throw new RuntimeException("没空实现 先不写");
    }

    //----------------数据增删 end-------------------

    public class ViewHolder extends RecyclerView.ViewHolder {
        ITEM data;

        public ViewHolder(View itemView) {
            super(itemView);

        }

        public void bind(ITEM data) {
            this.data = data;
        }

        public ITEM getData() {
            return data;
        }
    }
}
