package com.petsknow.doctor.usermodule.bean;

import java.util.List;

/**
 * Created by yukuo on 2016/3/16.
 */
public class UpdatePasswordVcodeBean {

    /**
     * data : ["163200"]
     * msg : 发送验证码成功
     * status : 0
     * time : 1458125723031
     */

    private String msg;
    private int status;
    private long time;
    private List<String> data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setData(List<String> data) {
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

    public List<String> getData() {
        return data;
    }
}
