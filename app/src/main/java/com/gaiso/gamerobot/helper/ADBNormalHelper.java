package com.gaiso.gamerobot.helper;

import com.gaiso.gamerobot.constants.CMD;

/**
 * Created by WangDongJie on 2016/11/17.
 */

public class ADBNormalHelper {

    public static void click(int[] coordinate) {
        ADBShell.exec(CMD.Generator.tap(coordinate[0], coordinate[1]));
    }

    public static void text(String text) {
        ADBShell.exec(CMD.Generator.text(text));
    }

    public static void enter() {
        ADBShell.exec(CMD.Generator.imeCode(2));
    }

}
