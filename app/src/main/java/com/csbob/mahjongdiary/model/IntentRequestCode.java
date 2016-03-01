package com.csbob.mahjongdiary.model;

/**
 * Used by adding intent extras
 * <p/>
 * Created by yiyang on 01/03/2016.
 */
public enum IntentRequestCode {
    NEW_GAME(1),
    EDIT_GAME(2);

    private int code;

    IntentRequestCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
