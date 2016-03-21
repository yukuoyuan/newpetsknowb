package com.petsknow.doctor.patientmodule.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.fragment.BaseFragment;
import com.petsknow.doctor.commonmodule.netutil.OkHttpUtil;
import com.petsknow.doctor.commonmodule.netutil.RequestPacket;
import com.petsknow.doctor.commonmodule.netutil.ResponseListener;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.patientmodule.adapter.MyPatientlistViewAdapter;
import com.petsknow.doctor.patientmodule.bean.MypatientBean;
import com.petsknow.doctor.usermodule.manger.UserManger;
import com.squareup.okhttp.Request;

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
        RequestPacket requestPacket = new RequestPacket();
        requestPacket.url = ConstantUrl.BaseUrl() + ConstantUrl.Mypatient;
        requestPacket.addArgument("doctorId", UserManger.getUserId());
        OkHttpUtil.Request(RequestPacket.GET, requestPacket, new ResponseListener<MypatientBean>() {
            @Override
            public void onSuccess(MypatientBean mypatientbean) {
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
            public void onFailure(Request request) {
                srl_mypatient.setRefreshing(false);
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
