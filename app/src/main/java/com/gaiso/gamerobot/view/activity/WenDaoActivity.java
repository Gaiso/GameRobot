package com.gaiso.gamerobot.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gaiso.gamerobot.R;
import com.gaiso.gamerobot.Robot;
import com.gaiso.gamerobot.RobotActivity;
import com.gaiso.gamerobot.bean.ShoutBean;
import com.gaiso.gamerobot.config.Config;
import com.gaiso.gamerobot.constants.PreferencesKey;
import com.gaiso.gamerobot.helper.PreferencesHelper;
import com.gaiso.gamerobot.task.shout.ShoutTask;
import com.gaiso.gamerobot.view.adapter.DialogRecyclerAdapter;
import com.gaiso.gamerobot.view.widget.FloatMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WangDongJie on 2016/11/18.
 */

public class WenDaoActivity extends RobotActivity implements DialogRecyclerAdapter.OnDialogClickListener, View
        .OnClickListener {

    private MaterialDialog mChannelDialog;
    private Config.WenDao mWenDao;
    private TextView mTvChannel;
    private EditText mEtShoutOne, mEtShoutTwo, mEtShoutThree, mEtShoutInterval;
    private CheckBox mCbShoutOne, mCbShoutTwo, mCbShoutThree;
    private List<String> mShout = new ArrayList<>(3);
    private FloatMenu mFloatMenu = MainActivity.mFloatMenu;
    private Robot mRobot = Robot.getRobot();
    private DialogRecyclerAdapter mAdapter;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wendao);
        mWenDao = (Config.WenDao) MainActivity.mGame;
        init();
    }

    private void init() {
        mAdapter = new DialogRecyclerAdapter(this, R.array.wen_dao_channel_list);
        mManager = new LinearLayoutManager(this);
        mAdapter.setOnItemClickListener(this);

        mFloatMenu.setOrientation(FloatMenu.HORIZONTAL);
        mFloatMenu.setOnMenuClickListener(new FloatMenu.OnMenuClickListener() {
            @Override
            public void onMenuClick() {
                if (mRobot.isRunning())
                    mRobot.shutDown();
                mFloatMenu.hide();
                Toast.makeText(WenDaoActivity.this, R.string.stop_robot, Toast.LENGTH_SHORT).show();
            }
        });

        mTvChannel = (TextView) findViewById(R.id.tv_channel);
        mEtShoutOne = (EditText) findViewById(R.id.et_shout_one);
        mEtShoutTwo = (EditText) findViewById(R.id.et_shout_two);
        mEtShoutThree = (EditText) findViewById(R.id.et_shout_three);
        mEtShoutInterval = (EditText) findViewById(R.id.et_shout_interval);
        mCbShoutOne = (CheckBox) findViewById(R.id.cb_shout_one);
        mCbShoutTwo = (CheckBox) findViewById(R.id.cb_shout_two);
        mCbShoutThree = (CheckBox) findViewById(R.id.cb_shout_three);

        mTvChannel.setOnClickListener(this);
        findViewById(R.id.btn_start).setOnClickListener(this);

        initShoutView();
    }

    private void initShoutView() {
        String json = PreferencesHelper.read(PreferencesKey.WEN_DAO_SHOUT);
        if ("".equals(json))
            return;
        ShoutBean sb = ShoutBean.fromJson(json);
        mTvChannel.setText(sb.getChannel());
        mEtShoutInterval.setText(sb.getInterval());
        mEtShoutOne.setText(sb.getShout().get(0));
        mEtShoutTwo.setText(sb.getShout().get(1));
        mEtShoutThree.setText(sb.getShout().get(2));
        mCbShoutOne.setChecked(sb.getCheck().get(0));
        mCbShoutTwo.setChecked(sb.getCheck().get(1));
        mCbShoutThree.setChecked(sb.getCheck().get(2));
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, WenDaoActivity.class);
        context.startActivity(intent);
    }

    private void getShout() {
        mShout.clear();
        String shout;
        if (mCbShoutOne.isChecked() && !TextUtils.isEmpty(shout = mEtShoutOne.getText().toString()))
            mShout.add(shout);

        if (mCbShoutTwo.isChecked() && !TextUtils.isEmpty(shout = mEtShoutTwo.getText().toString()))
            mShout.add(shout);

        if (mCbShoutThree.isChecked() && !TextUtils.isEmpty(shout = mEtShoutThree.getText().toString()))
            mShout.add(shout);
    }

    @Override
    public void onDialogClick(CharSequence channel) {
        mWenDao.setChannel(channel.toString());
        mTvChannel.setText(channel);
        mChannelDialog.dismiss();
    }

    private void saveShout() {
        ShoutBean sb = new ShoutBean();
        sb.setChannel(mTvChannel.getText().toString());
        sb.setInterval(mEtShoutInterval.getText().toString());
        List<String> shout = new ArrayList<>(3);
        shout.add(mEtShoutOne.getText().toString());
        shout.add(mEtShoutTwo.getText().toString());
        shout.add(mEtShoutThree.getText().toString());
        sb.setShout(shout);
        List<Boolean> check = new ArrayList<>(3);
        check.add(mCbShoutOne.isChecked());
        check.add(mCbShoutTwo.isChecked());
        check.add(mCbShoutThree.isChecked());
        sb.setCheck(check);
        PreferencesHelper.write(PreferencesKey.WEN_DAO_SHOUT, sb.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_channel:
                if (mChannelDialog == null) {
                    mChannelDialog = new MaterialDialog.Builder(this)
                            .adapter(mAdapter, mManager).build();
                }
                mChannelDialog.show();
                break;
            case R.id.btn_start:
                if (Robot.getRobot().isRunning())
                    return;
                getShout();
                if (mShout.size() > 0) {
                    ShoutTask task = new ShoutTask();
                    String interval = mEtShoutInterval.getText().toString();
                    if (!TextUtils.isEmpty(interval))
                        task.setInterval(Integer.valueOf(interval));
                    task.setShoutText(mShout);
                    mRobot.insertTask(task);
                    mRobot.startUp();
                    mFloatMenu.show();
                    saveShout();
                    Toast.makeText(this, R.string.start_robot, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
