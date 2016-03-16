package com.petsknow.doctor.sessionmodule.bean;

import java.util.List;

/**
 * Created by yukuo on 2016/3/16.
 */
public class Sendmedicalbean {

    /**
     * data : [{"id":555}]
     * msg : success
     * status : 0
     * time : 1458107646758
     */

    private String msg;
    private int status;
    private long time;
    /**
     * id : 555
     */

    private List<DataEntity> data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setData(List<DataEntity> data) {
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

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private int id;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
