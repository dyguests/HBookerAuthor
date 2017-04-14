package com.fanhl.hbookerauthor.data;

import java.util.Date;

/**
 * Created by fanhl on 2017/4/11.
 */
public class Book {
    private String id;
    /** 封面 url */
    private String cover;
    /** 总收藏 */
    private int collect;
    private String title;
    /** 字数统计 */
    private int wordCount;
    private Chapter latestChapter;
    private Status status;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setLatestChapter(Chapter latestChapter) {
        this.latestChapter = latestChapter;
    }

    public Chapter getLatestChapter() {
        return latestChapter;
    }

    public static class Chapter {


        private String title;
        private Date date;

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Date getDate() {
            return date;
        }
    }

    public enum Status {
        None,
        NotMetContractConditions,
        Signed;

        public static Status parse(String value) {
            if ("未满足签约字数条件".equals(value)) {
                return NotMetContractConditions;
            }

            return None;
        }
    }
}
