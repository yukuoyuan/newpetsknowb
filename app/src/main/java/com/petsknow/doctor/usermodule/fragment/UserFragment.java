package com.petsknow.doctor.usermodule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.fragment.BaseFragment;

/**
 * Created by yukuo on 2016/1/21.
 */
public class UserFragment extends BaseFragment {


    @Override
    public void initView(View view) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initdata() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
