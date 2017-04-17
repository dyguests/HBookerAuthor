package com.fanhl.hbookerauthor.ui.book.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.data.Tag;
import com.fanhl.hbookerauthor.ui.common.ListAdapter;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

/**
 * Created by fanhl on 2017/4/17.
 */
public class TagPickerDialogFragment extends BottomSheetDialogFragment {
    public static final String TAG = TagPickerDialogFragment.class.getSimpleName();

    private RecyclerView recyclerView;

    private List<Tag> tags;
    private List<Tag> selectedTags;

    public static TagPickerDialogFragment newInstance(List<Tag> tags, List<Tag> selectedTags) {

        Bundle args = new Bundle();

        TagPickerDialogFragment fragment = new TagPickerDialogFragment();
        fragment.setArguments(args);
        fragment.tags = tags;
        fragment.selectedTags = selectedTags;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_tag_picker, container, false);
        assignViews(view);
        initData();
        return view;
    }

    private void assignViews(View view) {
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new FlexboxLayoutManager());
    }

    private void initData() {
        recyclerView.setAdapter(new Adapter(tags, selectedTags));
    }

    private class Adapter extends ListAdapter<Adapter.ViewHolder, Tag> {

        private final List<Tag> tags;
        private final List<Tag> selectedTags;

        public Adapter(List<Tag> tags, List<Tag> selectedTags) {
            this.tags = tags;
            this.selectedTags = selectedTags;

            replaceItems(tags);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_tag_add, parent, false));
        }

        public class ViewHolder extends ListAdapter.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
            }

            @Override
            public void bind(Object data) {
                super.bind(data);
                ((TextView) itemView).setText(((Tag) data).getValue());
            }
        }
    }
}
