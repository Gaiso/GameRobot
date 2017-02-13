package com.gaiso.gamerobot;

import android.app.Application;

import com.gaiso.gamerobot.holder.ContextHolder;

/**
 * Created by WangDongJie on 2016/11/18.
 */

public class RobotApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextHolder.init(this);
    }
}
