package com.gaiso.gamerobot.task;

/**
 * Created by WangDongJie on 2016/11/17.
 */

public interface Task {
    void run();
    void cancel();
    boolean isCancel();
}
