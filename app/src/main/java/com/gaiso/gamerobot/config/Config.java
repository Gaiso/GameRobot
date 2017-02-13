package com.gaiso.gamerobot.config;

/**
 * Created by WangDongJie on 2016/11/18.
 */

public interface Config {

    interface Game {
    }

    class WenDao implements Game {

        public static final String CHANNEL_CURRENT = "当前";
        public static final String CHANNEL_WORLD = "世界";
        public static final String CHANNEL_GROUP = "帮派";
        public static final String CHANNEL_TEAM = "组队";

        private String mChannel = CHANNEL_WORLD;

        public String getChannel() {
            return mChannel;
        }

        public void setChannel(String channel) {
            mChannel = channel;
        }
    }


}
