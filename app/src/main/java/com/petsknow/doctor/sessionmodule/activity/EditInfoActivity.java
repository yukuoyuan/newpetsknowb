package com.petsknow.doctor.sessionmodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.commonmodule.constant.Constant;
import com.petsknow.doctor.commonmodule.constant.PetsknowDoctorApplication;
import com.petsknow.doctor.commonmodule.utils.T;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yukuo on 2016/3/16.
 * 这是一个编辑信息的页面
 */
public class EditInfoActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.et_editinfo)
    EditText etEditinfo;
    private int type;
    private String desc;
    private Intent intent;
    private String tochartusername;

    @Override
    public void initdata(Bundle extras) {
        publicTitlebg.setVisibility(View.GONE);
        ibMainMypatient.setVisibility(View.GONE);
        ivBack.setVisibility(View.VISIBLE);
        tvPublicTitle.setVisibility(View.VISIBLE);
        type = extras.getInt("type");
        desc = extras.getString("desc");
        tochartusername = extras.getString("toChatUsername");
        if (type == 1) {
            tvPublicTitle.setText("主诉信息编辑");
            etEditinfo.setText(desc);
            tvRight.setVisibility(View.GONE);
        } else if (type == 2) {
            tvPublicTitle.setText("初诊信息编辑");
            tvRight.setText("聊天记录");
            tvRight.setVisibility(View.VISIBLE);
        } else if (type == 3) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("聊天记录");
            tvPublicTitle.setText("建议方案编辑");
        }

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_editinfo;
    }

    @Override
    @OnClick({R.id.iv_editinfo_ok, R.id.iv_back, R.id.tv_right})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_editinfo_ok:
                String info = etEditinfo.getText().toString().trim();
                if (TextUtils.isEmpty(info)) {
                    T.showLong(PetsknowDoctorApplication.context, "请输入您要输入的信息");
                    return;
                }
                intent = new Intent(EditInfoActivity.this, EditAegrotatActivity.class);
                intent.putExtra("info", info);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_right:
                //跳转至聊天记录页面
                intent = new Intent(EditInfoActivity.this, SeesionListCookieActivity.class);
                intent.putExtra("toChatUsername", tochartusername);
                startActivityForResult(intent, Constant.STARTSEEIONCOOKIEFORRESULT);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.STARTSEEIONCOOKIEFORRESULT && resultCode == RESULT_OK) {
            etEditinfo.append(data.getStringExtra("msg"));
        }
    }
}
