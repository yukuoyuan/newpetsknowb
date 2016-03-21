package com.petsknow.doctor.commonmodule.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.petsknow.doctor.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yukuo on 2016/1/22.
 * 这是一个图片查看功能的页面
 */
public class PhotoBrowerActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.iv_photobrower)
    ImageView iv_photobrower;
    private String photourl;

    @Override
    public void initdata(Bundle extras) {
        photourl = extras.getString("url");
        Glide.with(this).load(photourl).centerCrop().into(iv_photobrower);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_photobrower;
    }

    @Override
    @OnClick({R.id.iv_photobrower})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_photobrower:
                onBackPressed();
                break;
        }
    }
}
