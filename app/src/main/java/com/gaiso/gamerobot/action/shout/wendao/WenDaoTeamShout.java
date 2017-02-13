package com.gaiso.gamerobot.action.shout.wendao;

import com.gaiso.gamerobot.helper.ADBNormalHelper;

/**
 * Created by WangDongJie on 2016/11/21.
 */

public class WenDaoTeamShout extends WenDaoShout {
    @Override
    protected void clickChannel() {
        ADBNormalHelper.click(mCoordinate.team());
    }
}
