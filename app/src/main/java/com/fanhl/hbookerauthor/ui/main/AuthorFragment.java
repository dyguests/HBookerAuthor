package com.fanhl.hbookerauthor.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.ui.account.LoginActivity;

/**
 * Created by fanhl on 2017/4/13.
 */

public class AuthorFragment extends Fragment {
    private android.widget.Button loginBtn;

    public static AuthorFragment newInstance() {

        Bundle args = new Bundle();

        AuthorFragment fragment = new AuthorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_author, container, false);
        assignViews(view);
        return view;
    }

    private void assignViews(View view) {
        this.loginBtn = (Button) view.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(v -> LoginActivity.launch(getContext()));
    }
}
