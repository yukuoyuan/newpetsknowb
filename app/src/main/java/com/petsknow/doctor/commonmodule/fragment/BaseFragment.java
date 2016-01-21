package com.petsknow.doctor.commonmodule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petsknow.doctor.commonmodule.utils.L;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个基础的fragment
 */
public abstract class BaseFragment extends Fragment {
    public abstract void initView(View view);

    public abstract void setListener();

    public abstract void initdata();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e("oncreat", "oncreat");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.e("onCreateView", "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        L.e("onViewCreated", "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        L.e("onActivityCreated", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        L.e("onStart", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        L.e("onResume", "onResume");
    }


    @Override
    public void onPause() {
        super.onPause();
        L.e("onPause", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        L.e("onStop", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.e("onDestroyView", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.e("onDestroy", "onDestroy");
    }
}
