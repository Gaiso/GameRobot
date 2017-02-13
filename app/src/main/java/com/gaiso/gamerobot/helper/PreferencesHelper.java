package com.gaiso.gamerobot.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.gaiso.gamerobot.constants.PreferencesKey;
import com.gaiso.gamerobot.holder.ContextHolder;

/**
 * Created by WangDongJie on 2016/11/21.
 */

public class PreferencesHelper {

    private static Context mContext = ContextHolder.getContext();

    public static boolean write(String key, String value) {
        SharedPreferences preferences = mContext.getSharedPreferences(PreferencesKey.FILE_NAME, Context
                .MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
        return true;
    }

    public static String read(String key) {
        SharedPreferences preferences = mContext.getSharedPreferences(PreferencesKey.FILE_NAME, Context
                .MODE_PRIVATE);
        return preferences.getString(key, "");
    }
}
