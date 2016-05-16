package org.popkit.core.entity;

/**
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-05-16:11:47
 */
public class SimpleResult {
    private boolean success;
    private String info;

    public SimpleResult() {
        this.success = false;
        this.info = "";
    }

    public SimpleResult(boolean success, String info) {
        this.success = success;
        this.info = info;
    }

    public void update(boolean status, String info) {
        this.success = status;
        this.info = info;
    }

    public static SimpleResult fail(String info) {
        return new SimpleResult(false, info);
    }

    public static SimpleResult success(String info) {
        return new SimpleResult(true, info);
    }
    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
