package com.houserenting.utils;

import java.util.HashMap;

public class MsgMap extends HashMap<String, Object> {

    private final static int SUCCESS = 0;
    private final static int FAILED = -1;

    public void putSuccessCode() {
        put("code", SUCCESS);
    }

    public void putFailedCode(String msg) {
        put("code", FAILED);
        put("msg", msg);
    }

    public void failForLackOfParam(){
        putFailedCode("参数不全");
    }

    public void failForLimit(){
        putFailedCode("limit 错误");
    }

}
