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
import com.petsknow.doctor.commonmodule.utils.SPUtil;
import com.petsknow.doctor.guidemodule.fragment.GuideFirstFragment;
import com.petsknow.doctor.guidemodule.fragment.GuideSecondFragment;
import com.petsknow.doctor.guidemodule.fragment.GuideThirdFragment;
import com.petsknow.doctor.mainmodule.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个引导的页面
 */
public class GuideActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager vp_guide;
    private Button bt_getintomain;
    private List<Fragment> fragments = new ArrayList<>();
    private Intent intent;

    @Override
    public void initView() {
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        bt_getintomain = (Button) findViewById(R.id.bt_getintomain);
    }

    @Override
    public void setListener() {
        bt_getintomain.setOnClickListener(this);
    }

    @Override
    public void initdata() {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        setListener();
        initdata();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_getintomain:
                SPUtil.putBoolean(getApplicationContext(), Constant.ISFIRST, true);
                loadLogin();
                break;
        }
    }

    /**
     * 跳转至登录注册页面
     */
    private void loadLogin() {
        intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
