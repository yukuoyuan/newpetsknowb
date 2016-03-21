package com.petsknow.doctor.usermodule.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.Constant;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.fragment.BaseFragment;
import com.petsknow.doctor.commonmodule.netutil.OkHttpUtil;
import com.petsknow.doctor.commonmodule.netutil.RequestPacket;
import com.petsknow.doctor.commonmodule.netutil.ResponseListener;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.commonmodule.view.MyListview;
import com.petsknow.doctor.guidemodule.activity.SplashActivity;
import com.petsknow.doctor.usermodule.activity.SettingActivity;
import com.petsknow.doctor.usermodule.bean.MypersonBean;
import com.petsknow.doctor.usermodule.manger.UserManger;
import com.squareup.okhttp.Request;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个我的信息的页面
 */
public class UserFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.tv_professor_doctor)
    TextView tvProfessorDoctor;
    @Bind(R.id.tv_pet_company)
    TextView tvPetCompany;
    @Bind(R.id.tv_doctor_number)
    TextView tvDoctorNumber;
    @Bind(R.id.tv_admissions_number)
    TextView tvAdmissionsNumber;
    @Bind(R.id.tv_activity_main_grade)
    TextView tvActivityMainGrade;
    @Bind(R.id.tv_activity_main_compare)
    TextView tvActivityMainCompare;
    @Bind(R.id.iv_switch_button)
    Switch ivSwitchButton;
    @Bind(R.id.my_lv_pingjia)
    MyListview myLvPingjia;

    @Override
    public void initdata(Bundle arguments) {
        //获取个人信息的方法
        getmypersoninfo();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_user;
    }

    public void getmypersoninfo() {
        RequestPacket requestPacket = new RequestPacket();
        requestPacket.url = ConstantUrl.BaseUrl() + ConstantUrl.getdoctorinfo;
        requestPacket.addArgument("doctorId", UserManger.getUserId());
        OkHttpUtil.Request(RequestPacket.GET, requestPacket, new ResponseListener<MypersonBean>() {
            @Override
            public void onSuccess(MypersonBean mypersonBean) {
                if (mypersonBean.getData() != null && mypersonBean.getStatus() == 0) {
                    initViewData(mypersonBean);
                } else {
                    T.show(getActivity(), mypersonBean.getMsg(), 0);
                }
            }

            @Override
            public void onFailure(Request request) {

            }
        });
    }

    /**
     * 设置界面数据
     * @param mypersonBean
     */
    private void initViewData(MypersonBean mypersonBean) {
        //医生名字
        tvUserName.setText(mypersonBean.getData().get(0).getTrueName());
        //职业
        tvProfessorDoctor.setText(mypersonBean.getData().get(0).getTitle());
        //医院名字
        tvPetCompany.setText(mypersonBean.getData().get(0).getHospital());
        //接诊多少人
        tvAdmissionsNumber.setText(mypersonBean.getData().get(0).getTimesOfWork() + "");
        //评分
        tvActivityMainGrade.setText(mypersonBean.getData().get(0).getEvaluation() + "");
        //超越行业水平
        tvActivityMainCompare.setText(mypersonBean.getData().get(0).getLevel() + "");
        //医生编号
        tvDoctorNumber.setText(mypersonBean.getData().get(0).getId() + "");
    }

    @Override
    @OnClick({R.id.rl_setting})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_setting:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(intent, Constant.STARTSETINGFORRESULT);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.STARTSETINGFORRESULT && resultCode == getActivity().RESULT_OK) {
            //关闭当前页面
            Intent intent = new Intent(getActivity(), SplashActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }
}
