package com.petsknow.doctor.patientmodule.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.fragment.BaseFragment;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.patientmodule.adapter.MyPatientlistViewAdapter;
import com.petsknow.doctor.patientmodule.bean.MypatientBean;
import com.petsknow.doctor.usermodule.manger.UserManger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by yukuo on 2016/1/22.
 * 这是一个我的患者的页面
 */
public class MypatientFragment extends BaseFragment {
    @Bind(R.id.srl_mypatient)
    SwipeRefreshLayout srl_mypatient;
    @Bind(R.id.lv_mypatient)
    ListView lv_mypatient;
    private MypatientBean mypatientbean;
    private List<MypatientBean.DataEntity> list = new ArrayList<>();
    private MyPatientlistViewAdapter myPatientlistViewAdapter;
    @Override
    public void initdata(Bundle arguments) {
        myPatientlistViewAdapter = new MyPatientlistViewAdapter(getActivity(), list);
        lv_mypatient.setAdapter(myPatientlistViewAdapter);
        srl_mypatient.setColorSchemeResources(R.color.themecolor);
        srl_mypatient.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getmypatientdata();
            }
        });
        getmypatientdata();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_mypatient;
    }

    public void getmypatientdata() {
        String url = ConstantUrl.BaseUrl() + ConstantUrl.Mypatient;
        RequestParams params = new RequestParams(url);
        params.addParameter("doctorId", UserManger.getUserId());
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String s) {
                L.e("我的患者列表", s);
                srl_mypatient.setRefreshing(false);
                mypatientbean = JSON.parseObject(s, MypatientBean.class);
                if (mypatientbean.getStatus() == 0) {
                    if (mypatientbean.getData() != null && mypatientbean.getData().size() > 0) {
                        list.clear();
                        for (int i = 0; i < mypatientbean.getData().size(); i++) {
                            list.add(mypatientbean.getData().get(i));
                        }
                        initListview();
                    } else {
                        T.show(getActivity(), mypatientbean.getMsg(), 0);
                    }
                } else {
                    T.show(getActivity(), mypatientbean.getMsg(), 0);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                srl_mypatient.setRefreshing(false);
                L.e("我的患者列表的错误", throwable.getMessage());
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

    /**
     * 初始化listview的数据
     */
    private void initListview() {
        myPatientlistViewAdapter.notifyDataSetChanged();
    }
}
