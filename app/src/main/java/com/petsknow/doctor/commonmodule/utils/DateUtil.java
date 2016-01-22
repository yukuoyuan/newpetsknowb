package com.petsknow.doctor.commonmodule.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yukuo on 2016/1/9.
 * 这是一个日期转换工具类
 */
public class DateUtil {
    /**
     * 这是一个给定特殊的日期格式,返回一个把毫秒值转换为字符串的日期
     *
     * @param date         给定日期毫秒值
     * @param formatString 给定需要格式化的日期格式例如:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getFormatDate(long date, String formatString) {
        String formatData;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
        formatData = simpleDateFormat.format(date);
        return formatData;
    }

    /**
     * 这是一个给定一个已经转换后的日期字符串,返回一个该字符串所对应的毫秒值
     *
     * @param formatString 给定需要格式化的日期格式例如:yyyy-MM-dd HH:mm:ss
     * @param resultString 给定的已经转换后的字符串
     * @return
     */
    public static long getDateLongTime(String formatString, String resultString) {
        long longtime = 0l;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
            Date date = simpleDateFormat.parse(resultString);
            longtime = date.getTime();
            return longtime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return longtime;
    }

    /**
     * 这是一个获取是多久之前的信息的界面
     *
     * @param time
     * @return
     */
    public static String getKeepTimStr(long time) {
        if (time < 0)
            return "0分钟";
        if (time / (1000 * 60) < 60) {
            return time / (1000 * 60) + "分钟";
        } else if (time / (1000 * 60 * 60) < 24) {
            return time / (1000 * 60 * 60) + "小时";
        } else if (time / (1000 * 60 * 60 * 24) < 30) {
            return time / (1000 * 60 * 60 * 24) + "天";
        } else if (time / (1000 * 60 * 60 * 24 * 30L) < 12) {
            return time / (1000 * 60 * 60 * 24 * 30L) + "月";
        } else {
            return time / (1000L * 60L * 60L * 24L * 30L * 12L) + "年";
        }
    }

    /**
     * 这是一个获取年龄的工具类
     *
     * @param m 生日时间
     * @return
     */
    public static long getAge(long m) {
        long l = System.currentTimeMillis();
        if (l <= m) {
            return 0l;
        }
        long delt = (l - m) / 3600000;
        long s = delt / (24 * 365);
        if (s < 1) {
            return 1;
        } else {
            return s;
        }

    }
}
