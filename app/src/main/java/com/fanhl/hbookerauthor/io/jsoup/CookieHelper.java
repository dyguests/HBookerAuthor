package com.fanhl.hbookerauthor.io.jsoup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanhl on 2017/4/13.
 */
public class CookieHelper {

    private static HashMap<String, String> cookieMap;

    static {
        cookieMap = new HashMap<>();
    }

    public static void save(Map<String, String> cookies) {
        cookieMap.putAll(cookies);
    }


    public static Map<String, String> getCookie() {
        return cookieMap;
    }
}
