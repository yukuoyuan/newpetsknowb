package com.petsknow.doctor.commonmodule.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yukuo on 2016/3/16.
 * 这是一个显示网页的界面
 */
public class WebActivity extends BaseActivity implements View.OnClickListener {
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
    private int type;

    @Override
    public void initdata(Bundle extras) {
        type = extras.getInt("type");
        publicTitlebg.setVisibility(View.GONE);
        ibMainMypatient.setVisibility(View.GONE);
        tvRight.setVisibility(View.GONE);
        ivBack.setVisibility(View.VISIBLE);
        tvPublicTitle.setVisibility(View.VISIBLE);
        if (type == 1) {
            tvPublicTitle.setText("关于我们");
            String url = ConstantUrl.BaseUrl() + ConstantUrl.aboutme;
            wvMyweb.loadUrl(url);
        } else if (type == 2) {
            tvPublicTitle.setText("用户协议");
            String url = ConstantUrl.BaseUrl() + ConstantUrl.protocol;
            wvMyweb.loadUrl(url);
        }

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_web;
    }

    @Override
    @OnClick({R.id.iv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
}
