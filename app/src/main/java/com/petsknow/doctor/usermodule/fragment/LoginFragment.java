package com.petsknow.doctor.usermodule.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.Constant;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.fragment.BaseFragment;
import com.petsknow.doctor.commonmodule.netutil.OkHttpUtil;
import com.petsknow.doctor.commonmodule.netutil.RequestPacket;
import com.petsknow.doctor.commonmodule.netutil.ResponseListener;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.mainmodule.activity.MainActivity;
import com.petsknow.doctor.usermodule.bean.LoginBean;
import com.petsknow.doctor.usermodule.manger.UserManger;
import com.squareup.okhttp.Request;

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
    private Intent intent;
    private Registfragment mRegisterFragment;

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
                if (mRegisterFragment == null)
                    mRegisterFragment = new Registfragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.rotatedown_right_in, R.anim.rotatedown_left_out);
                fragmentTransaction.replace(R.id.fl_loginandgist, mRegisterFragment).commit();
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
        RequestPacket requestPacket = new RequestPacket();
        requestPacket.url = ConstantUrl.BaseUrl() + ConstantUrl.login;
        requestPacket.addArgument("phone", phone);
        requestPacket.addArgument("password", pwd);
        OkHttpUtil.Request(RequestPacket.POST, requestPacket, new ResponseListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                if (loginBean.getStatus() == 0) {
                    if (loginBean.getData() != null && loginBean.getData().size() > 0) {
                        //存储用户信息
                        saveuserdata(loginBean);
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
            public void onFailure(Request request) {

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
     * @param loginBean
     */
    private void saveuserdata(LoginBean loginBean) {
        UserManger.saveUserId(loginBean.getData().get(0).getId());
        UserManger.saveUserEaseMobName(loginBean.getData().get(0).getEasemobName());
        UserManger.saveUserAvaturl(loginBean.getData().get(0).getAvatarUrl());
        UserManger.saveUserTrueName(loginBean.getData().get(0).getTrueName());
        UserManger.saveUserPhone(loginBean.getData().get(0).getPhone());
    }
}
