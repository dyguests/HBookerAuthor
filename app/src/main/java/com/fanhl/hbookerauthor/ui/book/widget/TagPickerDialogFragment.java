package com.fanhl.hbookerauthor.ui.book.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanhl.hbookerauthor.R;

/**
 * Created by fanhl on 2017/4/17.
 */
public class TagPickerDialogFragment extends BottomSheetDialogFragment {
    public static final String TAG = TagPickerDialogFragment.class.getSimpleName();

    public static TagPickerDialogFragment newInstance() {

        Bundle args = new Bundle();

        TagPickerDialogFragment fragment = new TagPickerDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_tag_picker, container, false);
        assignViews(view);
        return view;
    }

    private void assignViews(View view) {

    }
}
