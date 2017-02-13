package com.gaiso.gamerobot.task.shout;

import com.gaiso.gamerobot.view.activity.MainActivity;
import com.gaiso.gamerobot.action.shout.ShoutAction;
import com.gaiso.gamerobot.factory.shout.ShoutFactory;
import com.gaiso.gamerobot.rx.subscriber.RobotSubscribe;
import com.gaiso.gamerobot.task.Task;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;

/**
 * Created by WangDongJie on 2016/11/17.
 */

public class ShoutTask implements Task {

    private int mInterval = 10;//默认10秒
    private ShoutAction mAction;
    private Subscription mSubscription;
    private ShoutText mShoutText;

    public void setInterval(int interval) {
        mInterval = interval;
    }

    public void setShoutText(List<String> shout) {
        if (shout != null && shout.size() != 0) {
            mShoutText = new ShoutText(shout);
            mAction.setShoutText(mShoutText);
        }
    }

    public ShoutTask() {
        ShoutFactory factory = new ShoutFactory();
        mAction = factory.create(MainActivity.mGame);
    }

    @Override
    public void run() {
        mSubscription = Observable.interval(3, mInterval, TimeUnit.SECONDS)
                .subscribe(new RobotSubscribe<Long>() {

                    @Override
                    public void onNext(Long l) {
                        super.onNext(l);
                        mAction.action();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void cancel() {
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }

    @Override
    public boolean isCancel() {
        return mSubscription == null || mSubscription.isUnsubscribed();
    }

    public static class ShoutText {

        private List<String> mShout;

        private int mCurrent;

        private int mSize;

        public ShoutText(List<String> shout) {
            if (shout != null && shout.size() != 0) {
                mShout = shout;
                mSize = mShout.size();
                convert();
            }
        }

        private void convert() {
            for (int i = 0; i < mSize; i++) {
                String shout = mShout.get(i);
                String unicode = StringEscapeUtils.escapeJava(shout);
                mShout.set(i, unicode);
            }
        }

        public String getShout() {
            String shout = mShout.get(mCurrent);
            updateCursor();
            return shout;
        }

        private void updateCursor() {
            if (mCurrent < mSize - 1) {
                mCurrent++;
            } else {
                mCurrent = 0;
            }
        }
    }
}
