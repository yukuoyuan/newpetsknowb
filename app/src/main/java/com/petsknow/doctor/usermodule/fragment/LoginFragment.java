package com.petsknow.doctor.usermodule.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.Constant;
import com.petsknow.doctor.commonmodule.constant.ContextUrl;
import com.petsknow.doctor.commonmodule.fragment.BaseFragment;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.mainmodule.activity.MainActivity;
import com.petsknow.doctor.usermodule.bean.LoginBean;
import com.petsknow.doctor.usermodule.manger.UserManger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个登陆的页面
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.et_login_phone)
    EditText et_login_phone;
    @Bind(R.id.et_login_password)
    EditText et_login_password;
    private LoginBean loginBean;
    private Intent intent;

    @Override
    public void initdata(Bundle arguments) {

    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_login;
    }

    @Override
    @OnClick({R.id.bt_login, R.id.bt_forgetpassword, R.id.bt_login_regist})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                loginuser();
                break;
            case R.id.bt_forgetpassword:
                break;
            case R.id.bt_login_regist:
                Registfragment registfragment = new Registfragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction tx = fm.beginTransaction();
                tx.replace(R.id.fl_loginandgist, registfragment, "TWO");
                tx.commit();
                break;
        }
    }

    private void loginuser() {
        String phone = et_login_phone.getText().toString().trim();
        String pwd = et_login_password.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            T.show(getActivity(), "手机号不能为空", 0);
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            T.show(getActivity(), "密码不能为空", 0);
            return;
        }
        String url = ContextUrl.BaseUrl() + ContextUrl.login;
        RequestParams params = new RequestParams(url);
        params.addParameter("phone", phone);
        params.addParameter("password", pwd);
        params.setAsJsonContent(true);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                L.e("登录成功", o);
                loginBean = JSON.parseObject(o, LoginBean.class);
                if (loginBean.getStatus() == 0) {
                    if (loginBean.getData() != null && loginBean.getData().size() > 0) {
                        //存储用户信息
                        saveuserdata();
                        logineasemobnema(loginBean.getData().get(0).getEasemobName());
                        UserManger.setLogin(true);
                        intent = new Intent(getActivity(), MainActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    } else {
                        T.show(getActivity(), loginBean.getMsg(), 0);
                    }
                } else {
                    T.show(getActivity(), loginBean.getMsg(), 0);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                T.show(getActivity(), "网络连接超时,请稍后再试", 0);
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
     * 登录环信
     */
    private void logineasemobnema(String usernema) {
        EMChatManager.getInstance().login(usernema, Constant.EASEMOBNAMEPWD, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMGroupManager.getInstance().loadAllGroups();
                L.d("main", "登陆聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                L.d("main", "登陆聊天服务器失败！");
            }
        });
    }

    /**
     * 这是一个存储用户信息的方法
     */
    private void saveuserdata() {
        UserManger.saveUserId(loginBean.getData().get(0).getId());
        UserManger.saveUserEaseMobName(loginBean.getData().get(0).getEasemobName());
        UserManger.saveUserAvaturl(loginBean.getData().get(0).getAvatarUrl());
        UserManger.saveUserTrueName(loginBean.getData().get(0).getTrueName());
        UserManger.saveUserPhone(loginBean.getData().get(0).getPhone());
    }
}
