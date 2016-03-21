package com.petsknow.doctor.patientmodule.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easemob.EMCallBack;
import com.easemob.chat.CmdMessageBody;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.easemob.chat.TextMessageBody;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.commonmodule.activity.PhotoBrowerActivity;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.constant.PetsknowDoctorApplication;
import com.petsknow.doctor.commonmodule.netutil.OkHttpUtil;
import com.petsknow.doctor.commonmodule.netutil.RequestPacket;
import com.petsknow.doctor.commonmodule.netutil.ResponseListener;
import com.petsknow.doctor.commonmodule.utils.DateUtil;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.commonmodule.view.MyListview;
import com.petsknow.doctor.mainmodule.activity.MainActivity;
import com.petsknow.doctor.patientmodule.bean.ListInquryListBean;
import com.petsknow.doctor.patientmodule.bean.WatingpatientBean;
import com.petsknow.doctor.sessionmodule.activity.ChatActivity;
import com.petsknow.doctor.usermodule.bean.LoginBean;
import com.petsknow.doctor.usermodule.manger.UserManger;
import com.squareup.okhttp.Request;

import butterknife.Bind;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by yukuo on 2016/1/22.
 * 这是一个问诊详情的界面
 */
public class WatingDetailActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.iv_back)
    ImageButton iv_back;
    @Bind(R.id.tv_public_title)
    TextView tv_public_title;
    @Bind(R.id.public_titlebg)
    ImageView public_titlebg;
    @Bind(R.id.ib_main_mypatient)
    ImageButton ib_main_mypatient;
    @Bind(R.id.tv_right)
    TextView tv_right;
    private int id;
    private WatingpatientBean watingpatientBean;
    @Bind(R.id.iv_watingdetial_avator)   //头像
            ImageView iv_watingdetial_avator;
    @Bind(R.id.tv_watingdetailusername) //用户名字
            TextView tv_watingdetailusername;
    @Bind(R.id.tv_watingdetaildesc)  //描述信息
            TextView tv_watingdetaildesc;
    @Bind(R.id.ll_watingdetailphoto)        //照片信息
            LinearLayout ll_watingdetailphoto;

    @Bind(R.id.tv_watingdetail_nohave) //没有照片的信息展示
            TextView tv_watingdetail_nohave;

    @Bind(R.id.iv_watingdetail_photoone)  //第一个照片
            ImageView iv_watingdetail_photoone;

    @Bind(R.id.iv_watingdetail_phototwo)  //第二个照片
            ImageView iv_watingdetail_phototwo;

    @Bind(R.id.iv_watingdetail_photothree)  //第三个照片
            ImageView iv_watingdetail_photothree;
    private Intent intent;
    private String avator;
    @Bind(R.id.tv_watingdetail_pet_name) //宠物姓名
            TextView tv_watingdetail_pet_name;

    @Bind(R.id.tv_watingdetail_pet_breed)  //宠物品种
            TextView tv_watingdetail_pet_breed;

    @Bind(R.id.tv_watingdetail_pet_age)  //宠物年龄
            TextView tv_watingdetail_pet_age;

    @Bind(R.id.tv_wating_detail_pet_sterrilization) //绝育状态
            TextView tv_wating_detail_pet_sterrilization;

    @Bind(R.id.tv_watingdetail_pet_in_parasite)  //体内驱虫
            TextView tv_watingdetail_pet_in_parasite;

    @Bind(R.id.tv_watingdetail_pet_needle)  //联苗针时间
            TextView tv_watingdetail_pet_needle;
    @Bind(R.id.tv_watingdetail_pet_gender)  //性别
            TextView tv_watingdetail_pet_gender;
    @Bind(R.id.tv_watingdetail_pet_rabies_vaccine)  //狂犬疫苗
            TextView tv_watingdetail_pet_rabies_vaccine;
    @Bind(R.id.tv_watingdetail_pet_out_parasite)  //体外驱虫
            TextView tv_watingdetail_pet_out_parasite;
    @Bind(R.id.mlv_watingdetail_history_list)
    MyListview mlv_watingdetail_history_list;

    @Override
    public void initdata(Bundle extras) {
        iv_back.setVisibility(View.VISIBLE);
        tv_public_title.setVisibility(View.VISIBLE);
        public_titlebg.setVisibility(View.GONE);
        ib_main_mypatient.setVisibility(View.GONE);
        tv_right.setVisibility(View.VISIBLE);
        tv_public_title.setText("待诊详情");
        id = getIntent().getIntExtra("id", 0);
        avator = getIntent().getStringExtra("avator");
        getwatingdetail();

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_watingdetail;
    }

    @Override
    @OnClick({R.id.iv_back, R.id.tv_right, R.id.iv_watingdetail_photoone, R.id.iv_watingdetail_phototwo
            , R.id.iv_watingdetail_photothree})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_right:
                //接诊
                if (watingpatientBean.getData().get(0).getBillStatus() != 1) {
                    String s = "该问诊单无法被接诊";
                    if (watingpatientBean.getData().get(0).getBillStatus() == 2) {
                        s = "该问诊单已被其他医生接手";
                    } else if (watingpatientBean.getData().get(0).getBillStatus() == 4) {
                        s = "该问诊已被取消";
                    }
                    T.show(getApplicationContext(), s, 0);
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        WatingDetailActivity.this);
                builder.setTitle("提示");
                builder.setMessage("你确定要接诊吗?");
                builder.setPositiveButton("确认",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //接诊
                                reception();
                                dialog.dismiss();
                            }
                        });
                builder.setNegativeButton("取消", null).show();
                break;
            case R.id.iv_watingdetail_photoone:
                intent = new Intent(WatingDetailActivity.this, PhotoBrowerActivity.class);
                intent.putExtra("url", ConstantUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(0));
                startActivity(intent);
                break;
            case R.id.iv_watingdetail_phototwo:
                intent = new Intent(WatingDetailActivity.this, PhotoBrowerActivity.class);
                intent.putExtra("url", ConstantUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(1));
                startActivity(intent);
                break;
            case R.id.iv_watingdetail_photothree:
                intent = new Intent(WatingDetailActivity.this, PhotoBrowerActivity.class);
                intent.putExtra("url", ConstantUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(2));
                startActivity(intent);
                break;
        }
    }

    /**
     * 这是一个接诊的方法
     */
    private void reception() {
        RequestPacket requestPacket = new RequestPacket();
        requestPacket.url = ConstantUrl.BaseUrl() + ConstantUrl.reception;
        requestPacket.addArgument("id", id);
        OkHttpUtil.Request(RequestPacket.POST, requestPacket, new ResponseListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                sendcmdmessage();
            }

            @Override
            public void onFailure(Request request) {
                sendcmdmessage();
            }
        });
    }

    /**
     * 发送一条cmd消息
     */
    private void sendcmdmessage() {
        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD);
        String action = "doctor" + UserManger.getUserId();//action可以自定义，在广播接收时可以收到
        CmdMessageBody cmdBody = new CmdMessageBody(action);
        String toUsername = watingpatientBean.getData().get(0).getEasemobName();//发送给某个人
        cmdMsg.setReceipt(toUsername);
        cmdMsg.setAttribute("a", "a");//支持自定义扩展
        cmdMsg.addBody(cmdBody);
        EMChatManager.getInstance().sendMessage(cmdMsg, new EMCallBack() {
            @Override
            public void onSuccess() {
                L.e("接诊透传消息", "发送成功");
                sendmessage();
            }

            @Override
            public void onError(int i, String s) {
                L.e("接诊透传消息", s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
        //跳转页面
        intent = new Intent(WatingDetailActivity.this, ChatActivity.class);
        intent.putExtra("toChatUsername", watingpatientBean.getData().get(0).getEasemobName());//聊天对方的环信账号
        intent.putExtra("id", watingpatientBean.getData().get(0).getId());//问诊单id
        intent.putExtra("avator", watingpatientBean.getData().get(0).getAvatarUrl());//对方用户的头像
        intent.putExtra("time", watingpatientBean.getData().get(0).getCreatingTime());//问诊时间
        intent.putExtra("desc", watingpatientBean.getData().get(0).getDescription());//描述信息
        intent.putExtra("petname", watingpatientBean.getData().get(0).getPetsVo().getName());//宠物名字
        intent.putExtra("petid", watingpatientBean.getData().get(0).getPetsVo().getId());//宠物id
        intent.putExtra("sessionid", watingpatientBean.getData().get(0).getSessionId());//会话id
        intent.putExtra("ownerid", watingpatientBean.getData().get(0).getOwnerId());//所属用户的id
        startActivity(intent);
        EventBus.getDefault().post("Admissions");
        finish();
    }

    /*
            发送一条消息
             */
    private void sendmessage() {
        //获取到与聊天人的会话对象。参数username为聊天人的userid或者groupid，后文中的username皆是如此
        EMConversation conversation = EMChatManager.getInstance().getConversation(UserManger.getUserEaseMobName());
        //创建一条文本消息
        EMMessage message = EMMessage.createSendMessage(EMMessage.Type.TXT);
        //设置消息body
        TextMessageBody txtBody = new TextMessageBody(watingpatientBean.getData().get(0).getUserName() + "您好,我是宠知道派来的医生,下面的事就交给我吧.");
        message.addBody(txtBody);
        //设置接收人
        message.setReceipt(watingpatientBean.getData().get(0).getEasemobName());
        //把消息加入到此会话对象中
        conversation.addMessage(message);
        //发送消息
        EMChatManager.getInstance().sendMessage(message, new EMCallBack() {
            @Override
            public void onSuccess() {
                L.e("接诊消息发送", "发送成功");
            }

            @Override
            public void onError(int i, String s) {
                L.e("接诊消息发送", s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    /**
     * 获取问诊详情的界面
     */
    public void getwatingdetail() {
        RequestPacket requestPacket = new RequestPacket();
        requestPacket.url = ConstantUrl.BaseUrl() + ConstantUrl.getwatingdetial;
        requestPacket.addArgument("id", id);
        OkHttpUtil.Request(RequestPacket.GET, requestPacket, new ResponseListener<WatingpatientBean>() {
            @Override
            public void onSuccess(WatingpatientBean watingpatientBeann) {
                watingpatientBean = watingpatientBeann;
                if (watingpatientBeann.getStatus() == 0) {
                    if (watingpatientBeann.getData() != null && watingpatientBeann.getData().size() > 0) {
                        initviewdata();
                        gethistorylist();
                    } else {
                        T.show(getApplicationContext(), watingpatientBeann.getMsg(), 0);
                    }
                } else {
                    T.show(getApplicationContext(), watingpatientBeann.getMsg(), 0);
                }
            }

            @Override
            public void onFailure(Request request) {

            }
        });
    }

    /**
     * 初始化界面的数据
     */
    private void initviewdata() {
        tv_watingdetailusername.setText(this.watingpatientBean.getData().get(0).getUserName());
        tv_watingdetaildesc.setText(this.watingpatientBean.getData().get(0).getDescription());
        Glide.with(this).load(ConstantUrl.qiniu + avator)
                .error(R.drawable.default_icon_headphoto).into(iv_watingdetial_avator);
        if (this.watingpatientBean.getData().get(0).getPhotos().size() == 0) {
            ll_watingdetailphoto.setVisibility(View.GONE);
            tv_watingdetail_nohave.setVisibility(View.VISIBLE);
        } else {
            ll_watingdetailphoto.setVisibility(View.VISIBLE);
            tv_watingdetail_nohave.setVisibility(View.GONE);
        }
        //图片的展示
        int size = this.watingpatientBean.getData().get(0).getPhotos().size();
        if (size == 1) {
            Glide.with(this).load(ConstantUrl.qiniu + this.watingpatientBean.getData().get(0).getPhotos().get(0))
                    .centerCrop().into(iv_watingdetail_photoone);
            iv_watingdetail_photoone.setVisibility(View.VISIBLE);
            iv_watingdetail_phototwo.setVisibility(View.GONE);
            iv_watingdetail_photothree.setVisibility(View.GONE);
        } else if (size == 2) {
            Glide.with(this).load(ConstantUrl.qiniu + this.watingpatientBean.getData().get(0).getPhotos().get(0)).centerCrop().into(iv_watingdetail_photoone);
            Glide.with(this).load(ConstantUrl.qiniu + this.watingpatientBean.getData().get(0).getPhotos().get(1)).centerCrop().into(iv_watingdetail_phototwo);
            iv_watingdetail_photoone.setVisibility(View.VISIBLE);
            iv_watingdetail_phototwo.setVisibility(View.VISIBLE);
            iv_watingdetail_photothree.setVisibility(View.GONE);
        } else if (size == 3) {
            Glide.with(this).load(ConstantUrl.qiniu + this.watingpatientBean.getData().get(0).getPhotos().get(0)).centerCrop().into(iv_watingdetail_photoone);
            Glide.with(this).load(ConstantUrl.qiniu + this.watingpatientBean.getData().get(0).getPhotos().get(1)).centerCrop().into(iv_watingdetail_phototwo);
            Glide.with(this).load(ConstantUrl.qiniu + this.watingpatientBean.getData().get(0).getPhotos().get(2)).centerCrop().into(iv_watingdetail_photothree);
            iv_watingdetail_photoone.setVisibility(View.VISIBLE);
            iv_watingdetail_phototwo.setVisibility(View.VISIBLE);
            iv_watingdetail_photothree.setVisibility(View.VISIBLE);
        }
        /**
         * 填充宠物信息
         */
        //姓名
        tv_watingdetail_pet_name.setText(this.watingpatientBean.getData().get(0).getPetsVo().getName());
        //品种
        if (MainActivity.getPetTypeName(this.watingpatientBean.getData().get(0).getPetsVo().getSpecies()) == null) {
            tv_watingdetail_pet_breed.setText("未知");
        }else {
            tv_watingdetail_pet_breed.setText(MainActivity.getPetTypeName(this.watingpatientBean.getData().get(0).getPetsVo().getSpecies()));
        }

        //年龄
        tv_watingdetail_pet_age.setText(DateUtil.getAge(this.watingpatientBean.getData().get(0).getPetsVo().getBirthday()) + "岁");
        //绝育状态
        tv_wating_detail_pet_sterrilization.setText(this.watingpatientBean.getData().get(0).getPetsVo().getIsNeuter() == 1 ? "已绝育" : "未绝育");
        //体内驱虫时间
        tv_watingdetail_pet_in_parasite.setText(DateUtil.getpetVaccine(this.watingpatientBean.getData().get(0).getPetsVo().getInsideDebugTime()));
        //联苗针时间
        tv_watingdetail_pet_needle.setText(DateUtil.getpetVaccine(this.watingpatientBean.getData().get(0).getPetsVo().getMultiVaccineTime()));
        //性别
        tv_watingdetail_pet_gender.setText(this.watingpatientBean.getData().get(0).getPetsVo().getGender() == 1 ? "雌性" : "雄性");
        //狂犬疫苗
        // tv_watingdetail_pet_rabies_vaccine.setText(DateUtil.getpetVaccine(watingpatientBean.getData().get(0).getPetsVo().getMultiVaccineTime()));
        //体外驱虫
        tv_watingdetail_pet_out_parasite.setText(DateUtil.getpetVaccine(this.watingpatientBean.getData().get(0).getPetsVo().getOutsideDebugTime()));
    }

    /**
     * 获取历史问诊记录的方法
     */
    public void gethistorylist() {
        RequestPacket requestPacket = new RequestPacket();
        requestPacket.url = ConstantUrl.BaseUrl() + ConstantUrl.gethistoryinqurylist;
        requestPacket.addArgument("petsId", watingpatientBean.getData().get(0).getPetsVo().getId());
        OkHttpUtil.Request(RequestPacket.POST, requestPacket, new ResponseListener<ListInquryListBean>() {
            @Override
            public void onSuccess(ListInquryListBean listInquryListBean) {
                if (listInquryListBean.getData() != null &&
                        listInquryListBean.getData().size() > 0 && listInquryListBean.getStatus() == 0) {
                    PatientAdapter patientAdapter = new PatientAdapter(listInquryListBean);
                    mlv_watingdetail_history_list.setAdapter(patientAdapter);
                } else {
                    T.showLong(PetsknowDoctorApplication.context, listInquryListBean.getMsg());
                }

            }

            @Override
            public void onFailure(Request request) {

            }
        });
    }

    class PatientAdapter extends BaseAdapter {

        ListInquryListBean listInquryListBean;

        public PatientAdapter(ListInquryListBean listInquryListBean) {
            this.listInquryListBean = listInquryListBean;
        }

        @Override
        public int getCount() {
            return listInquryListBean.getData().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_list_history_inqury, null);
                holder = new ViewHolder();
                holder.textView = (TextView) convertView.findViewById(R.id.item_patient_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textView.setText(listInquryListBean.getData().get(position).getDescription());
            return convertView;
        }


        class ViewHolder {
            TextView textView;
        }
    }
}
