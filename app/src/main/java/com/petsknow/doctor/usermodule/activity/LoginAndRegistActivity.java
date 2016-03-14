package com.petsknow.doctor.usermodule.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.usermodule.fragment.LoginFragment;

import butterknife.Bind;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个登录注册的页面
 */
public class LoginAndRegistActivity extends BaseActivity {
    @Bind(R.id.fl_loginandgist)
    FrameLayout fl_loginandgist;
    @Override
    public void initdata(Bundle extras) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_loginandgist, new LoginFragment(), "ONE");
        ft.commit();
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_login_regist;
    }

}
