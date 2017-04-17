package com.fanhl.hbookerauthor.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by fanhl on 2017/4/11.
 */
public class Book implements Parcelable {
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

    public static class Chapter implements Parcelable {


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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeLong(this.date != null ? this.date.getTime() : -1);
        }

        public Chapter() {
        }

        protected Chapter(Parcel in) {
            this.title = in.readString();
            long tmpDate = in.readLong();
            this.date = tmpDate == -1 ? null : new Date(tmpDate);
        }

        public static final Creator<Chapter> CREATOR = new Creator<Chapter>() {
            @Override
            public Chapter createFromParcel(Parcel source) {
                return new Chapter(source);
            }

            @Override
            public Chapter[] newArray(int size) {
                return new Chapter[size];
            }
        };
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.cover);
        dest.writeInt(this.collect);
        dest.writeString(this.title);
        dest.writeInt(this.wordCount);
        dest.writeParcelable(this.latestChapter, flags);
        dest.writeInt(this.status == null ? -1 : this.status.ordinal());
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.id = in.readString();
        this.cover = in.readString();
        this.collect = in.readInt();
        this.title = in.readString();
        this.wordCount = in.readInt();
        this.latestChapter = in.readParcelable(Chapter.class.getClassLoader());
        int tmpStatus = in.readInt();
        this.status = tmpStatus == -1 ? null : Status.values()[tmpStatus];
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
