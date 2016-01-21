package com.petsknow.doctor.commonmodule.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.petsknow.doctor.R;


/**
 * Created by yukuo on 2016/1/11.
 * 这是一个发送通知的工具类
 */
public class NFUtils {

    private static int count;

    /**
     * 这是一个展示通知的方法
     *
     * @param context
     * @param title   通知的标题
     * @param text    通知的内容
     */
    public static void show(Context context, String title, String text) {
        count++;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        //设置图标
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //设置标题
        builder.setContentTitle(title);
        //内容
        builder.setContentText(text);
        Notification notification = builder.build();
        //设置声音
        notification.defaults = Notification.DEFAULT_SOUND;
        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.notify(count, notification);
    }

}
