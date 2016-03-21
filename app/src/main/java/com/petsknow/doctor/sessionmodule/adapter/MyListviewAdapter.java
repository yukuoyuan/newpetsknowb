package com.petsknow.doctor.sessionmodule.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.utils.DateUtil;
import com.petsknow.doctor.mainmodule.bean.SeesionBean;


import java.util.List;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个问诊列表的适配器
 */
public class MyListviewAdapter extends BaseAdapter {
    private List<SeesionBean.DataEntity> list;
    private Context context;

    public MyListviewAdapter(Context context, List<SeesionBean.DataEntity> list) {
        super();
        this.list = list;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder = null;
        if (convertView == null) {
            myHolder = new MyHolder();
            convertView = View.inflate(context, R.layout.item_mlv_session, null);
            myHolder.avaturl = (ImageView) convertView.findViewById(R.id.iv_avatorurl);
            myHolder.sessiontitle = (TextView) convertView.findViewById(R.id.tv_session_title);
            myHolder.sessiondesc = (TextView) convertView.findViewById(R.id.tv_session_desc);
            myHolder.seesionstatus = (TextView) convertView.findViewById(R.id.tv_session_status);
            myHolder.seesiontime = (TextView) convertView.findViewById(R.id.tv_session_time);
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }
        //设置宠物的名字
        if (list.get(position).getPetsVo() != null) {
            myHolder.sessiontitle.setText(list.get(position).getPetsVo().getName());
        }
        //描述信息
        myHolder.sessiondesc.setText(list.get(position).getDescription());
        if (list.get(position).getBillStatus() == 5) {
            myHolder.seesionstatus.setText("已评论");
        } else if (list.get(position).getBillStatus() == 6) {
            myHolder.seesionstatus.setText("未评论");
        } else if (list.get(position).getBillStatus() == 2) {
            myHolder.seesionstatus.setText("等您回复");
        } else if (list.get(position).getBillStatus() == 3) {
            myHolder.seesionstatus.setText("已结束问诊");
        }
        /**
         * 设置时间的显示
         */
        if (list.get(position).getAcceptTime() == 0) {
            myHolder.seesiontime.setText("还没有说话");
        } else {
            long time = System.currentTimeMillis() - list.get(position).getAcceptTime();
            myHolder.seesiontime.setText(DateUtil.getKeepTimStr(time) + "前");
        }
        /**
         * 设置头像的展示
         */
        Glide.with(context).load(ConstantUrl.qiniu + list.get(position).getAvatarUrl())
                .error(R.drawable.default_icon_headphoto).into(myHolder.avaturl);
        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class MyHolder {
        ImageView avaturl;
        TextView sessiontitle;
        TextView sessiondesc;
        TextView seesionstatus;
        TextView seesiontime;
    }

}
