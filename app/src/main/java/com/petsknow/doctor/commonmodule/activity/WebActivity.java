package com.petsknow.doctor.commonmodule.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.ContextUrl;

import butterknife.Bind;

/**
 * Created by yukuo on 2016/3/16.
 * 这是一个显示网页的界面
 */
public class WebActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageButton ivBack;
    @Bind(R.id.tv_public_title)
    TextView tvPublicTitle;
    @Bind(R.id.public_titlebg)
    ImageView publicTitlebg;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ib_main_mypatient)
    ImageButton ibMainMypatient;
    @Bind(R.id.wv_myweb)
    WebView wvMyweb;

    @Override
    public void initdata(Bundle extras) {
        publicTitlebg.setVisibility(View.GONE);
        ibMainMypatient.setVisibility(View.GONE);
        tvRight.setVisibility(View.GONE);
        ivBack.setVisibility(View.VISIBLE);
        tvPublicTitle.setVisibility(View.VISIBLE);
        tvPublicTitle.setText("关于我们");
        String url = ContextUrl.BaseUrl()+ContextUrl.aboutme;
        wvMyweb.loadUrl(url);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_web;
    }
}
