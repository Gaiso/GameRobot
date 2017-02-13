package com.gaiso.gamerobot.holder;

import android.content.Context;

/**
 * Created by WangDongJie on 2016/11/18.
 */

public class ContextHolder {

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }
}
