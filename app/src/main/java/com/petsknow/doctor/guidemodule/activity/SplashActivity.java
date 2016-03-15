package com.petsknow.doctor.guidemodule.activity;

import android.content.Intent;
import android.os.Bundle;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.commonmodule.constant.Constant;
import com.petsknow.doctor.commonmodule.utils.SPUtil;
import com.petsknow.doctor.mainmodule.activity.MainActivity;
import com.petsknow.doctor.usermodule.activity.LoginAndRegistActivity;
import com.petsknow.doctor.usermodule.manger.UserManger;

/**
 * 这是一个过渡页面
 */
public class SplashActivity extends BaseActivity {

    private Intent intent;

    @Override
    public void initdata(Bundle extras) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2800);
                    //判断是否打开引导页
                    boolean isFirst = SPUtil.getBoolean(getApplicationContext(), Constant.ISFIRST, false);
                    if (!isFirst) {
                        intent = new Intent(SplashActivity.this, GuideActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        /**
                         *判断是否登录
                         */
                        if (UserManger.islogin()) {
                            //打开主页面
                            loadMainUi();
                        } else {
                            //打开登录注册页面
                            loadLogin();
                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_splash;
    }

    private void loadLogin() {
        intent = new Intent(SplashActivity.this, LoginAndRegistActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 打开主页面
     */
    private void loadMainUi() {
        intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
