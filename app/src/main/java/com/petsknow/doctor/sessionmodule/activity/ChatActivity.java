package com.petsknow.doctor.sessionmodule.activity;

import android.os.Bundle;

import com.easemob.easeui.ui.EaseChatFragment;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;

/**
 * Created by yukuo on 2016/1/25.
 * 这是一个聊天的页面
 */
public class ChatActivity extends BaseActivity {
    @Override
    public void initView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initdata() {
        EaseChatFragment
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        initView();
        setListener();
        initdata();
    }
}
