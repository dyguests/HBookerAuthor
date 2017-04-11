package com.fanhl.hbookerauthor.ui.common;

import android.support.v7.widget.RecyclerView;

/**
 * @param <CVH>
 */
public abstract class ClickableAdapter<CVH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<CVH> {

    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.itemLongClickListener = listener;
    }

    @Override
    public void onBindViewHolder(final CVH holder, final int position) {
        holder.itemView.setOnClickListener(view -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(holder.getAdapterPosition(), holder);
            }
        });
        holder.itemView.setOnLongClickListener(view -> itemLongClickListener != null && itemLongClickListener.onItemLongClick(holder.getAdapterPosition(), holder));
    }

    public interface OnItemClickListener {
        void onItemClick(int position, RecyclerView.ViewHolder holder);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(int position, RecyclerView.ViewHolder holder);
    }

}