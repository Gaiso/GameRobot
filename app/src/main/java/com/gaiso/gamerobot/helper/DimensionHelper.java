package com.gaiso.gamerobot.helper;

import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.gaiso.gamerobot.holder.ContextHolder;

/**
 * Created by WangDongJie on 2016/11/21.
 */

public class DimensionHelper {

    private static DisplayMetrics mMetrics;

    static {
        mMetrics = ContextHolder.getContext().getResources().getDisplayMetrics();
    }

    public static int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mMetrics);
    }
}
