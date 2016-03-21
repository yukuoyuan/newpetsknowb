package com.petsknow.doctor.commonmodule.glide;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.PetsknowDoctorApplication;

/**
 * Created by yukuo on 2016/3/21.
 * 这是一个网络图片加载框架工具类
 */
public class GlideUtils {
    /**
     * 这是一个加载网络图片并转换为圆形的方法
     *
     * @param url       网络图片路径
     * @param imageView 控件
     */
    public static void circleImage(String url, ImageView imageView) {
        Glide.with(PetsknowDoctorApplication.context).load(url)
                .error(R.drawable.default_icon_headphoto)
                .transform(new GlideCircleTransform(PetsknowDoctorApplication.context))
                .into(imageView);
    }

    /**
     * 这是一个加载网络图片的方法,显示方式为中心最大化
     *
     * @param url       网络图片路径
     * @param imageView 控件
     */
    public static void imageCentergroup(String url, ImageView imageView) {
        Glide.with(PetsknowDoctorApplication.context).load(url)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 这是一个正常加载网络图片的方法
     *
     * @param url       网络图片路径
     * @param imageView 控件
     */
    public static void image(String url, ImageView imageView) {
        Glide.with(PetsknowDoctorApplication.context).load(url)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

    /**
     * 这是一个正常加载网络图片并设置圆角的方法
     *
     * @param url       网络图片路径
     * @param imageView 控件
     * @param f         圆角度
     */
    public static void roundImage(String url, ImageView imageView, int f) {
        Glide.with(PetsknowDoctorApplication.context).load(url)
                .error(R.drawable.default_icon_headphoto)
                .transform(new GlideRoundTransform(PetsknowDoctorApplication.context, f))
                .into(imageView);
    }

    /**
     * 这是一个正常加载网络图片并设置圆角的方法并且一中心放大加载
     *
     * @param url       网络图片路径
     * @param imageView 控件
     * @param f         圆角度
     */
    public static void roundImageCenterGroup(String url, ImageView imageView, int f) {
        Glide.with(PetsknowDoctorApplication.context).load(url)
                .error(R.drawable.default_icon_headphoto)
                .transform(new GlideRoundTransform(PetsknowDoctorApplication.context, f))
                .centerCrop()
                .into(imageView);
    }
}
