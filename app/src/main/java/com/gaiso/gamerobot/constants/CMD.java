package com.gaiso.gamerobot.constants;

/**
 * Created by WangDongJie on 2016/11/17.
 */

public interface CMD {

    /**
     * adb shell am broadcast -a ADB_INPUT_TEXT --es msg '你好嗎? Hello?'
     * adb shell am broadcast -a ADB_EDITOR_CODE --ei code 2
     * adb shell ime set com.gaiso.gamerobot.ime/.RobotIME
     * adb shell ime list -s
     * adb shell input tap 0 0
     */

    String IME_MESSAGE = "ADB_INPUT_TEXT";
    String IME_KEYCODE = "ADB_INPUT_CODE";
    String IME_EDITOR_CODE = "ADB_EDITOR_CODE";

    String IME_MESSAGE_KEY = "msg";
    String IME_CODE_KEY = "code";

    class Generator {

        public static String text(String text) {
            return "am broadcast -a " + IME_MESSAGE + " --es " + IME_MESSAGE_KEY + " '" + text + "'";
        }

        public static String keyCode(int code) {
            return "am broadcast -a " + IME_KEYCODE + " --ei " + IME_CODE_KEY + " " + code;
        }

        public static String imeCode(int code) {
            return "am broadcast -a " + IME_EDITOR_CODE + " --ei " + IME_CODE_KEY + " " + code;
        }

        public static String tap(int x, int y) {
            return "input tap " + x + " " + y;
        }

        public static String listIme() {
            return "ime list -s";
        }

        public static String switchIme(String name) {
            return "ime set " + name;
        }

        public static String switchIme() {
            return "ime set com.gaiso.gamerobot/.ime.RobotIME";
        }
    }
}
