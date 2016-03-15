package com.petsknow.doctor.commonmodule.bean;

import java.util.List;

/**
 * Created by yukuo on 2016/3/14.
 */
public class CommonBean {

    /**
     * data : []
     * msg : success
     * status : 0
     * time : 1458008185752
     */

    private String msg;
    private int status;
    private long time;
    private List<?> data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public int getStatus() {
        return status;
    }

    public long getTime() {
        return time;
    }

    public List<?> getData() {
        return data;
    }
}
