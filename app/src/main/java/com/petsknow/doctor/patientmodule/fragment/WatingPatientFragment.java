package com.petsknow.doctor.patientmodule.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.fragment.BaseFragment;
import com.petsknow.doctor.commonmodule.netutil.OkHttpUtil;
import com.petsknow.doctor.commonmodule.netutil.RequestPacket;
import com.petsknow.doctor.commonmodule.netutil.ResponseListener;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.patientmodule.activity.WatingDetailActivity;
import com.petsknow.doctor.patientmodule.adapter.WatingPatientlistViewAdapter;
import com.petsknow.doctor.patientmodule.bean.WatingpatientBean;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.greenrobot.event.EventBus;

/**
 * Created by yukuo on 2016/1/22.
 * 这是一个待珍的页面
 */
public class WatingPatientFragment extends BaseFragment {
    @Bind(R.id.srl_wating_patient)
    SwipeRefreshLayout srl_wating_patient;
    @Bind(R.id.lv_wating_patient)
    ListView lv_wating_patient;
    private WatingPatientlistViewAdapter watingPatientlistViewAdapter;
    private WatingpatientBean watingpatientBean;
    private List<WatingpatientBean.DataEntity> list = new ArrayList<>();
    private Intent intent;
    public void onEvent(String event) {
        if (event.equals("Admissions")){
            getActivity().finish();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 这是一个获取患者列表的界面的方法
     */
    private void getwatingpatient() {
        RequestPacket requestPacket = new RequestPacket();
        requestPacket.url = ConstantUrl.BaseUrl() + ConstantUrl.getwatingpatient;
        OkHttpUtil.Request(RequestPacket.POST, requestPacket, new ResponseListener<WatingpatientBean>() {
            @Override
            public void onSuccess(WatingpatientBean watingpatientBean) {
                if (srl_wating_patient!=null){
                    srl_wating_patient.setRefreshing(false);
                }
                if (watingpatientBean.getStatus() == 0) {
                    if (watingpatientBean.getData() != null && watingpatientBean.getData().size() > 0) {
                        list.clear();
                        for (int i = 0; i < watingpatientBean.getData().size(); i++) {
                            list.add(watingpatientBean.getData().get(i));
                        }
                        initlistview();
                    } else {
                        T.show(getActivity(), watingpatientBean.getMsg(), 0);
                    }
                } else {
                    T.show(getActivity(), watingpatientBean.getMsg(), 0);
                }
            }

            @Override
            public void onFailure(Request request) {
                if (srl_wating_patient != null) {
                    srl_wating_patient.setRefreshing(false);
                }
            }
        });
    }

    private void initlistview() {
        watingPatientlistViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void initdata(Bundle arguments) {
        watingPatientlistViewAdapter = new WatingPatientlistViewAdapter(getActivity(), list);
        lv_wating_patient.setAdapter(watingPatientlistViewAdapter);
        srl_wating_patient.setColorSchemeResources(R.color.themecolor);
        srl_wating_patient.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getwatingpatient();
            }
        });
        lv_wating_patient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getActivity(), WatingDetailActivity.class);
                intent.putExtra("id", list.get(position).getId());
                intent.putExtra("avator", list.get(position).getAvatarUrl());
                startActivity(intent);
            }
        });
        /**
         * 这是初始化数据得方法
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                srl_wating_patient.setRefreshing(true);
                getwatingpatient();
            }
        }, 100);
    }
    @Override
    public int getContentLayout() {
        return R.layout.fragment_watingpatient;
    }
}
