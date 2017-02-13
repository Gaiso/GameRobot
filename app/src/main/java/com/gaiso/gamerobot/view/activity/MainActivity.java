package com.gaiso.gamerobot.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gaiso.gamerobot.R;
import com.gaiso.gamerobot.RobotActivity;
import com.gaiso.gamerobot.config.Config;
import com.gaiso.gamerobot.helper.RootVerify;
import com.gaiso.gamerobot.view.widget.FloatMenu;

public class MainActivity extends RobotActivity implements View.OnClickListener {

    public static Config.Game mGame;
    public static FloatMenu mFloatMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_wen_dao).setOnClickListener(this);
        mFloatMenu = new FloatMenu(this);
    }

    @Override
    public void onClick(View v) {
        if (!RootVerify.isRoot()) {
            Toast.makeText(this, R.string.no_root_tips, Toast.LENGTH_SHORT).show();
            return;
        }
        switch (v.getId()) {
            case R.id.tv_wen_dao:
                mGame = new Config.WenDao();
                WenDaoActivity.start(this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFloatMenu.destroy();
    }
}
