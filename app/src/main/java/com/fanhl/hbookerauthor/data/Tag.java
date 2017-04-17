package com.fanhl.hbookerauthor.data;

/**
 * Created by fanhl on 2017/4/17.
 */

public class Tag {
    private final String value;
    private final boolean isDefault;

    public Tag() {
        this(null);
    }

    public Tag(String value) {
        this(value, false);
    }

    public Tag(String value, boolean isDefault) {
        this.value = value;
        this.isDefault = isDefault;
    }

    public String getValue() {
        return value;
    }
}
