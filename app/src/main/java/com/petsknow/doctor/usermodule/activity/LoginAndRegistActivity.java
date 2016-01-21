package com.petsknow.doctor.usermodule.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.usermodule.fragment.LoginFragment;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个登录注册的页面
 */
public class LoginAndRegistActivity extends BaseActivity {

    private FrameLayout fl_loginandgist;

    @Override
    public void initView() {
        fl_loginandgist = (FrameLayout) findViewById(R.id.fl_loginandgist);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initdata() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_loginandgist, new LoginFragment(), "ONE");
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_regist);
        initView();
        setListener();
        initdata();
    }
}
