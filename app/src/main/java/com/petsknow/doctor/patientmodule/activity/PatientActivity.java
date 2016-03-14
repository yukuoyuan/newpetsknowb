package com.petsknow.doctor.patientmodule.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.patientmodule.adapter.MyPatientPagerAdapter;
import com.petsknow.doctor.patientmodule.fragment.MypatientFragment;
import com.petsknow.doctor.patientmodule.fragment.WatingPatientFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yukuo on 2016/1/22.
 * 这是一个患者的页面
 */
public class PatientActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.tab_patient)
    TabLayout tab_patient;
    @Bind(R.id.vp_patient)
    ViewPager vp_patient;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    @Bind(R.id.iv_back)
    ImageButton iv_back;
    @Bind(R.id.tv_public_title)
    TextView tv_public_title;
    @Bind(R.id.ib_main_mypatient)
    ImageButton ib_main_mypatient;
    @Bind(R.id.public_titlebg)
    ImageView public_titlebg;

    @Override
    public void initdata(Bundle extras) {
        iv_back.setVisibility(View.VISIBLE);
        tv_public_title.setVisibility(View.VISIBLE);
        public_titlebg.setVisibility(View.GONE);
        ib_main_mypatient.setVisibility(View.GONE);
        tv_public_title.setText("患者");
        fragments.add(new WatingPatientFragment());
        fragments.add(new MypatientFragment());
        list.add("待珍");
        list.add("我的患者");
        tab_patient.setTabMode(TabLayout.MODE_FIXED);
        tab_patient.addTab(tab_patient.newTab().setText(list.get(0)));
        tab_patient.addTab(tab_patient.newTab().setText(list.get(1)));
        MyPatientPagerAdapter myPatientPagerAdapter = new MyPatientPagerAdapter(getSupportFragmentManager(), fragments, list);
        vp_patient.setAdapter(myPatientPagerAdapter);
        tab_patient.setupWithViewPager(vp_patient);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_patient;
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
