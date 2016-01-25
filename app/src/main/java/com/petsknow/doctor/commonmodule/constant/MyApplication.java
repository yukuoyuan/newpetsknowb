package com.petsknow.doctor.commonmodule.constant;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import com.easemob.chat.EMChat;
import com.easemob.easeui.controller.EaseUI;
import com.petsknow.doctor.commonmodule.utils.SPUtil;

import org.xutils.x;

import java.util.Iterator;
import java.util.List;

/**
 * Created by yukuo on 2016/1/11.
 */
public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //初始化xutils3.0
        x.Ext.init(this);
        initeasemobname();
        initeasemobnemasecond();
        //初始化环信界面
        EaseUI.getInstance().init(context);
    }

    private void initeasemobnemasecond() {
        EMChat.getInstance().init(context);
    }

    private void initeasemobname() {
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        if (processAppName == null || !processAppName.equalsIgnoreCase("com.easemob.chatuidemo")) {
            return;
        }
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
            }
        }
        return processName;
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
