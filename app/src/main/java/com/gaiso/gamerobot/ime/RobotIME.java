package com.gaiso.gamerobot.ime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.inputmethodservice.InputMethodService;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

import com.gaiso.gamerobot.R;
import com.gaiso.gamerobot.constants.CMD;

import org.apache.commons.lang3.StringEscapeUtils;


public class RobotIME extends InputMethodService {

    private BroadcastReceiver mReceiver = null;

    @Override
    public View onCreateInputView() {

        if (mReceiver == null) {
            IntentFilter filter = new IntentFilter(CMD.IME_MESSAGE);
            filter.addAction(CMD.IME_KEYCODE);
            filter.addAction(CMD.IME_EDITOR_CODE);
            mReceiver = new IMEReceiver();
            registerReceiver(mReceiver, filter);
        }

        return null;
    }

    public void onDestroy() {
        if (mReceiver != null)
            unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    class IMEReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(CMD.IME_MESSAGE)) {
                String msg = intent.getStringExtra(CMD.IME_MESSAGE_KEY);
                String text = StringEscapeUtils.unescapeJava(msg.replace("u\\", "\\"));
                if (!TextUtils.isEmpty(text)) {
                    InputConnection ic = getCurrentInputConnection();
                    if (ic != null)
                        ic.commitText(text, 1);
                }
            }

            if (intent.getAction().equals(CMD.IME_KEYCODE)) {
                int code = intent.getIntExtra(CMD.IME_CODE_KEY, -1);
                if (code != -1) {
                    InputConnection ic = getCurrentInputConnection();
                    if (ic != null)
                        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, code));
                }
            }

            if (intent.getAction().equals(CMD.IME_EDITOR_CODE)) {
                int code = intent.getIntExtra(CMD.IME_CODE_KEY, -1);
                if (code != -1) {
                    InputConnection ic = getCurrentInputConnection();
                    if (ic != null)
                        ic.performEditorAction(code);
                }
            }
        }
    }
}
