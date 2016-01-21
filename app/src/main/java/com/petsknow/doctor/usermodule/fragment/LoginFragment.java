package com.petsknow.doctor.usermodule.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.Constant;
import com.petsknow.doctor.commonmodule.constant.ContextUrl;
import com.petsknow.doctor.commonmodule.constant.MyApplication;
import com.petsknow.doctor.commonmodule.fragment.BaseFragment;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.mainmodule.activity.MainActivity;
import com.petsknow.doctor.usermodule.bean.LoginBean;
import com.petsknow.doctor.usermodule.manger.UserManger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个登陆的页面
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private Button bt_login;
    private EditText et_login_phone;
    private EditText et_login_password;
    private Button bt_forgetpassword;
    private Button bt_login_regist;
    private LoginBean loginBean;
    private Intent intent;

    @Override
    public void initView(View view) {
        bt_login = (Button) view.findViewById(R.id.bt_login);
        et_login_phone = (EditText) view.findViewById(R.id.et_login_phone);
        et_login_password = (EditText) view.findViewById(R.id.et_login_password);
        bt_forgetpassword = (Button) view.findViewById(R.id.bt_forgetpassword);
        bt_login_regist = (Button) view.findViewById(R.id.bt_login_regist);
    }

    @Override
    public void setListener() {
        bt_login.setOnClickListener(this);
        bt_forgetpassword.setOnClickListener(this);
        bt_login_regist.setOnClickListener(this);
    }

    @Override
    public void initdata() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setListener();
        initdata();
    }

    @Override
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
        String url = ContextUrl.BaseUrl + ContextUrl.login;
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
                        MyApplication.setLogin(true);
                        intent = new Intent(getActivity(), MainActivity.class);
                        getActivity().startActivity(intent);
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
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        EMGroupManager.getInstance().loadAllGroups();
                        L.d("main", "登陆聊天服务器成功！");
                    }
                });
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
    }
}
