package com.petsknow.doctor.commonmodule.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by yukuo on 2016/1/20.
 * 这是一个基础的类
 */
public abstract class BaseActivity extends AppCompatActivity {
    public abstract void initdata(Bundle extras);

    /**
     * 这是一个获取页面布局的方法
     * @return
     */
    public abstract int getContentLayout() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        ButterKnife.bind(this);
        initdata(getIntent().getExtras());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
