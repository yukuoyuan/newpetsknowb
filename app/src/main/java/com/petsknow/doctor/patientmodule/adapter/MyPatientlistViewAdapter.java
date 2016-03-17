package com.petsknow.doctor.patientmodule.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.utils.DateUtil;
import com.petsknow.doctor.patientmodule.bean.MypatientBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by yukuo on 2016/1/22.
 * 这是一个患者界面的适配器
 */
public class MyPatientlistViewAdapter extends BaseAdapter {
    private List<MypatientBean.DataEntity> list;
    private Context context;
    private ImageOptions options;

    public MyPatientlistViewAdapter(Context context, List<MypatientBean.DataEntity> list) {
        super();
        this.list = list;
        this.context = context;
        initImageoptions();
    }

    private void initImageoptions() {
        options = new ImageOptions.Builder()
                //设置加载过程中的图片
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                        //设置加载失败后的图片
                .setFailureDrawableId(R.mipmap.ic_launcher)
                        //设置显示圆形图片
                .setCircular(true)
                        //设置支持gif
                .setIgnoreGif(false)
                .build();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder;
        if (convertView == null) {
            myHolder = new MyHolder();
            convertView = View.inflate(context, R.layout.item_mypatient, null);
            myHolder.avaturl = (ImageView) convertView.findViewById(R.id.iv_petavaturl);
            myHolder.petname = (TextView) convertView.findViewById(R.id.tv_petname);
            myHolder.tiem = (TextView) convertView.findViewById(R.id.tv_mypatienttime);
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }
        /**
         * 头像
         */
        x.image().bind(myHolder.avaturl, ConstantUrl.qiniu + list.get(position).getPets().getPetsAvator(), options);
        //宠物名字
        myHolder.petname.setText(list.get(position).getPets().getName());
        //时间
        myHolder.tiem.setText(DateUtil.getKeepTimStr(System.currentTimeMillis() - list.get(position).getCtime()) + "前");
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
        TextView petname;
        TextView tiem;
    }

}
