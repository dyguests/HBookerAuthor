package com.fanhl.hbookerauthor.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fanhl on 15/8/6.
 */
public class DateUtil {
    public static final String FORMAT_TIMESTAMP = "MMM dd, yyyy HH:mm:ss a";
    public static final String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_yMdHm = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_SHORT = "yyyy-MM-dd";
    public static final String FORMAT_SHORT2 = "yyyy/MM/dd";
    public static final String FORMAT_NUMBER = "yyyyMMdd";
    public static final String FORMAT_CN = "yyyy年MM月dd日 HH:mm:ss";
    public static final String FORMAT_CN_SHORT = "yyyy年MM月dd日";
    public static final String FORMAT_TIME = "HH:mm";
    public static final String FORMAT_yyyyMM = "yyyy-MM";
    public static final String FORMAT_hms = "HH:mm:ss";
    public static final String FORMAT_CN_YM = "yyyy年MM月";
    public static final String FORMAT_CN_YM2 = "yyyy年M月";
    public static final String FORMAT_CN_YMD = "yyyy年MM月dd日";
    public static final String FORMAT_CN_MD = "M月d日";
    public static final String FORMAT_CN_d_Hm = "dd日 HH:mm";
    public static final String FORMAT_MdHm = "MM-dd HH:mm";
    public static final String FORMAT_Md = "MM-dd";
    public static final String FORMAT_Md2 = "MM/dd";
    public static final String FORMAT_CN_M = "MM月";
    public static final String FORMAT_d = "d";

    /**
     * templete: FORMAT_STR -> Date
     *
     * @param str
     * @param format
     * @return
     */
    public static Date str2date(String str, String format) {
        if (Util.isNullOrEmpty(str)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * templete: Date -> FORMAT_STR
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2str(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(format);
        return sdf.format(date);
    }

    /**
     * templete: "yyyy-MM-dd HH:mm:ss" -> Date
     *
     * @param str
     * @return
     */
    public static Date long2date(String str) {
        return str2date(str, FORMAT_LONG);
    }

    /**
     * templete: "yyyy-MM-dd" -> Date
     *
     * @param str
     * @return
     */
    public static Date short2date(String str) {
        return str2date(str, FORMAT_SHORT);
    }

    /**
     * templete: "yyyy/MM/dd" -> Date
     *
     * @param str
     * @return
     */
    public static Date short2date2(String str) {
        return str2date(str, FORMAT_SHORT2);
    }


    /**
     * templete: "yyyyMMdd" -> Date
     *
     * @param str
     * @return
     */
    public static Date number2date(String str) {
        return str2date(str, FORMAT_NUMBER);
    }

    /**
     * templete: "yyyy年MM月dd日" -> Date
     *
     * @param str
     * @return
     */
    public static Date cnshort2date(String str) {
        return str2date(str, FORMAT_CN_SHORT);
    }

    /**
     * templete: "Sep 15, 2014 12:00:01 AM" -> Date
     *
     * @param str
     * @return
     */
    public static Date gson2date(String str) {
        return str2date(str, FORMAT_TIMESTAMP);
    }

    /**
     * templete: Date -> "yyyy-MM-dd HH:mm:ss"
     *
     * @param date
     * @return
     */
    public static String date2long(Date date) {
        return date2str(date, FORMAT_LONG);
    }

    /**
     * templete: Date -> "Sep 15, 2014 12:00:01 AM"
     *
     * @param date
     * @return
     */
    public static String date2gson(Date date) {
        return date2str(date, FORMAT_TIMESTAMP);
    }

    /**
     * templete: Date -> "yyyy-MM-dd"
     *
     * @param date
     * @return
     */
    public static String date2short(Date date) {
        return date2str(date, FORMAT_SHORT);
    }

    /**
     * templete: Date -> "yyyy/MM/dd"
     *
     * @param date
     * @return
     */
    public static String date2short2(Date date) {
        return date2str(date, FORMAT_SHORT2);
    }

    /**
     * templete: String -> "yyyyMMdd"
     *
     * @param date
     * @return
     */
    public static String date2number(Date date) {
        return date2str(date, FORMAT_NUMBER);
    }

    /**
     * templete: String -> "yyyy年MM月dd日 HH:mm:ss"
     *
     * @param date
     * @return
     */
    public static String date2cn(Date date) {
        return date2str(date, FORMAT_CN);
    }

    /**
     * templete: String -> "yyyy年MM月dd日"
     *
     * @param date
     * @return
     */
    public static String date2cnshort(Date date) {
        return date2str(date, FORMAT_CN_SHORT);
    }
}
