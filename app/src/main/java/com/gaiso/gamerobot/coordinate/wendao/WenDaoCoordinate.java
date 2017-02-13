package com.gaiso.gamerobot.coordinate.wendao;

import com.gaiso.gamerobot.coordinate.GameCoordinate;
import com.gaiso.gamerobot.helper.DimensionHelper;

/**
 * Created by WangDongJie on 2016/11/16.
 */

public class WenDaoCoordinate extends GameCoordinate {

    private static final int EDIT_TEXT_MARGIN_LEFT_DP = 42;
    private static final int EDIT_TEXT_MARGIN_TOP_DP = 16;
    private static final int EDIT_TEXT_HEIGHT_DP = 24;
    private static final int EDIT_TEXT_WIDTH_DP = 200;

    private static final int CHANNEL_FIRST_MARGIN_TOP_DP = 50;
    private static final int CHANNEL_MARGIN_TOP_DP = 4;
    private static final int CHANNEL_MARGIN_LEFT_DP = 4;
    private static final int CHANNEL_WIDTH_DP = 43;
    private static final int CHANNEL_HEIGHT_DP = 29;

    private float mRatio = 0.3f;

    private int[] mEdit, mCurrent, mWorld, mGroup, mTeam, mSystem, mRumor, mOther, mNotice;

    public int[] edit() {
        if (mEdit == null) {
            mEdit = new int[2];
            mEdit[0] = DimensionHelper.dp2px(EDIT_TEXT_MARGIN_LEFT_DP + EDIT_TEXT_WIDTH_DP * mRatio);
            mEdit[1] = DimensionHelper.dp2px(EDIT_TEXT_MARGIN_TOP_DP + EDIT_TEXT_HEIGHT_DP * mRatio);
        }
        return mEdit;
    }

    public int[] current() {
        if (mCurrent == null) {
            mCurrent = new int[2];
            initChannelItemCoordinate(mCurrent, 0);
        }
        return mCurrent;
    }

    public int[] world() {
        if (mWorld == null) {
            mWorld = new int[2];
            initChannelItemCoordinate(mWorld, 1);
        }
        return mWorld;
    }

    public int[] group() {
        if (mGroup == null) {
            mGroup = new int[2];
            initChannelItemCoordinate(mGroup, 2);
        }
        return mGroup;
    }

    public int[] team() {
        if (mTeam == null) {
            mTeam = new int[2];
            initChannelItemCoordinate(mTeam, 3);
        }
        return mTeam;
    }

    public int[] system() {
        if (mSystem == null) {
            mSystem = new int[2];
            initChannelItemCoordinate(mSystem, 4);
        }
        return mSystem;
    }

    public int[] rumor() {
        if (mRumor == null) {
            mRumor = new int[2];
            initChannelItemCoordinate(mRumor, 5);
        }
        return mRumor;
    }

    public int[] other() {
        if (mOther == null) {
            mOther = new int[2];
            initChannelItemCoordinate(mOther, 6);
        }
        return mOther;
    }

    public int[] notice() {
        if (mNotice == null) {
            mNotice = new int[2];
            initChannelItemCoordinate(mNotice, 7);
        }
        return mNotice;
    }

    private void initChannelItemCoordinate(int[] item, int position) {
        item[0] = DimensionHelper.dp2px(CHANNEL_MARGIN_LEFT_DP + CHANNEL_WIDTH_DP * mRatio);
        item[1] = DimensionHelper.dp2px(calculateItemHeight(position));
    }

    private float calculateItemHeight(int item) {
        return CHANNEL_FIRST_MARGIN_TOP_DP + CHANNEL_HEIGHT_DP * (item + mRatio) + CHANNEL_MARGIN_TOP_DP * item;
    }
}
