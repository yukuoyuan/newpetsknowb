package com.petsknow.doctor.sessionmodule.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.sessionmodule.adapter.ChartListVIewAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yukuo on 2016/1/25.
 * 这是一个聊天的页面
 */
public class ChatActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.iv_back)
    ImageButton ivBack;
    @Bind(R.id.tv_public_title)
    TextView tvPublicTitle;
    @Bind(R.id.public_titlebg)
    ImageView publicTitlebg;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ib_main_mypatient)
    ImageButton ibMainMypatient;
    @Bind(R.id.lv_chart)
    RecyclerView lvChart;
    private List<EMMessage> emMessages;
    private String toChatUsername;

    @Override
    public void initdata(Bundle extras) {
        toChatUsername = extras.getString("toChatUsername");
        publicTitlebg.setVisibility(View.GONE);
        ibMainMypatient.setVisibility(View.GONE);
        tvRight.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);
        tvPublicTitle.setVisibility(View.VISIBLE);
        tvPublicTitle.setText("问诊中");
        tvRight.setText("结束回话");
        getallmessage();

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_chart;
    }

    @Override
    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_right:
                //结束回话,并跳转到发送诊断书界面

                break;
        }
    }

    /**
     * 获取当前会话所有的会话列表
     */
    public void getallmessage() {
        EMConversation conversation = EMChatManager.getInstance().getConversation(toChatUsername);
        //获取此会话的所有消息
        emMessages = conversation.getAllMessages();
        lvChart.setLayoutManager(new LinearLayoutManager(this));
        lvChart.setAdapter(new ChartListVIewAdapter(emMessages));
        L.i("消息个数", emMessages.size() + "");
    }


}
