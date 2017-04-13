package com.fanhl.hbookerauthor.io.rest;

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

    public static void set(String cookies) {
        if (cookies == null) {
            return;
        }
        String[] parts = cookies.split(";");
        for (String part : parts) {
            int i = part.indexOf('=');
            if (i > 0 && i < part.length() - 1) {
                String key = part.substring(0, i);
                String value = part.substring(i + 1, part.length() - 1);
                cookieMap.put(key, value);
            }
        }
    }

    public static String get() {
        StringBuilder sb = new StringBuilder();
        cookieMap.forEach((key, value) -> {
            sb.append(key).append("=").append(value).append("; ");
        });
        return sb.toString();
    }

    public static void save(Map<String, String> cookies) {
        cookieMap.putAll(cookies);
    }


    public static Map<String, String> getCookie() {
        return cookieMap;
    }
}
