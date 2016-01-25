package com.petsknow.doctor.mainmodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.easemob.EMConnectionListener;
import com.easemob.EMError;
import com.easemob.chat.EMChatManager;
import com.easemob.util.NetUtils;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.commonmodule.adapter.MyPagerAdapter;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.infomodule.fragment.InfoFragment;
import com.petsknow.doctor.patientmodule.activity.PatientActivity;
import com.petsknow.doctor.sessionmodule.fragment.SessionListFragment;
import com.petsknow.doctor.usermodule.fragment.UserFragment;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.service.XGPushService;

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
        initXg();
        initem();
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

    /**
     * 这是一个环信监听连接状态的方法
     */
    private void initem() {
        EMChatManager.getInstance().addConnectionListener(new EMConnectionListener() {
            @Override
            public void onConnected() {
                L.e("环信连接状态", "连接成功");
            }

            @Override
            public void onDisconnected(final int i) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (i == EMError.USER_REMOVED) {
                            // 显示帐号已经被移除
                            L.e("环信连接状态", "帐号已经被移除");
                        } else if (i == EMError.CONNECTION_CONFLICT) {
                            // 显示帐号在其他设备登陆
                            L.e("环信连接状态", "帐号在其他设备登陆");
                        } else {
                            if (NetUtils.hasNetwork(MainActivity.this)) {
                                //连接不到聊天服务器
                                L.e("环信连接状态", "连接不到聊天服务器");
                            } else {
                                //当前网络不可用，请检查网络设置
                                L.e("环信连接状态", "当前网络不可用，请检查网络设置");
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * 注册信鸽
     */
    private void initXg() {
        XGPushConfig.enableDebug(this, true);
        XGPushManager.registerPush(getApplicationContext(), new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                L.e("TPush", "注册成功，设备token为：" + data);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                L.e("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
        // 2.36（不包括）之前的版本需要调用以下2行代码
        Intent service = new Intent(this, XGPushService.class);
        startService(service);
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
