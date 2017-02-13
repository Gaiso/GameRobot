package com.gaiso.gamerobot.factory.shout.wendao;

import com.gaiso.gamerobot.action.shout.wendao.WenDaoCurrentShout;
import com.gaiso.gamerobot.action.shout.wendao.WenDaoGroupShout;
import com.gaiso.gamerobot.action.shout.wendao.WenDaoShout;
import com.gaiso.gamerobot.action.shout.wendao.WenDaoTeamShout;
import com.gaiso.gamerobot.action.shout.wendao.WenDaoWorldShout;
import com.gaiso.gamerobot.config.Config;

/**
 * Created by WangDongJie on 2016/11/18.
 */

public class WenDaoShoutFactory {

    public static WenDaoShout create(Config.Game game) {

        if (game instanceof Config.WenDao) {
            Config.WenDao wenDao = (Config.WenDao) game;
            WenDaoShout action;
            switch (wenDao.getChannel()) {
                case Config.WenDao.CHANNEL_CURRENT:
                    action = new WenDaoCurrentShout();
                    break;
                case Config.WenDao.CHANNEL_WORLD:
                    action = new WenDaoWorldShout();
                    break;
                case Config.WenDao.CHANNEL_GROUP:
                    action = new WenDaoGroupShout();
                    break;
                case Config.WenDao.CHANNEL_TEAM:
                    action = new WenDaoTeamShout();
                    break;
                default:
                    action = null;
                    break;
            }
            return action;
        }

        return null;
    }
}
