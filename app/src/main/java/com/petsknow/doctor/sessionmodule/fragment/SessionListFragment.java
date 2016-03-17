package com.petsknow.doctor.sessionmodule.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.fastjson.JSON;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.fragment.BaseFragment;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.commonmodule.view.MyListview;
import com.petsknow.doctor.mainmodule.bean.SeesionBean;
import com.petsknow.doctor.sessionmodule.activity.ChatActivity;
import com.petsknow.doctor.sessionmodule.activity.EditAegrotatActivity;
import com.petsknow.doctor.sessionmodule.adapter.MyListviewAdapter;
import com.petsknow.doctor.usermodule.manger.UserManger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.greenrobot.event.EventBus;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个会话列表的页面
 */
public class SessionListFragment extends BaseFragment {

    @Bind(R.id.mlv_session_ing)
    MyListview mlv_session_ing;
    @Bind(R.id.mlv_session_loading)
    MyListview mlv_session_loading;
    @Bind(R.id.mlv_session_done)
    MyListview mlv_session_done;
    private SeesionBean seesionBean;
    private List<SeesionBean.DataEntity> list01 = new ArrayList<SeesionBean.DataEntity>();
    private List<SeesionBean.DataEntity> list02 = new ArrayList<SeesionBean.DataEntity>();
    private List<SeesionBean.DataEntity> list03 = new ArrayList<SeesionBean.DataEntity>();
    private MyListviewAdapter myListviewAdapter01;
    private MyListviewAdapter myListviewAdapter02;
    private MyListviewAdapter myListviewAdapter03;
    private Intent intent;

    @Override
    public void initdata(Bundle arguments) {
        getseesiondata();
        mlv_session_ing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("toChatUsername", list01.get(position).getEasemobName());//聊天对方的环信账号
                intent.putExtra("id", list01.get(position).getId());//问诊单id
                intent.putExtra("avator", list01.get(position).getAvatarUrl());//对方用户的头像
                intent.putExtra("time", list01.get(position).getCreatingTime());//问诊时间
                intent.putExtra("desc", list01.get(position).getDescription());//描述信息
                intent.putExtra("petname", list01.get(position).getPetsVo().getName());//宠物名字
                intent.putExtra("petid", list01.get(position).getPetsVo().getId());//宠物id
                intent.putExtra("sessionid", list01.get(position).getSessionId());//会话id
                intent.putExtra("ownerid", list01.get(position).getOwnerId());//所属用户的id
                L.e("聊天人的环信账号01", list01.get(position).getEasemobName() + "**" + list01.get(position).getId());
                getActivity().startActivity(intent);
            }
        });
        mlv_session_loading.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getActivity(), EditAegrotatActivity.class);
                intent.putExtra("id", list02.get(position).getId());//问诊单id
                intent.putExtra("toChatUsername", list02.get(position).getEasemobName());
                intent.putExtra("time", list02.get(position).getCreatingTime());//问诊时间
                intent.putExtra("desc", list02.get(position).getDescription());//描述信息
                intent.putExtra("petname", list02.get(position).getPetsVo().getName());//宠物名字
                intent.putExtra("petid", list02.get(position).getPetsVo().getId());//宠物id
                intent.putExtra("sessionid", list02.get(position).getSessionId());//会话id
                intent.putExtra("ownerid", list02.get(position).getOwnerId());//所属用户的id
                getActivity().startActivity(intent);
            }
        });
        mlv_session_done.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    public void onEvent(String event) {
        if (event.equals("Admissions")) {
            getseesiondata();
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

    @Override
    public int getContentLayout() {
        return R.layout.fragment_sessionlist;
    }

    /**
     * 获取会话列表
     */
    public void getseesiondata() {
        String url = ConstantUrl.BaseUrl() + ConstantUrl.getallseesionlist;
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
