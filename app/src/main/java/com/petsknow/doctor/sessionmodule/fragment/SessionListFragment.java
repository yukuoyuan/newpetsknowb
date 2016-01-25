package com.petsknow.doctor.sessionmodule.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.alibaba.fastjson.JSON;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.ContextUrl;
import com.petsknow.doctor.commonmodule.fragment.BaseFragment;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.commonmodule.view.MyListview;
import com.petsknow.doctor.mainmodule.bean.SeesionBean;
import com.petsknow.doctor.sessionmodule.activity.ChatActivity;
import com.petsknow.doctor.sessionmodule.adapter.MyListviewAdapter;
import com.petsknow.doctor.usermodule.manger.UserManger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个会话列表的页面
 */
public class SessionListFragment extends BaseFragment {


    private MyListview mlv_session_ing;
    private MyListview mlv_session_loading;
    private MyListview mlv_session_done;
    private SeesionBean seesionBean;
    private List<SeesionBean.DataEntity> list01 = new ArrayList<SeesionBean.DataEntity>();
    private List<SeesionBean.DataEntity> list02 = new ArrayList<SeesionBean.DataEntity>();
    private List<SeesionBean.DataEntity> list03 = new ArrayList<SeesionBean.DataEntity>();
    private MyListviewAdapter myListviewAdapter01;
    private MyListviewAdapter myListviewAdapter02;
    private MyListviewAdapter myListviewAdapter03;
    private Intent intent;

    @Override
    public void initView(View view) {
        mlv_session_ing = (MyListview) view.findViewById(R.id.mlv_session_ing);
        mlv_session_loading = (MyListview) view.findViewById(R.id.mlv_session_loading);
        mlv_session_done = (MyListview) view.findViewById(R.id.mlv_session_done);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initdata() {
        getseesiondata();
        mlv_session_ing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("toChatUsername", list01.get(position).getEasemobName());
                startActivity(intent);
            }
        });
        mlv_session_loading.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("toChatUsername", list01.get(position).getEasemobName());
                startActivity(intent);
            }
        });
        mlv_session_done.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("toChatUsername", list01.get(position).getEasemobName());
                startActivity(intent);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sessionlist, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setListener();
        initdata();
    }

    /**
     * 获取会话列表
     */
    public void getseesiondata() {
        String url = ContextUrl.BaseUrl + ContextUrl.getallseesionlist;
        RequestParams params = new RequestParams(url);
        params.addParameter("doctorId", UserManger.getUserId());
        params.addHeader("dt_id", UserManger.getUserId() + "");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                L.e("所有会话列表", o);
                seesionBean = JSON.parseObject(o, SeesionBean.class);
                if (seesionBean.getStatus() == 0) {
                    if (seesionBean.getData() != null && seesionBean.getData().size() > 0) {
                        list01.clear();
                        list02.clear();
                        list03.clear();
                        for (int i = 0; i < seesionBean.getData().size(); i++) {
                            if (seesionBean.getData().get(i).getBillStatus() == 2) {
                                list01.add(seesionBean.getData().get(i));
                            } else if (seesionBean.getData().get(i).getBillStatus() == 3) {
                                list02.add(seesionBean.getData().get(i));
                            } else if (seesionBean.getData().get(i).getBillStatus() == 5 || seesionBean.getData().get(i).getBillStatus() == 6) {
                                list03.add(seesionBean.getData().get(i));
                            }
                        }
                        initlistviewdata();
                    } else {
                        T.show(getActivity(), seesionBean.getMsg(), 0);
                    }
                } else {
                    T.show(getActivity(), seesionBean.getMsg(), 0);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                T.show(getActivity(), "网络请求超时,请稍后再试", 0);
                L.e("所有会话列表的错误信息", throwable.toString());
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
    private void initlistviewdata() {
        myListviewAdapter01 = new MyListviewAdapter(getActivity(), list01);
        mlv_session_ing.setAdapter(myListviewAdapter01);
        myListviewAdapter01.notifyDataSetChanged();
        myListviewAdapter02 = new MyListviewAdapter(getActivity(), list02);
        mlv_session_loading.setAdapter(myListviewAdapter02);
        myListviewAdapter02.notifyDataSetChanged();
        myListviewAdapter03 = new MyListviewAdapter(getActivity(), list03);
        mlv_session_done.setAdapter(myListviewAdapter03);
        myListviewAdapter03.notifyDataSetChanged();
    }
}
