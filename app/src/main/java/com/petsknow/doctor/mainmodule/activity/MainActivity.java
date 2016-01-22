package com.petsknow.doctor.mainmodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.commonmodule.adapter.MyPagerAdapter;
import com.petsknow.doctor.infomodule.fragment.InfoFragment;
import com.petsknow.doctor.patientmodule.activity.PatientActivity;
import com.petsknow.doctor.sessionmodule.fragment.SessionListFragment;
import com.petsknow.doctor.usermodule.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个主页面
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager vp_main;
    private RadioGroup rg_main;
    private RadioButton rb_ask;
    private RadioButton rb_info;
    private RadioButton rb_user;
    private List<Fragment> fragments = new ArrayList<>();
    private ImageButton ib_main_mypatient;
    private Intent intent;

    @Override
    public void initView() {
        vp_main = (ViewPager) findViewById(R.id.vp_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        rb_ask = (RadioButton) findViewById(R.id.rb_ask);
        rb_info = (RadioButton) findViewById(R.id.rb_info);
        rb_user = (RadioButton) findViewById(R.id.rb_user);
        ib_main_mypatient = (ImageButton) findViewById(R.id.ib_main_mypatient);
    }

    @Override
    public void setListener() {
        ib_main_mypatient.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        fragments.add(new SessionListFragment());
        fragments.add(new InfoFragment());
        fragments.add(new UserFragment());
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        vp_main.setAdapter(myPagerAdapter);
        vp_main.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rb_ask.setChecked(true);
                        break;
                    case 1:
                        rb_info.setChecked(true);
                        break;
                    case 2:
                        rb_user.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_ask:
                        vp_main.setCurrentItem(0);
                        break;
                    case R.id.rb_info:
                        vp_main.setCurrentItem(1);
                        break;
                    case R.id.rb_user:
                        vp_main.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
        initdata();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_main_mypatient:
                intent = new Intent(MainActivity.this, PatientActivity.class);
                startActivity(intent);
                break;
        }
    }
}
