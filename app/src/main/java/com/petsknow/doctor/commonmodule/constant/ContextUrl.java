package com.petsknow.doctor.commonmodule.constant;

import com.petsknow.doctor.commonmodule.utils.IsRelease;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个管理线上线下版本请求网络路径的管理类
 */
public class ContextUrl {

    //测试地址
    // public static String BaseUrl = "http://101.200.1.168:8080/pets_know_api";
    //正式地址
    //  public static String BaseUrl = "http://182.92.31.72/pets_know_api";
    public static String BaseUrl() {
        if (IsRelease.IsRelease) {
            //正式地址
            return "http://182.92.31.72/pets_know_api";
        } else {
            //测试地址
            return "http://101.200.1.168:8080/pets_know_api";
        }
    }

    //七牛地址
    public static String qiniu = "http://7xk4ye.com2.z0.glb.qiniucdn.com/";
    //登录
    public static String login = "/doctor/open/doctor/login";
    //所有会话列表
    public static String getallseesionlist = "/inquiry/find_inquiry_bill_by_doctor_id";
    //我的患者列表
    public static String Mypatient = "/inquiry/find_illness_case_by_doctor_id";
    //所有待珍列表的方法
    public static String getwatingpatient = "/inquiry/find_un_dispense_inquiry_bill";
    //获取问诊详情
    public static String getwatingdetial = "/inquiry/find_inquiry_bill_by_id";
    //接诊
    public static String reception = "/inquiry/confirm_inquiry_bill";
    //结束当前会话
    public static String oversession = "/inquiry/finish_inquiry_bill";
}
