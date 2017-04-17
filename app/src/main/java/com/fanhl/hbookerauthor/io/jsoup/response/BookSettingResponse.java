package com.fanhl.hbookerauthor.io.jsoup.response;

import com.fanhl.hbookerauthor.data.Tag;

import java.util.List;

/**
 * Created by fanhl on 2017/4/17.
 */

public class BookSettingResponse {

    private List<Tag> myTags;
    /** 所有默认可选的tags */
    private List<Tag> tags;

    public void setMyTags(List<Tag> myTags) {
        this.myTags = myTags;
    }

    public List<Tag> getMyTags() {
        return myTags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return tags;
    }
}
