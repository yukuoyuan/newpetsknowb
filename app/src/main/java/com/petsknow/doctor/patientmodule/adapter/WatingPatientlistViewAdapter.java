package com.petsknow.doctor.patientmodule.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.ContextUrl;
import com.petsknow.doctor.commonmodule.utils.DateUtil;
import com.petsknow.doctor.patientmodule.bean.MypatientBean;
import com.petsknow.doctor.patientmodule.bean.WatingpatientBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by yukuo on 2016/1/22.
 * 这是一个患者界面的适配器
 */
public class WatingPatientlistViewAdapter extends BaseAdapter {
    private List<WatingpatientBean.DataEntity> list;
    private Context context;
    private ImageOptions options;
    private ImageOptions options02;
    public WatingPatientlistViewAdapter(Context context, List<WatingpatientBean.DataEntity> list) {
        super();
        this.list = list;
        this.context = context;
        initImageoptions();
        initImageoptions02();
    }

    private void initImageoptions() {
        options = new ImageOptions.Builder()
                //设置加载过程中的图片
                .setLoadingDrawableId(R.drawable.main_mypatient)
                        //设置加载失败后的图片
                .setFailureDrawableId(R.drawable.main_mypatient)

                .setCircular(true)
                        //设置支持gif
                .setIgnoreGif(false)
                .build();
    }
    private void initImageoptions02() {
        options02 = new ImageOptions.Builder()
                //设置加载过程中的图片
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                        //设置加载失败后的图片
                .setFailureDrawableId(R.mipmap.ic_launcher)
                        //设置支持gif
                .setIgnoreGif(false)
                .build();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder;
        if (convertView == null) {
            myHolder = new MyHolder();
            convertView = View.inflate(context, R.layout.item_watingpatient, null);
            myHolder.iv_nohavept_avator = (ImageView) convertView.findViewById(R.id.iv_wating_petavaturl_nopt);
            myHolder.iv_nohavept_petsex = (ImageView) convertView.findViewById(R.id.iv_petsex_nopt);
            myHolder.tv_nohavept_username = (TextView) convertView.findViewById(R.id.tv_watingusername_nopt);
            myHolder.tv_nohavept_petname = (TextView) convertView.findViewById(R.id.tv_watingpetname_nopt);
            myHolder.tv_nohavept_status = (TextView) convertView.findViewById(R.id.tv_wating_status_nopt);
            myHolder.tv_nohavept_desc = (TextView) convertView.findViewById(R.id.tv_wating_desc_nopt);
            myHolder.ll_nohavept = (LinearLayout) convertView.findViewById(R.id.ll_wating_nohavept);

            myHolder.iv_havept_avator = (ImageView) convertView.findViewById(R.id.iv_wating_petavaturl_pt);
            myHolder.iv_havept_petsex = (ImageView) convertView.findViewById(R.id.iv_petsex_pt);
            myHolder.tv_havept_username = (TextView) convertView.findViewById(R.id.tv_watingusername_pt);
            myHolder.tv_havept_petname = (TextView) convertView.findViewById(R.id.tv_watingpetname_pt);
            myHolder.tv_havept_status = (TextView) convertView.findViewById(R.id.tv_wating_status_pt);
            myHolder.tv_havept_desc = (TextView) convertView.findViewById(R.id.tv_wating_desc_pt);
            myHolder.ll_havept = (LinearLayout) convertView.findViewById(R.id.ll_wating_havept);
            myHolder.iv_havept_photo  = (ImageView) convertView.findViewById(R.id.tv_wating_photos_pt);
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }
        if (list.get(position).getPhotos().size() == 0) {
            //没有问诊的照片
            myHolder.ll_nohavept.setVisibility(View.VISIBLE);
            myHolder.ll_havept.setVisibility(View.GONE);
            //用户头像
            x.image().bind(myHolder.iv_nohavept_avator, ContextUrl.qiniu + list.get(position).getAvatarUrl(), options);

            //用户名字
            myHolder.tv_nohavept_username.setText(list.get(position).getUserName());
            //宠物名字和年龄
            myHolder.tv_nohavept_petname.setText(list.get(position).getPetsVo().getName() + "(" + DateUtil.getAge(list.get(position).getPetsVo().getBirthday()) + "岁)");
            //宠物性别
            myHolder.iv_nohavept_petsex.setImageResource(list.get(position).getPetsVo().getGender() == 2 ?
                    R.drawable.wating_pet_man :
                    R.drawable.wating_pet_women);
            //状态展示
            if (list.get(position).getBillStatus() == 2) {
                myHolder.tv_nohavept_status.setText("问诊中");
            } else if (list.get(position).getBillStatus() == 1) {
                myHolder.tv_nohavept_status.setText("已候诊" + DateUtil.getKeepTimStr(System.currentTimeMillis() - list.get(position).getCreatingTime()));
            } else {
                myHolder.tv_nohavept_status.setText("已结束问诊");
            }
            //描述信息
            myHolder.tv_nohavept_desc.setText(list.get(position).getDescription());

        } else {
            //有问诊照片
            myHolder.ll_nohavept.setVisibility(View.GONE);
            myHolder.ll_havept.setVisibility(View.VISIBLE);
            //用户头像
            x.image().bind(myHolder.iv_havept_avator, ContextUrl.qiniu + list.get(position).getAvatarUrl(), options);
            //问诊图像
            x.image().bind(myHolder.iv_havept_photo, ContextUrl.qiniu + list.get(position).getPhotos().get(0), options02);
            //用户名字
            myHolder.tv_havept_username.setText(list.get(position).getUserName());
            //宠物名字和年龄
            myHolder.tv_havept_petname.setText(list.get(position).getPetsVo().getName() + "(" + DateUtil.getAge(list.get(position).getPetsVo().getBirthday()) + "岁)");
            //宠物性别
            myHolder.iv_havept_petsex.setImageResource(list.get(position).getPetsVo().getGender() == 2 ?
                    R.drawable.wating_pet_man :
                    R.drawable.wating_pet_women);
            //状态展示
            if (list.get(position).getBillStatus() == 2) {
                myHolder.tv_havept_status.setText("问诊中");
            } else if (list.get(position).getBillStatus() == 1) {
                myHolder.tv_havept_status.setText("已候诊" + DateUtil.getKeepTimStr(System.currentTimeMillis() - list.get(position).getCreatingTime()));
            } else {
                myHolder.tv_havept_status.setText("已结束问诊");
            }
            //描述信息
            myHolder.tv_havept_desc.setText(list.get(position).getDescription());
        }
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
        LinearLayout ll_nohavept;
        ImageView iv_nohavept_avator;
        ImageView iv_nohavept_petsex;
        TextView tv_nohavept_username;
        TextView tv_nohavept_petname;
        TextView tv_nohavept_status;
        TextView tv_nohavept_desc;
        LinearLayout ll_havept;
        ImageView iv_havept_avator;
        ImageView iv_havept_petsex;
        ImageView iv_havept_photo;
        TextView tv_havept_username;
        TextView tv_havept_petname;
        TextView tv_havept_status;
        TextView tv_havept_desc;
    }

}