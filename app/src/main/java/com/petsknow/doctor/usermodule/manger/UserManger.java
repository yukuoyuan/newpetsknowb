package com.petsknow.doctor.usermodule.manger;

import android.content.Context;

import com.petsknow.doctor.commonmodule.constant.Constant;
import com.petsknow.doctor.commonmodule.constant.PetsknowDoctorApplication;
import com.petsknow.doctor.commonmodule.utils.SPUtil;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个用户信息的管理类
 */
public class UserManger {
    public static Context context = PetsknowDoctorApplication.context;

    /**
     * 存储用户id的方法
     *
     * @param id
     */
    public static void saveUserId(int id) {
        SPUtil.putInt(context, Constant.USERID, id);
    }

    /**
     * 获得一个用户id的方法
     *
     * @return
     */
    public static int getUserId() {
        return SPUtil.getInt(context, Constant.USERID, 0);
    }

    /**
     * 这是一个获取用户电话的方法
     *
     * @return
     */
    public static String getUserPhone() {
        return SPUtil.getString(context, Constant.USERPHONE, "");
    }

    /**
     * 这是一个存储用户电话的方法
     *
     * @param phone
     */
    public static void saveUserPhone(String phone) {
        SPUtil.putString(context, Constant.USERPHONE, phone);
    }

    /**
     * 这是一个存储用户环信账号名字的方法
     *
     * @param easemobname
     */
    public static void saveUserEaseMobName(String easemobname) {
        SPUtil.putString(context, Constant.USEREASEMOBNAME, easemobname);
    }

    /**
     * 这是一个获取环信账号名字的方法
     *
     * @return
     */
    public static String getUserEaseMobName() {
        return SPUtil.getString(context, Constant.USEREASEMOBNAME, "");
    }

    /**
     * 这是一个保存用户头像的方法
     *
     * @param url
     */
    public static void saveUserAvaturl(String url) {
        SPUtil.putString(context, Constant.USERAVATOR, url);
    }

    /**
     * 这是一个保存用户真是名字的方法
     *
     * @param name
     */
    public static void saveUserTrueName(String name) {
        SPUtil.putString(context, Constant.USERTRUENAME, name);
    }

    /**
     * 这是一个获取用户真实名字的方法
     *
     * @return
     */
    public static String getUserTrueName() {
        return SPUtil.getString(context, Constant.USERTRUENAME, "");
    }

    /**
     * 获取一个用户头像的方法
     *
     * @return
     */
    public static String getUSerAvator() {
        return SPUtil.getString(context, Constant.USERAVATOR, "");
    }

    /**
     * 这是一个判断用户是否登录的方法
     *
     * @return
     */
    public static boolean islogin() {
        boolean flag = SPUtil.getBoolean(context, Constant.ISLOGIN, false);
        return flag;
    }

    /**
     * 这是一个存储用户登录状态的方法
     *
     * @param flag
     */
    public static void setLogin(boolean flag) {
        SPUtil.putBoolean(context, Constant.ISLOGIN, flag);
    }
}
