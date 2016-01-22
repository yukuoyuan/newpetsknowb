package com.petsknow.doctor.patientmodule.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yukuo on 2016/1/21.
 * 这是一个viewpager的适配器
 */
public class MyPatientPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> list;

    public MyPatientPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> list) {
        super(fm);
        this.fragments = fragments;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position % list.size());
    }
}
