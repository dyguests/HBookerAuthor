package com.fanhl.hbookerauthor.ui.common;

import android.support.v4.app.Fragment;

import com.fanhl.hbookerauthor.App;

/**
 * Created by fanhl on 2017/4/11.
 */

public abstract class BaseFragment extends Fragment {

    protected BaseActivity getBaseActivity() {
        return ((BaseActivity) getActivity());
    }

    protected App getApp() {
        return getBaseActivity().getApp();
    }
}
