package com.gaiso.gamerobot.action.shout.wendao;

import com.gaiso.gamerobot.action.shout.ShoutAction;
import com.gaiso.gamerobot.coordinate.wendao.WenDaoCoordinate;
import com.gaiso.gamerobot.helper.ADBNormalHelper;

/**
 * Created by WangDongJie on 2016/11/17.
 */

public abstract class WenDaoShout extends ShoutAction {

    protected WenDaoCoordinate mCoordinate;

    public WenDaoShout() {
        mCoordinate = new WenDaoCoordinate();
    }

    @Override
    protected abstract void clickChannel();

    @Override
    protected void clickEdit() {
        ADBNormalHelper.click(mCoordinate.edit());
    }
}
