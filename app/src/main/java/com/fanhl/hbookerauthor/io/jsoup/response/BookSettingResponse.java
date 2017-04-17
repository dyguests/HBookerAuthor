package com.fanhl.hbookerauthor.io.jsoup.response;

import com.fanhl.hbookerauthor.data.Tag;

import java.util.List;

/**
 * Created by fanhl on 2017/4/17.
 */

public class BookSettingResponse {
    private List<Tag> myTags;

    public void setMyTags(List<Tag> myTags) {
        this.myTags = myTags;
    }

    public List<Tag> getMyTags() {
        return myTags;
    }
}
