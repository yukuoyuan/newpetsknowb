package com.petsknow.doctor.usermodule.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.WebActivity;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.constant.PetsknowDoctorApplication;
import com.petsknow.doctor.commonmodule.fragment.BaseFragment;
import com.petsknow.doctor.commonmodule.netutil.OkHttpUtil;
import com.petsknow.doctor.commonmodule.netutil.RequestPacket;
import com.petsknow.doctor.commonmodule.netutil.ResponseListener;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.commonmodule.view.DelayButton;
import com.petsknow.doctor.usermodule.bean.RegistBean;
import com.petsknow.doctor.usermodule.bean.UpdatePasswordVcodeBean;
import com.petsknow.doctor.usermodule.manger.UserManger;
import com.squareup.okhttp.Request;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个注册页面
 */
public class Registfragment extends BaseFragment implements View.OnClickListener, DelayButton.OnDelayListener {


    @Bind(R.id.regist_phone)
    EditText registPhone;
    @Bind(R.id.regist_password)
    EditText registPassword;
    @Bind(R.id.regist_verify_code)
    EditText registVerifyCode;
    @Bind(R.id.regist_get_verify_code)
    DelayButton registGetVerifyCode;
    @Bind(R.id.register_agree)
    CheckBox registerAgree;
    private LoginFragment mLoginFragment;
    private Intent intent;

    @Override
    public void initdata(Bundle arguments) {
        registGetVerifyCode.setDelayListener(this);
        registPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 10) {
                    registGetVerifyCode.setEnabled(true);
                } else {
                    registGetVerifyCode.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_regist;
    }

    @Override
    @OnClick({R.id.regist_cancel, R.id.register, R.id.regist_get_verify_code, R.id.register_protocol})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_cancel:
                //取消
                if (mLoginFragment == null)
                    mLoginFragment = new LoginFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.rotatedown_left_in, R.anim.rotatedown_right_out);
                fragmentTransaction.replace(R.id.fl_loginandgist, mLoginFragment).commit();
                break;
            case R.id.register:
                //注册
                String telRegex = "[1][3578]\\d{9}";
                String phone = registPhone.getText().toString().trim();
                String password = registPassword.getText().toString().trim();
                String vcode = registVerifyCode.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    T.showLong(PetsknowDoctorApplication.context, "手机号不能为空");
                    return;
                }
                if (!phone.matches(telRegex)) {
                    T.showLong(PetsknowDoctorApplication.context, "请输入正确的手机号码");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    T.showLong(PetsknowDoctorApplication.context, "密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(vcode)) {
                    T.showLong(PetsknowDoctorApplication.context, "验证码不能为空");
                    return;
                }
                if (!registerAgree.isChecked()) {
                    T.showLong(PetsknowDoctorApplication.context, "请阅读同意医师行为规范");
                    return;
                }
                regist(phone, password, vcode);
                break;
            case R.id.regist_get_verify_code:
                //发送验证码的按钮
                //校验手机号的正确性
                //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
                String telRegexsms = "[1][3578]\\d{9}";
                String phonesms = registPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phonesms)) {
                    T.showLong(PetsknowDoctorApplication.context, "手机号不能为空");
                    return;
                }
                if (!phonesms.matches(telRegexsms)) {
                    T.showLong(PetsknowDoctorApplication.context, "请输入正确的手机号码");
                    return;
                }
                sendsmsvcode(phonesms);
                break;
            case R.id.register_protocol:
                //医生用户协议的页面
                intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("type", 2);
                getActivity().startActivity(intent);
                break;
        }
    }

    /**
     * 这是一个注册的方法
     *
     * @param phone
     * @param password
     * @param vcode
     */
    private void regist(String phone, String password, String vcode) {
        RequestPacket requestPacket = new RequestPacket();
        requestPacket.url = ConstantUrl.BaseUrl() + ConstantUrl.regist;
        requestPacket.addArgument("phone", phone);
        requestPacket.addArgument("password", password);
        requestPacket.addArgument("channel", UserManger.getUserId());
        requestPacket.addArgument("vcode", vcode);
        OkHttpUtil.Request(RequestPacket.POST, requestPacket, new ResponseListener<RegistBean>() {
            @Override
            public void onSuccess(RegistBean registBean) {
                if (registBean.getData() != null && registBean.getStatus() == 0) {
                    T.show(getActivity(), registBean.getMsg(), 0);
                    //进入提交审核信息的页面
                } else {
                    T.show(getActivity(), registBean.getMsg(), 0);
                }
            }

            @Override
            public void onFailure(Request request) {

            }
        });
    }

    /**
     * 发送手机验证码
     *
     * @param phone
     */
    private void sendsmsvcode(String phone) {
        registGetVerifyCode.delay(120);
        RequestPacket requestPacket = new RequestPacket();
        requestPacket.url = ConstantUrl.BaseUrl() + ConstantUrl.sendvcode;
        requestPacket.addArgument("phone", phone);
        requestPacket.addArgument("opt", "user_reg");
        OkHttpUtil.Request(RequestPacket.GET, requestPacket, new ResponseListener<UpdatePasswordVcodeBean>() {
            @Override
            public void onSuccess(UpdatePasswordVcodeBean updatePasswordVcodeBean) {
                if (updatePasswordVcodeBean.getData() != null && updatePasswordVcodeBean.getStatus() == 0) {
                    T.show(getActivity(), updatePasswordVcodeBean.getMsg(), 0);
                } else {
                    T.show(getActivity(), updatePasswordVcodeBean.getMsg(), 0);
                }
            }

            @Override
            public void onFailure(Request request) {

            }
        });
    }

    @Override
    public void onStartDelay(int second) {
        registGetVerifyCode.setEnabled(false);
    }

    @Override
    public void onRun(int second) {

    }

    @Override
    public void onFinish() {
        if (registGetVerifyCode != null) {
            registGetVerifyCode.setEnabled(true);
        }
    }
}
