package com.petsknow.doctor.usermodule.manger;

import android.content.Context;

import com.petsknow.doctor.commonmodule.constant.Constant;
import com.petsknow.doctor.commonmodule.constant.MyApplication;
import com.petsknow.doctor.commonmodule.utils.SPUtil;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个用户信息的管理类
 */
public class UserManger {
    public static Context context = MyApplication.context;

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
     * 获取一个用户头像的方法
     *
     * @return
     */
    public static String getUSerAvator() {
        return SPUtil.getString(context, Constant.USERAVATOR, "");
    }
}