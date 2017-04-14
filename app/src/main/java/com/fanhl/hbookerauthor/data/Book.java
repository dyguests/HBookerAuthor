package com.fanhl.hbookerauthor.data;

/**
 * Created by fanhl on 2017/4/11.
 */
public class Book {
    private String id;
    /** 封面 url */
    private String cover;
    /** 总收藏 */
    private int collect;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getCollect() {
        return collect;
    }
}
