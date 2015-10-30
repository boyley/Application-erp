package com.github.application.erp.controller.web;

/**
 * Created by lenovo on 2015/10/18.
 */
public class Api<T> {

    private boolean success = false;
    private String msg = "";
    private int code;
    private T data;

    public Api() {
    }

    public Api(boolean success) {
        this.success = success;
    }

    public Api(boolean success, int code) {
        this.success = success;
        this.code = code;
    }

    public Api(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public Api(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public Api(boolean success, int code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public Api(boolean success,int code, T data) {
        this.code = code;
        this.data = data;
        this.success = success;
    }

    public Api(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Api(boolean success, int code, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
