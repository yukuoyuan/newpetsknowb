package com.petsknow.doctor.mainmodule.bean;

import java.util.List;

/**
 * Created by yukuo on 2016/3/21.
 */
public class PetTypeBean {
    private List<datas> data ;

    private String msg;

    private int status;

    private   long time;

    public void setData(List<datas> data){
        this.data = data;
    }
    public List<datas> getData(){
        return this.data;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setTime(long time){
        this.time = time;
    }
    public long getTime(){
        return this.time;
    }
    public class datas {
        private int id;

        private String keyName;

        private String name;

        private int species;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setKeyName(String keyName){
            this.keyName = keyName;
        }
        public String getKeyName(){
            return this.keyName;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setSpecies(int species){
            this.species = species;
        }
        public int getSpecies(){
            return this.species;
        }

    }
}
