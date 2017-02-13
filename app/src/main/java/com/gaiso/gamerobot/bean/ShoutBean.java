package com.gaiso.gamerobot.bean;

import com.gaiso.gamerobot.helper.GsonHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by WangDongJie on 2016/11/21.
 */

public class ShoutBean {

    private String channel;
    private String interval;
    private List<String> shout;
    private List<Boolean> check;

    public static ShoutBean fromJson(String json) {
        ShoutBean sb = GsonHelper.fromJson(json, ShoutBean.class);
        return sb;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public List<String> getShout() {
        return shout;
    }

    public void setShout(List<String> shout) {
        this.shout = shout;
    }

    public List<Boolean> getCheck() {
        return check;
    }

    public void setCheck(List<Boolean> check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return GsonHelper.toJson(this);
    }
}
