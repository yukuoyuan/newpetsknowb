package com.petsknow.doctor.usermodule.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.commonmodule.bean.CommonBean;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.commonmodule.view.DelayButton;
import com.petsknow.doctor.usermodule.bean.UpdatePasswordVcodeBean;
import com.petsknow.doctor.usermodule.manger.UserManger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yukuo on 2016/3/16.
 * 这是一个修改密码的界面
 */
public class UpdatePaswordActivity extends BaseActivity implements View.OnClickListener, DelayButton.OnDelayListener {
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
    @Bind(R.id.et_update_password_first)
    EditText etUpdatePasswordFirst;
    @Bind(R.id.et_update_password_second)
    EditText etUpdatePasswordSecond;
    @Bind(R.id.et_update_passwor_vcode)
    EditText etUpdatePassworVcode;
    @Bind(R.id.bt_update_password_getvcode)
    DelayButton btUpdatePasswordGetvcode;
    private UpdatePasswordVcodeBean updatePasswordVcodeBean;

    @Override
    public void initdata(Bundle extras) {
        publicTitlebg.setVisibility(View.GONE);
        ibMainMypatient.setVisibility(View.GONE);
        tvRight.setVisibility(View.GONE);
        ivBack.setVisibility(View.VISIBLE);
        tvPublicTitle.setVisibility(View.VISIBLE);
        tvPublicTitle.setText("修改密码");
        btUpdatePasswordGetvcode.setDelayListener(this);
        btUpdatePasswordGetvcode.setEnabled(true);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_updatepassword;
    }

    @Override
    @OnClick({R.id.iv_back, R.id.bt_update_password_getvcode, R.id.bt_update_password_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.bt_update_password_getvcode:
                //发送验证码
                btUpdatePasswordGetvcode.delay(120);
                sendvcode();
                break;
            case R.id.bt_update_password_confirm:
                //修改密码的方法
                String newpwdfirst = etUpdatePasswordFirst.getText().toString().trim();
                String newpwdsecond = etUpdatePasswordSecond.getText().toString().trim();
                String vcode = etUpdatePassworVcode.getText().toString().trim();
                if (TextUtils.isEmpty(newpwdfirst)) {
                    T.show(UpdatePaswordActivity.this, "第一次输入密码不能为空", 0);
                    return;
                }
                if (TextUtils.isEmpty(newpwdsecond)) {
                    T.show(UpdatePaswordActivity.this, "第二次输入密码不能为空", 0);
                    return;
                }
                if (TextUtils.isEmpty(vcode)) {
                    T.show(UpdatePaswordActivity.this, "验证码不能为空", 0);
                    return;
                }
                if (!newpwdfirst.equals(newpwdsecond)) {
                    T.show(UpdatePaswordActivity.this, "两次输入密码不一致", 0);
                    return;
                }
                updatepasword(newpwdfirst, vcode);
                break;

        }
    }

    /**
     * 这是一个修改密码的方法
     *
     * @param newpwdfirst
     * @param vcode
     */
    private void updatepasword(String newpwdfirst, String vcode) {
        String url = ConstantUrl.BaseUrl() + ConstantUrl.updatepwd;
        RequestParams params = new RequestParams(url);
        String phone = UserManger.getUserPhone();
        params.addParameter("phone", phone);
        params.addParameter("password", newpwdfirst);
        params.addParameter("vcode", vcode);
        params.setAsJsonContent(true);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                L.e("更改密码成功", o);
                CommonBean commonBean = JSON.parseObject(o, CommonBean.class);
                if (commonBean.getData() != null && commonBean.getStatus() == 0) {
                    T.show(UpdatePaswordActivity.this, commonBean.getMsg(), 0);
                    finish();
                } else {
                    T.show(UpdatePaswordActivity.this, commonBean.getMsg(), 0);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                T.show(UpdatePaswordActivity.this, "网络连接失败请稍后再试", 0);
            }

            @Override
            public void onCancelled(CancelledException e) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    /**
     * 发送验证码
     */
    private void sendvcode() {
        String url = ConstantUrl.BaseUrl() + ConstantUrl.sendvcode;
        RequestParams params = new RequestParams(url);
        params.addParameter("phone", UserManger.getUserPhone());
        params.addParameter("opt", "user_reg");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                L.e("发送成功", o);
                updatePasswordVcodeBean = JSON.parseObject(o, UpdatePasswordVcodeBean.class);
                if (updatePasswordVcodeBean.getData() != null && updatePasswordVcodeBean.getStatus() == 0) {
                    T.show(UpdatePaswordActivity.this, updatePasswordVcodeBean.getMsg(), 0);
                } else {
                    T.show(UpdatePaswordActivity.this, updatePasswordVcodeBean.getMsg(), 0);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                T.show(UpdatePaswordActivity.this, "网络连接超时,请稍后再试", 0);
            }

            @Override
            public void onCancelled(CancelledException e) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    @Override
    public void onStartDelay(int second) {
        btUpdatePasswordGetvcode.setEnabled(false);
    }

    @Override
    public void onRun(int second) {

    }

    @Override
    public void onFinish() {
        if (btUpdatePasswordGetvcode != null) {
            btUpdatePasswordGetvcode.setEnabled(true);
        }
    }
}
