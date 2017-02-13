package com.gaiso.gamerobot.view.widget;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.gaiso.gamerobot.R;
import com.gaiso.gamerobot.helper.DimensionHelper;
import com.gaiso.gamerobot.helper.DirectionHandler;
import com.gaiso.gamerobot.helper.Porter;

/**
 * Created by WangDongJie on 2016/11/21.
 */

public class FloatMenu implements Porter.OnMoveListener {

    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    private Context mContext;
    private Icon mIcon;
    private Porter mPorter;
    private WindowManager.LayoutParams mLayoutParams;
    private OnMenuClickListener mMenuClickListener;
    private DirectionHandler mDirectionHandler = DirectionHandler.getInstance();
    private int mPortraitWidth, mLandscapeWidth;

    public FloatMenu(Context context) {
        mContext = context;
        init();
    }

    public interface OnMenuClickListener {
        void onMenuClick();
    }

    public void setOrientation(int orientation) {
        if (orientation == VERTICAL)
            mDirectionHandler.setWidth(mPortraitWidth);
        else
            mDirectionHandler.setWidth(mLandscapeWidth);
    }

    public void setOnMenuClickListener(OnMenuClickListener listener) {
        mMenuClickListener = listener;
    }

    private void init() {
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        mPortraitWidth = dm.widthPixels;
        mLandscapeWidth = dm.heightPixels;

        mLayoutParams = getDefaultParams(0, 0);

        mDirectionHandler.setWidth(mLandscapeWidth);
        mPorter = Porter.getPorter();
        mPorter.init(mContext, manager);

        mIcon = new Icon(mContext);
        mPorter.port(mIcon.getIcon());
        mPorter.setOnMoveListener(this);
    }

    public void show() {
        if (!mPorter.hasTarget(mIcon.getIcon())) {
            mPorter.addTarget(mIcon.getIcon(), mLayoutParams);
        }
        mIcon.showIcon();
    }

    public void hide() {
        mIcon.hideIcon();
    }

    public void destroy() {
        if (mPorter.hasTarget(mIcon.getIcon()))
            mPorter.removeTarget(mIcon.getIcon());
    }

    private WindowManager.LayoutParams getDefaultParams(int x, int y) {

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.format = PixelFormat.RGBA_8888;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = x;
        params.y = y;
        params.width = DimensionHelper.dp2px(40);
        params.height = DimensionHelper.dp2px(40);

        return params;
    }

    @Override
    public void onMoveStart(View view, int x, int y) {
    }

    @Override
    public void onMoving(View view, int x, int y) {
    }

    @Override
    public void onMoveEnd(View view, int x, int y, boolean sideChange, boolean justAClick) {
        if (justAClick && mMenuClickListener != null) {
            mMenuClickListener.onMenuClick();
        }
    }



    private class Icon {

        private View mVIcon;

        public Icon(Context context) {
            mVIcon = View.inflate(context, R.layout.icon, null);
        }

        public View getIcon() {
            return mVIcon;
        }

        public void hideIcon() {
            mVIcon.setVisibility(View.GONE);
        }

        public void showIcon() {
            mVIcon.setVisibility(View.VISIBLE);
        }
    }
}
