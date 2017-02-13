package com.gaiso.gamerobot.helper;

import com.gaiso.gamerobot.constants.CMD;

import java.util.Iterator;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by WangDongJie on 2016/11/17.
 */

public class SwitchIMEHelper {

    private static SwitchIMEHelper INSTANCE = new SwitchIMEHelper();
    private boolean mIsCustomIme;
    private String mDefaultIme;

    private SwitchIMEHelper() {
        // TODO: 2016/11/18 list all ime on device and get the default one
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                List<String> imeList = ADBShell.exec(CMD.Generator.listIme());
                mDefaultIme = getDefault(imeList);
            }
        }).subscribeOn(Schedulers.computation()).subscribe();
    }

    private String getDefault(List<String> imeList) {
        Iterator<String> iterator = imeList.iterator();
        while (iterator.hasNext()) {
            String ime = iterator.next();
            if (!ime.contains("com.gaiso.gamerobot"))
                return ime;
        }
        return "";
    }

    public static SwitchIMEHelper getHelper() {
        return INSTANCE;
    }

    public boolean isCustomIme() {
        return mIsCustomIme;
    }

    public void switch2Custom() {
        mIsCustomIme = true;
        ADBShell.exec(CMD.Generator.switchIme());
    }

    public void switch2Default() {
        // TODO: 2016/11/18 switch to default ime
        mIsCustomIme = false;
        ADBShell.exec(CMD.Generator.switchIme(mDefaultIme));
    }
}
