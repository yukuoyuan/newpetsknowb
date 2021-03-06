package com.petsknow.doctor.usermodule.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.chat.EMChatManager;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.commonmodule.activity.WebActivity;
import com.petsknow.doctor.commonmodule.bean.CommonBean;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.netutil.OkHttpUtil;
import com.petsknow.doctor.commonmodule.netutil.RequestPacket;
import com.petsknow.doctor.commonmodule.netutil.ResponseListener;
import com.petsknow.doctor.usermodule.manger.UserManger;
import com.squareup.okhttp.Request;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yukuo on 2016/3/16.
 * 这是一个设置页面
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
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
    private Intent intent;

    @Override
    public void initdata(Bundle extras) {
        publicTitlebg.setVisibility(View.GONE);
        ibMainMypatient.setVisibility(View.GONE);
        tvRight.setVisibility(View.GONE);
        ivBack.setVisibility(View.VISIBLE);
        tvPublicTitle.setVisibility(View.VISIBLE);
        tvPublicTitle.setText("设置");
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_setting;
    }

    @Override
    @OnClick({R.id.iv_back, R.id.tv_update_password, R.id.tv_login_off, R.id.tv_pet_know_about})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_update_password:
                //更改密码
                intent = new Intent(SettingActivity.this, UpdatePaswordActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_off:
                //退出登录
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setMessage("您确认要退出登录吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loginout();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
                break;
            case R.id.tv_pet_know_about:
                //关于我们
                intent = new Intent(SettingActivity.this, WebActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
        }
    }

    /**
     * 这是一个退出登录的方法
     */
    private void loginout() {
        RequestPacket requestPacket = new RequestPacket();
        requestPacket.url = ConstantUrl.BaseUrl() + ConstantUrl.logout;
        requestPacket.addArgument("doctorId", UserManger.getUserId());
        OkHttpUtil.Request(RequestPacket.POST, requestPacket, new ResponseListener<CommonBean>() {
            @Override
            public void onSuccess(CommonBean commonBean) {
                setResult(RESULT_OK);
                UserManger.setLogin(false);
                EMChatManager.getInstance().logout();//此方法为同步方法
                finish();
            }

            @Override
            public void onFailure(Request request) {

            }
        });
    }
}
