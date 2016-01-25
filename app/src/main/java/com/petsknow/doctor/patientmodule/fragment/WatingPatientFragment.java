package com.petsknow.doctor.patientmodule.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.ContextUrl;
import com.petsknow.doctor.commonmodule.fragment.BaseFragment;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.patientmodule.activity.WatingDetailActivity;
import com.petsknow.doctor.patientmodule.adapter.WatingPatientlistViewAdapter;
import com.petsknow.doctor.patientmodule.bean.WatingpatientBean;
import com.petsknow.doctor.usermodule.manger.UserManger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yukuo on 2016/1/22.
 * 这是一个待珍的页面
 */
public class WatingPatientFragment extends BaseFragment {

    private SwipeRefreshLayout srl_wating_patient;
    private ListView lv_wating_patient;
    private WatingPatientlistViewAdapter watingPatientlistViewAdapter;
    private WatingpatientBean watingpatientBean;
    private List<WatingpatientBean.DataEntity> list = new ArrayList<>();
    private Intent intent;

    @Override
    public void initView(View view) {
        srl_wating_patient = (SwipeRefreshLayout) view.findViewById(R.id.srl_wating_patient);
        lv_wating_patient = (ListView) view.findViewById(R.id.lv_wating_patient);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initdata() {
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
                getActivity().startActivity(intent);
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

    /**
     * 这是一个获取患者列表的界面的方法
     */
    private void getwatingpatient() {
        String url = ContextUrl.BaseUrl + ContextUrl.getwatingpatient;
        RequestParams params = new RequestParams(url);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                L.e("所有待珍列表数据", s);
                srl_wating_patient.setRefreshing(false);
                watingpatientBean = JSON.parseObject(s, WatingpatientBean.class);
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
            public void onError(Throwable throwable, boolean b) {
                srl_wating_patient.setRefreshing(false);
                T.show(getActivity(), "网络连接超时!请稍后再试", 0);
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initlistview() {
        watingPatientlistViewAdapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_watingpatient, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setListener();
        initdata();
    }
}
