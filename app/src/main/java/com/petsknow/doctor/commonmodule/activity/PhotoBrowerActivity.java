package com.petsknow.doctor.commonmodule.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.petsknow.doctor.R;

import org.xutils.x;

/**
 * Created by yukuo on 2016/1/22.
 * 这是一个图片查看功能的页面
 */
public class PhotoBrowerActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_photobrower;
    private String photourl;

    @Override
    public void initView() {
        iv_photobrower = (ImageView) findViewById(R.id.iv_photobrower);
    }

    @Override
    public void setListener() {
        iv_photobrower.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        photourl = getIntent().getStringExtra("url");
        x.image().bind(iv_photobrower, photourl);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photobrower);
        initView();
        setListener();
        initdata();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_photobrower:
                onBackPressed();
                break;
        }
    }
}
