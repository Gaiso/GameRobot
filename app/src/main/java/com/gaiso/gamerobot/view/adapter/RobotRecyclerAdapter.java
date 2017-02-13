package com.gaiso.gamerobot.view.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by WangDongJie on 2016/11/18.
 */

public abstract class RobotRecyclerAdapter extends RecyclerView.Adapter {

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }
}
