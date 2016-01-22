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

/**
 * Created by yukuo on 2016/1/22.
 * 这是一个患者的页面
 */
public class PatientActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout tab_patient;
    private ViewPager vp_patient;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private ImageButton iv_back;
    private TextView tv_public_title;
    private ImageButton ib_main_mypatient;
    private ImageView public_titlebg;

    @Override
    public void initView() {
        tab_patient = (TabLayout) findViewById(R.id.tab_patient);
        vp_patient = (ViewPager) findViewById(R.id.vp_patient);
        iv_back = (ImageButton) findViewById(R.id.iv_back);
        tv_public_title = (TextView) findViewById(R.id.tv_public_title);
        public_titlebg = (ImageView) findViewById(R.id.public_titlebg);
        ib_main_mypatient = (ImageButton) findViewById(R.id.ib_main_mypatient);
    }

    @Override
    public void setListener() {
        iv_back.setOnClickListener(this);
    }

    @Override
    public void initdata() {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        initView();
        setListener();
        initdata();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
}
