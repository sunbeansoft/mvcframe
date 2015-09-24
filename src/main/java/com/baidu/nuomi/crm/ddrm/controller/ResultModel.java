package com.baidu.nuomi.crm.ddrm.controller;

/**
 * Created by sunbeansoft on 15-9-24.
 */
public class ResultModel {

    private boolean success;
    private String memo;

    public ResultModel(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
