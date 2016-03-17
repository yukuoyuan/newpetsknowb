package com.petsknow.doctor.commonmodule.constant;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import com.easemob.chat.EMChat;
import com.petsknow.doctor.commonmodule.utils.IsRelease;

import org.xutils.x;

import java.util.Iterator;
import java.util.List;

/**
 * Created by yukuo on 2016/1/11.
 */
public class PetsknowDoctorApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //初始化xutils3.0
        x.Ext.init(this);
        //有第三方服务需要调用此方法(环信)
        initeasemobname();
    }

    private void initeasemobnemasecond() {
        EMChat.getInstance().init(context);
        EMChat.getInstance().setDebugMode(!IsRelease.IsRelease);
    }

    private void initeasemobname() {
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        if (processAppName == null || !processAppName.equalsIgnoreCase("com.petsknow.doctor")) {
            return;
        }
        //初始化环信
        initeasemobnemasecond();
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

}
