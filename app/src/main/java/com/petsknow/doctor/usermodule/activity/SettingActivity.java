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
import com.petsknow.doctor.commonmodule.constant.ContextUrl;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.usermodule.manger.UserManger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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
                startActivity(intent);
                break;
        }
    }

    /**
     * 这是一个退出登录的方法
     */
    private void loginout() {
        String url = ContextUrl.BaseUrl() + ContextUrl.logout;
        RequestParams params = new RequestParams(url);
        params.addParameter("doctorId", UserManger.getUserId());
        params.setAsJsonContent(true);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                L.e("退出成功", o);
                //loginBean = JSON.parseObject(o, LoginBean.class);
                setResult(RESULT_OK);
                UserManger.setLogin(false);
                EMChatManager.getInstance().logout();//此方法为同步方法
                finish();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                T.show(SettingActivity.this, "网络连接超时,请稍后再试", 0);
            }

            @Override
            public void onCancelled(CancelledException e) {
            }

            @Override
            public void onFinished() {
            }
        });
    }
}
