package com.petsknow.doctor.guidemodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.commonmodule.adapter.MyPagerAdapter;
import com.petsknow.doctor.commonmodule.constant.Constant;
import com.petsknow.doctor.commonmodule.constant.MyApplication;
import com.petsknow.doctor.commonmodule.utils.SPUtil;
import com.petsknow.doctor.guidemodule.fragment.GuideFirstFragment;
import com.petsknow.doctor.guidemodule.fragment.GuideSecondFragment;
import com.petsknow.doctor.guidemodule.fragment.GuideThirdFragment;
import com.petsknow.doctor.mainmodule.activity.MainActivity;
import com.petsknow.doctor.usermodule.activity.LoginAndRegistActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个引导的页面
 */
public class GuideActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.vp_guide)
    ViewPager vp_guide;
    @Bind(R.id.bt_getintomain)
    Button bt_getintomain;
    private List<Fragment> fragments = new ArrayList<>();
    private Intent intent;

    @Override
    public void initdata(Bundle extras) {
        fragments.add(new GuideFirstFragment());
        fragments.add(new GuideSecondFragment());
        fragments.add(new GuideThirdFragment());
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        vp_guide.setAdapter(myPagerAdapter);
        vp_guide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    bt_getintomain.setVisibility(View.VISIBLE);
                } else {
                    bt_getintomain.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_guide;
    }

    @Override
    @OnClick({R.id.bt_getintomain})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_getintomain:
                SPUtil.putBoolean(getApplicationContext(), Constant.ISFIRST, true);
                /**
                 *判断是否登录
                 */
                if (MyApplication.islogin()) {
                    //打开主页面
                    loadMainUi();
                } else {
                    //打开登录注册页面
                    loadLogin();
                };
                break;
        }
    }

    /**
     * 跳转至登录注册页面
     */
    private void loadLogin() {
        intent = new Intent(GuideActivity.this, LoginAndRegistActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 打开主页面
     */
    private void loadMainUi() {
        intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
