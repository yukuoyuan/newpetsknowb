package com.petsknow.doctor.sessionmodule.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.petsknow.doctor.R;
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
