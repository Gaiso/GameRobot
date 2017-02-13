package com.gaiso.gamerobot.factory;

import com.gaiso.gamerobot.action.Action;
import com.gaiso.gamerobot.config.Config;

/**
 * Created by WangDongJie on 2016/11/18.
 */

public interface ActionFactory {
    Action create(Config.Game game);
}
