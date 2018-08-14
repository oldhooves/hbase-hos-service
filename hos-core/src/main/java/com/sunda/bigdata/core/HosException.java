package com.sunda.bigdata.core;

/**
 * Created by 老蹄子 on 2018/8/11 上午11:13
 */
public abstract class HosException extends RuntimeException {

    protected String errorMessage;

    public HosException(String errorMessage,Throwable cause){
        super(cause);
        this.errorMessage = errorMessage;
    }

    public abstract int errorCode();

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
