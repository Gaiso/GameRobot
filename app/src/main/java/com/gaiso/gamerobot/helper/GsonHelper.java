package com.gaiso.gamerobot.helper;

import com.google.gson.Gson;

/**
 * Created by WangDongJie on 2016/11/21.
 */

public class GsonHelper {

    private static final Gson gson = new Gson();

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }
}
