package com.gaiso.gamerobot.action.shout;

import com.gaiso.gamerobot.action.Action;
import com.gaiso.gamerobot.helper.ADBNormalHelper;
import com.gaiso.gamerobot.helper.SwitchIMEHelper;
import com.gaiso.gamerobot.task.shout.ShoutTask;

/**
 * Created by WangDongJie on 2016/11/17.
 */

public abstract class ShoutAction implements Action {

    private SwitchIMEHelper mImeHelper = SwitchIMEHelper.getHelper();
    private ShoutTask.ShoutText mShoutText;

    @Override
    public void action() {
        clickChannel();
        sleep();
        clickEdit();
        sleep();
        shout();
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setShoutText(ShoutTask.ShoutText shoutText) {
        mShoutText = shoutText;
    }

    protected abstract void clickChannel();

    protected abstract void clickEdit();

    protected void shout() {
        // TODO: 2016/11/17 make sure whether current IME is Custom ime
        if (mShoutText != null) {
            if (!mImeHelper.isCustomIme())
                mImeHelper.switch2Custom();
            ADBNormalHelper.text(mShoutText.getShout());
            sleep();
            ADBNormalHelper.enter();
        }
    }
}
