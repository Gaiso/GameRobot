package com.gaiso.gamerobot.factory.shout;

import com.gaiso.gamerobot.action.shout.ShoutAction;
import com.gaiso.gamerobot.config.Config;
import com.gaiso.gamerobot.factory.ActionFactory;
import com.gaiso.gamerobot.factory.shout.wendao.WenDaoShoutFactory;

/**
 * Created by WangDongJie on 2016/11/18.
 */

public class ShoutFactory implements ActionFactory {

    @Override
    public ShoutAction create(Config.Game game) {
        return WenDaoShoutFactory.create(game);
    }
}
