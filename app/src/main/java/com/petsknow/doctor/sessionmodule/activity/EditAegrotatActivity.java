package com.petsknow.doctor.sessionmodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.EMCallBack;
import com.easemob.chat.CmdMessageBody;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMMessage;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.commonmodule.constant.Constant;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.constant.PetsknowDoctorApplication;
import com.petsknow.doctor.commonmodule.netutil.OkHttpUtil;
import com.petsknow.doctor.commonmodule.netutil.RequestPacket;
import com.petsknow.doctor.commonmodule.netutil.ResponseListener;
import com.petsknow.doctor.commonmodule.utils.DateUtil;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.sessionmodule.bean.Sendmedicalbean;
import com.petsknow.doctor.usermodule.manger.UserManger;
import com.squareup.okhttp.Request;

import butterknife.Bind;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by yukuo on 2016/3/15.
 * 这是一个编辑诊断书的界面
 */
public class EditAegrotatActivity extends BaseActivity implements View.OnClickListener {
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
    @Bind(R.id.tv_editmedical_number)
    TextView tvEditmedicalNumber;
    @Bind(R.id.tv_editmedical_inquiry_time)
    TextView tvEditmedicalInquiryTime;
    @Bind(R.id.tv_1_wmc_pet_name)
    TextView tv1WmcPetName;
    @Bind(R.id.tv_1_wmc_pet_owner_description)
    TextView tv1WmcPetOwnerDescription;
    @Bind(R.id.tv_1_wmc_doctor_first_result)
    TextView tv1WmcDoctorFirstResult;
    @Bind(R.id.tv_1_wmc_doctor_proposal)
    TextView tv1WmcDoctorProposal;
    @Bind(R.id.tv_editmedicaldoctor_name)
    TextView tvEditmedicaldoctorName;
    private int id;
    private long time;
    private String desc;
    private String petname;
    private int petid;
    private int sessionid;
    private int ownerid;
    private Intent intent;
    private int type;
    private Sendmedicalbean sendmedicalbean;
    private String tochartusername;

    @Override
    public void initdata(Bundle extras) {
        id = extras.getInt("id");
        time = extras.getLong("time");
        desc = extras.getString("desc");
        petname = extras.getString("petname");
        petid = extras.getInt("petid");
        sessionid = extras.getInt("sessionid");
        ownerid = extras.getInt("ownerid");
        tochartusername = extras.getString("toChatUsername");
        publicTitlebg.setVisibility(View.GONE);
        ibMainMypatient.setVisibility(View.GONE);
        tvRight.setVisibility(View.GONE);
        ivBack.setVisibility(View.VISIBLE);
        tvPublicTitle.setVisibility(View.VISIBLE);
        tvPublicTitle.setText("编辑诊断书");
        //设置医生的名字
        tvEditmedicaldoctorName.setText(UserManger.getUserTrueName());
        //问诊单id
        tvEditmedicalNumber.setText(id + "");
        //问诊时间
        tvEditmedicalInquiryTime.setText(DateUtil.getFormatDate(time, "yyyy-MM-dd"));
        //描述主诉信息
        tv1WmcPetOwnerDescription.setText(desc);
        //宠物名字
        tv1WmcPetName.setText(petname);
        getinquerydata();
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_editaegrotat;
    }

    @Override
    @OnClick({R.id.iv_back, R.id.bt_send_medical_case, R.id.rl_editmedical_pet_information, R.id.rl_editmediacal_description
            , R.id.rl_editmediacal_first_result, R.id.rl_editmediacal_doctor_proposal})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.bt_send_medical_case:
                //发送诊断书信息
                sendmedicalinfo();
                break;
            case R.id.rl_editmedical_pet_information:
                //跳转到宠物详情页面
                break;
            case R.id.rl_editmediacal_description:
                //跳转至编辑主诉信息的页面
                intent = new Intent(EditAegrotatActivity.this, EditInfoActivity.class);
                type = 1;
                intent.putExtra("desc", desc);
                intent.putExtra("type", type);
                startActivityForResult(intent, Constant.STARTEDITINFOFORRESULT);
                break;
            case R.id.rl_editmediacal_first_result:
                //跳转至初诊信息页面
                intent = new Intent(EditAegrotatActivity.this, EditInfoActivity.class);
                type = 2;
                intent.putExtra("toChatUsername", tochartusername);
                intent.putExtra("type", type);
                startActivityForResult(intent, Constant.STARTEDITINFOFORRESULT);
                break;
            case R.id.rl_editmediacal_doctor_proposal:
                //跳转至建议方案页面
                intent = new Intent(EditAegrotatActivity.this, EditInfoActivity.class);
                type = 3;
                intent.putExtra("toChatUsername", tochartusername);
                intent.putExtra("type", type);
                startActivityForResult(intent, Constant.STARTEDITINFOFORRESULT);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constant.STARTEDITINFOFORRESULT) {
            String info = data.getStringExtra("info");
            if (type == 1) {
                tv1WmcPetOwnerDescription.setText(info);
            } else if (type == 2) {
                tv1WmcDoctorFirstResult.setText(info);
            } else if (type == 3) {
                tv1WmcDoctorProposal.setText(info);
            }
        }
    }

    /**
     * 这是一个发送诊断书的方法
     */
    private void sendmedicalinfo() {
        String petinfo = tv1WmcPetName.getText().toString().trim();//宠物信息
        String descption = tv1WmcPetOwnerDescription.getText().toString().trim();//主诉信息
        String firstinfo = tv1WmcDoctorFirstResult.getText().toString().trim();//初诊信息
        String adviceinfo = tv1WmcDoctorProposal.getText().toString().trim();//建议方案
        if (TextUtils.isEmpty(petinfo)) {
            T.showLong(PetsknowDoctorApplication.context, "宠物名字不能为空..请查看....");
            return;
        }
        if (TextUtils.isEmpty(descption)) {
            T.showLong(PetsknowDoctorApplication.context, "宿主描述不能为空..请输入宿主描述....");
            return;
        }
        if (TextUtils.isEmpty(firstinfo)) {
            T.showLong(PetsknowDoctorApplication.context, "初诊信息不能为空..请输入初诊信息....");
            return;
        }
        if (TextUtils.isEmpty(adviceinfo)) {
            T.showLong(PetsknowDoctorApplication.context, "建议方案不能为空..请输入建议方案....");
            return;
        }
        RequestPacket requestPacket = new RequestPacket();
        requestPacket.url = ConstantUrl.BaseUrl() + ConstantUrl.sendMedical;
        requestPacket.addArgument("doctorId", UserManger.getUserId());
        requestPacket.addArgument("petId", petid);//宠物id
        requestPacket.addArgument("illnessDescription", descption);//主诉信息
        requestPacket.addArgument("illnessAnalysis", firstinfo);//初诊方案
        requestPacket.addArgument("advice", adviceinfo);//建议
        requestPacket.addArgument("registerId", id);//问诊id
        requestPacket.addArgument("ownerId", ownerid);//所属用户id
        requestPacket.addArgument("SessionId", sessionid);//会话id
        OkHttpUtil.Request(RequestPacket.POST, requestPacket, new ResponseListener<Sendmedicalbean>() {
            @Override
            public void onSuccess(Sendmedicalbean sendmedicalbean) {
                if (sendmedicalbean.getStatus() == 0) {
                    EventBus.getDefault().post("Admissions");
                    //发送cmd消息
                    sendcmdmsg(sendmedicalbean.getData().get(0).getId());
                }
            }

            @Override
            public void onFailure(Request request) {

            }
        });
        finish();
    }

    /**
     * 发送cmd消息
     *
     * @param id
     */
    private void sendcmdmsg(int id) {
        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD);
        String action = "inquiry" + id;
        CmdMessageBody cmdBody = new CmdMessageBody(action);
        cmdMsg.setReceipt(tochartusername);
        cmdMsg.setAttribute("s", "s");//支持自定义扩展
        cmdMsg.addBody(cmdBody);
        EMChatManager.getInstance().sendMessage(cmdMsg, new EMCallBack() {
            @Override
            public void onSuccess() {
                L.e("发送透传成功", "发送成功了");
            }

            @Override
            public void onError(int i, String s) {
                L.e("发送透传失败", "发送失败了");
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    /**
     * 这是一个获取问诊详情的方法
     */
    public void getinquerydata() {

    }
}
