package com.gaiso.gamerobot;

import com.gaiso.gamerobot.helper.SwitchIMEHelper;
import com.gaiso.gamerobot.rx.subscriber.RobotSubscribe;
import com.gaiso.gamerobot.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by WangDongJie on 2016/11/17.
 */

public class Robot {

    private static Robot INSTANCE = new Robot();
    private TaskHeap mHeap = new TaskHeap();
    private SwitchIMEHelper mImeHelper = SwitchIMEHelper.getHelper();
    private int mInterval = 24 * 60 * 60;//默认24小时执行下一个任务
    private boolean mIsRunning;
    private CompositeSubscription mSubscription = new CompositeSubscription();

    private Robot() {
    }

    public static Robot getRobot() {
        return INSTANCE;
    }

    private class TaskHeap {

        private int mCurrent;

        private int mSize;

        private List<Task> mTasks = new ArrayList<>();

        public void insertTask(Task task) {
            if (task != null) {
                mSize++;
                mTasks.add(task);
            }
        }

        public Task getPreviousTask() {
            if (mSize == 0)
                return null;
            int pos = mCurrent - 1;
            if (pos < 0)
                return null;
            return mTasks.get(pos);
        }

        public Task getNextTask() {
            if (mSize == 0)
                return null;
            Task task = mTasks.get(mCurrent);
            updateCursor();
            return task;
        }

        private void updateCursor() {
            if (mCurrent < mSize - 1) {
                mCurrent++;
            } else {
                mCurrent = 0;//循环执行task
            }
        }

        public void clear() {
            cancelAllTask();
            mTasks.clear();
            mSize = 0;
        }

        private void cancelAllTask() {
            for (Task task : mTasks) {
                if (!task.isCancel())
                    task.cancel();
            }
        }
    }

    public void setInterval(int interval) {
        mInterval = interval;
    }

    public void insertTask(Task task) {
        mHeap.insertTask(task);
    }

    public void startUp() {
        mIsRunning = true;
        rxSchedule();
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    private void rxSchedule() {
        Subscription subscription = Observable.interval(0, mInterval, TimeUnit.SECONDS)
                .subscribe(new RobotSubscribe<Long>() {

                    @Override
                    public void onNext(Long aLong) {
                        super.onNext(aLong);
                        Task previous = mHeap.getPreviousTask();
                        if (previous != null && !previous.isCancel()) {
                            previous.cancel();
                        }
                        Task next = mHeap.getNextTask();
                        if (next != null) {
                            next.run();
                        }
                    }
                });
        mSubscription.add(subscription);
    }

    public void shutDown() {
        mIsRunning = false;
        mSubscription.clear();
        mHeap.clear();
        if (mImeHelper.isCustomIme())
            mImeHelper.switch2Default();
    }
}
