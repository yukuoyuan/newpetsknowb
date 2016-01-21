package com.petsknow.doctor.commonmodule.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yukuo on 2016/1/20.
 * 这是一个基础的类
 */
public abstract class BaseActivity extends AppCompatActivity {
    public abstract void initView();

    public abstract void setListener();

    public abstract void initdata();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
}
