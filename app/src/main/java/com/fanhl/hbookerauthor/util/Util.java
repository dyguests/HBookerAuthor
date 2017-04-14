package com.fanhl.hbookerauthor.util;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static boolean isNullOrEmpty(String s) {
        if (s == null) {
            return true;
        }

        if (s.trim().contentEquals("")) {
            return true;
        }

        return false;
    }

    public static boolean isNull(Object s) {
        if (s == null) {
            return true;
        }
        return false;
    }

    public static boolean isNullOrEmpty(List list) {
        if (list == null) {
            return true;
        }

        return list.size() == 0;
    }

    public static boolean isNullOrEmpty(SparseArray list) {
        if (list == null) {
            return true;
        }

        return list.size() == 0;
    }

    public static String nullToDefault(String s) {
        if (s == null) {
            s = "";
        }

        return s;
    }

    public static boolean isEqualString(String s1, String s2) {
        if (s1 == null && s2 != null) {
            return false;
        }

        if (s1 != null && s2 == null) {
            return false;
        }

        if (s1 == null) {
            s1 = "";
        }
        if (s2 == null) {
            s2 = "";
        }

        return s1.equals(s2);
    }

    public static <T> ArrayList<T> nullToDefault(ArrayList<T> arrayList) {
        if (arrayList == null) {
            return new ArrayList<>();
        }

        return arrayList;
    }

    public static <T> SparseArray<T> nullToDefault(SparseArray<T> arraylist) {
        if (arraylist == null) {
            return new SparseArray<>();
        }

        return arraylist;
    }

    public static int getListSize(List list) {
        if (list == null) {
            return 0;
        }

        return list.size();
    }


}
