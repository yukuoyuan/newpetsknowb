package com.petsknow.doctor.patientmodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.commonmodule.activity.PhotoBrowerActivity;
import com.petsknow.doctor.commonmodule.constant.ContextUrl;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.patientmodule.bean.WatingpatientBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by yukuo on 2016/1/22.
 * 这是一个问诊详情的界面
 */
public class WatingDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton iv_back;
    private TextView tv_public_title;
    private ImageView public_titlebg;
    private ImageButton ib_main_mypatient;
    private TextView tv_right;
    private int id;
    private Object watingdetail;
    private WatingpatientBean watingpatientBean;
    private ImageView iv_watingdetial_avator;
    private TextView tv_watingdetailusername;
    private TextView tv_watingdetaildesc;
    private LinearLayout ll_watingdetailphoto;
    private TextView tv_watingdetail_nohave;
    private ImageView iv_watingdetail_photoone;
    private ImageView iv_watingdetail_phototwo;
    private ImageView iv_watingdetail_photothree;
    private Intent intent;

    @Override
    public void initView() {
        iv_back = (ImageButton) findViewById(R.id.iv_back);
        tv_public_title = (TextView) findViewById(R.id.tv_public_title);
        public_titlebg = (ImageView) findViewById(R.id.public_titlebg);
        ib_main_mypatient = (ImageButton) findViewById(R.id.ib_main_mypatient);
        tv_right = (TextView) findViewById(R.id.tv_right);
        //头像
        iv_watingdetial_avator = (ImageView) findViewById(R.id.iv_watingdetial_avator);
        //用户名字
        tv_watingdetailusername = (TextView) findViewById(R.id.tv_watingdetailusername);
        //描述信息
        tv_watingdetaildesc = (TextView) findViewById(R.id.tv_watingdetaildesc);
        //照片信息
        ll_watingdetailphoto = (LinearLayout) findViewById(R.id.ll_watingdetailphoto);
        //没有照片的信息展示
        tv_watingdetail_nohave = (TextView) findViewById(R.id.tv_watingdetail_nohave);
        //第一个照片
        iv_watingdetail_photoone = (ImageView) findViewById(R.id.iv_watingdetail_photoone);
        //第二个照片
        iv_watingdetail_phototwo = (ImageView) findViewById(R.id.iv_watingdetail_phototwo);
        //第三个照片
        iv_watingdetail_photothree = (ImageView) findViewById(R.id.iv_watingdetail_photothree);

    }

    @Override
    public void setListener() {
        iv_back.setOnClickListener(this);
        tv_right.setOnClickListener(this);
        iv_watingdetail_photoone.setOnClickListener(this);
        iv_watingdetail_phototwo.setOnClickListener(this);
        iv_watingdetail_photothree.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        iv_back.setVisibility(View.VISIBLE);
        tv_public_title.setVisibility(View.VISIBLE);
        public_titlebg.setVisibility(View.GONE);
        ib_main_mypatient.setVisibility(View.GONE);
        tv_right.setVisibility(View.VISIBLE);
        tv_public_title.setText("待诊详情");
        id = getIntent().getIntExtra("id", 0);
        getwatingdetail();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watingdetail);
        initView();
        setListener();
        initdata();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_right:
                //接诊
                onBackPressed();
                break;
            case R.id.iv_watingdetail_photoone:
                intent = new Intent(WatingDetailActivity.this, PhotoBrowerActivity.class);
                intent.putExtra("url", ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(0));
                startActivity(intent);
                break;
            case R.id.iv_watingdetail_phototwo:
                intent = new Intent(WatingDetailActivity.this, PhotoBrowerActivity.class);
                intent.putExtra("url", ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(1));
                startActivity(intent);
                break;
            case R.id.iv_watingdetail_photothree:
                intent = new Intent(WatingDetailActivity.this, PhotoBrowerActivity.class);
                intent.putExtra("url", ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(2));
                startActivity(intent);
                break;
        }
    }

    /**
     * 获取问诊详情的界面
     */
    public void getwatingdetail() {
        String url = ContextUrl.BaseUrl + ContextUrl.getwatingdetial;
        RequestParams params = new RequestParams(url);
        params.setAsJsonContent(true);
        params.addParameter("id", id);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                L.e("问诊详情", s);
                watingpatientBean = JSON.parseObject(s, WatingpatientBean.class);
                if (watingpatientBean.getStatus() == 0) {
                    if (watingpatientBean.getData() != null && watingpatientBean.getData().size() > 0) {
                        initviewdata();
                    } else {
                        T.show(getApplicationContext(), watingpatientBean.getMsg(), 0);
                    }
                } else {
                    T.show(getApplicationContext(), watingpatientBean.getMsg(), 0);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                T.show(getApplicationContext(), "网络请求超时,请稍后再试", 0);
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
     * 初始化界面的数据
     */
    private void initviewdata() {
        tv_watingdetailusername.setText(watingpatientBean.getData().get(0).getUserName());
        tv_watingdetaildesc.setText(watingpatientBean.getData().get(0).getDescription());
        x.image().bind(iv_watingdetial_avator, ContextUrl.qiniu + watingpatientBean.getData().get(0).getAvatarUrl());
        if (watingpatientBean.getData().get(0).getPhotos().size() == 0) {
            ll_watingdetailphoto.setVisibility(View.GONE);
            tv_watingdetail_nohave.setVisibility(View.VISIBLE);
        } else {
            ll_watingdetailphoto.setVisibility(View.VISIBLE);
            tv_watingdetail_nohave.setVisibility(View.GONE);
        }
        //图片的展示
        int size = watingpatientBean.getData().get(0).getPhotos().size();
        if (size == 1) {
            iv_watingdetail_photoone.setVisibility(View.VISIBLE);
            iv_watingdetail_phototwo.setVisibility(View.GONE);
            iv_watingdetail_photothree.setVisibility(View.GONE);
        } else if (size == 2) {
            iv_watingdetail_photoone.setVisibility(View.VISIBLE);
            iv_watingdetail_phototwo.setVisibility(View.VISIBLE);
            iv_watingdetail_photothree.setVisibility(View.GONE);
        } else if (size == 3) {
            iv_watingdetail_photoone.setVisibility(View.VISIBLE);
            iv_watingdetail_phototwo.setVisibility(View.VISIBLE);
            iv_watingdetail_photothree.setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < size; i++) {
            if (size == 1) {
                x.image().bind(iv_watingdetail_photoone, ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(0));
            } else if (size == 2) {
                x.image().bind(iv_watingdetail_phototwo, ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(1));
            } else if (size == 3) {
                x.image().bind(iv_watingdetail_photothree, ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(2));
            }
        }
    }
}
