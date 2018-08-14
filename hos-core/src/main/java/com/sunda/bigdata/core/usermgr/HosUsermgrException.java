package com.sunda.bigdata.core.usermgr;

import com.sunda.bigdata.core.HosException;

/**
 * Created by 老蹄子 on 2018/8/11 下午5:54
 */
public class HosUsermgrException extends HosException{

    private int code;
    private String message;

    public HosUsermgrException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public HosUsermgrException(int code, String message) {
        super(message, null);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int errorCode() {
        return this.code;
    }
}
