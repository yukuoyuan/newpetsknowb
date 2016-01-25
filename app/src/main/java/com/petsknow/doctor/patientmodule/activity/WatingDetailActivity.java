package com.petsknow.doctor.patientmodule.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.easemob.EMCallBack;
import com.easemob.chat.CmdMessageBody;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.easemob.chat.TextMessageBody;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.commonmodule.activity.PhotoBrowerActivity;
import com.petsknow.doctor.commonmodule.constant.ContextUrl;
import com.petsknow.doctor.commonmodule.utils.DateUtil;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.patientmodule.bean.WatingpatientBean;
import com.petsknow.doctor.usermodule.manger.UserManger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by yukuo on 2016/1/22.
 * 这是一个问诊详情的界面
 */
public class WatingDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton iv_back;
    private TextView tv_public_title;
    private ImageView public_titlebg;
    private ImageButton ib_main_mypatient;
    private TextView tv_right;
    private int id;
    private WatingpatientBean watingpatientBean;
    private ImageView iv_watingdetial_avator;
    private TextView tv_watingdetailusername;
    private TextView tv_watingdetaildesc;
    private LinearLayout ll_watingdetailphoto;
    private TextView tv_watingdetail_nohave;
    private ImageView iv_watingdetail_photoone;
    private ImageView iv_watingdetail_phototwo;
    private ImageView iv_watingdetail_photothree;
    private Intent intent;
    private ImageOptions options;
    private String avator;
    private ImageOptions options02;
    private TextView tv_watingdetail_pet_name;
    private TextView tv_watingdetail_pet_breed;
    private TextView tv_watingdetail_pet_age;
    private TextView tv_wating_detail_pet_sterrilization;
    private TextView tv_watingdetail_pet_in_parasite;
    private TextView tv_watingdetail_pet_needle;
    private TextView tv_watingdetail_pet_gender;
    private TextView tv_watingdetail_pet_rabies_vaccine;
    private TextView tv_watingdetail_pet_out_parasite;

    @Override
    public void initView() {
        iv_back = (ImageButton) findViewById(R.id.iv_back);
        tv_public_title = (TextView) findViewById(R.id.tv_public_title);
        public_titlebg = (ImageView) findViewById(R.id.public_titlebg);
        ib_main_mypatient = (ImageButton) findViewById(R.id.ib_main_mypatient);
        tv_right = (TextView) findViewById(R.id.tv_right);
        //头像
        iv_watingdetial_avator = (ImageView) findViewById(R.id.iv_watingdetial_avator);
        //用户名字
        tv_watingdetailusername = (TextView) findViewById(R.id.tv_watingdetailusername);
        //描述信息
        tv_watingdetaildesc = (TextView) findViewById(R.id.tv_watingdetaildesc);
        //照片信息
        ll_watingdetailphoto = (LinearLayout) findViewById(R.id.ll_watingdetailphoto);
        //没有照片的信息展示
        tv_watingdetail_nohave = (TextView) findViewById(R.id.tv_watingdetail_nohave);
        //第一个照片
        iv_watingdetail_photoone = (ImageView) findViewById(R.id.iv_watingdetail_photoone);
        //第二个照片
        iv_watingdetail_phototwo = (ImageView) findViewById(R.id.iv_watingdetail_phototwo);
        //第三个照片
        iv_watingdetail_photothree = (ImageView) findViewById(R.id.iv_watingdetail_photothree);
        //宠物姓名
        tv_watingdetail_pet_name = (TextView) findViewById(R.id.tv_watingdetail_pet_name);
        //宠物品种
        tv_watingdetail_pet_breed = (TextView) findViewById(R.id.tv_watingdetail_pet_breed);
        //宠物年龄
        tv_watingdetail_pet_age = (TextView) findViewById(R.id.tv_watingdetail_pet_age);
        //绝育状态
        tv_wating_detail_pet_sterrilization = (TextView) findViewById(R.id.tv_wating_detail_pet_sterrilization);
        //体内驱虫
        tv_watingdetail_pet_in_parasite = (TextView) findViewById(R.id.tv_watingdetail_pet_in_parasite);
        //联苗针时间
        tv_watingdetail_pet_needle = (TextView) findViewById(R.id.tv_watingdetail_pet_needle);
        //性别
        tv_watingdetail_pet_gender = (TextView) findViewById(R.id.tv_watingdetail_pet_gender);
        //狂犬疫苗
        tv_watingdetail_pet_rabies_vaccine = (TextView) findViewById(R.id.tv_watingdetail_pet_rabies_vaccine);
        //体外驱虫
        tv_watingdetail_pet_out_parasite = (TextView) findViewById(R.id.tv_watingdetail_pet_out_parasite);
    }

    @Override
    public void setListener() {
        iv_back.setOnClickListener(this);
        tv_right.setOnClickListener(this);
        iv_watingdetail_photoone.setOnClickListener(this);
        iv_watingdetail_phototwo.setOnClickListener(this);
        iv_watingdetail_photothree.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        iv_back.setVisibility(View.VISIBLE);
        tv_public_title.setVisibility(View.VISIBLE);
        public_titlebg.setVisibility(View.GONE);
        ib_main_mypatient.setVisibility(View.GONE);
        tv_right.setVisibility(View.VISIBLE);
        tv_public_title.setText("待诊详情");
        id = getIntent().getIntExtra("id", 0);
        avator = getIntent().getStringExtra("avator");
        initImageoptions();
        initImageoptions02();
        getwatingdetail();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watingdetail);
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
                intent.putExtra("url", ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(0));
                startActivity(intent);
                break;
            case R.id.iv_watingdetail_phototwo:
                intent = new Intent(WatingDetailActivity.this, PhotoBrowerActivity.class);
                intent.putExtra("url", ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(1));
                startActivity(intent);
                break;
            case R.id.iv_watingdetail_photothree:
                intent = new Intent(WatingDetailActivity.this, PhotoBrowerActivity.class);
                intent.putExtra("url", ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(2));
                startActivity(intent);
                break;
        }
    }

    /**
     * 这是一个接诊的方法
     */
    private void reception() {
        String url = ContextUrl.BaseUrl + ContextUrl.reception;
        RequestParams params = new RequestParams(url);
        params.setAsJsonContent(true);
        params.addParameter("id", id);
        params.addHeader("dt_id", UserManger.getUserId() + "");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                L.e("接诊成功", s);
                sendcmdmessage();
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                L.e("接诊失败", "网络连接超时!请稍后再试");
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

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
        String url = ContextUrl.BaseUrl + ContextUrl.getwatingdetial;
        RequestParams params = new RequestParams(url);
        params.setAsJsonContent(true);
        params.addParameter("id", id);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                L.e("问诊详情", s);
                watingpatientBean = JSON.parseObject(s, WatingpatientBean.class);
                if (watingpatientBean.getStatus() == 0) {
                    if (watingpatientBean.getData() != null && watingpatientBean.getData().size() > 0) {
                        initviewdata();
                    } else {
                        T.show(getApplicationContext(), watingpatientBean.getMsg(), 0);
                    }
                } else {
                    T.show(getApplicationContext(), watingpatientBean.getMsg(), 0);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                T.show(getApplicationContext(), "网络请求超时,请稍后再试", 0);
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 初始化界面的数据
     */
    private void initviewdata() {
        tv_watingdetailusername.setText(watingpatientBean.getData().get(0).getUserName());
        tv_watingdetaildesc.setText(watingpatientBean.getData().get(0).getDescription());
        x.image().bind(iv_watingdetial_avator, ContextUrl.qiniu + avator, options02);
        if (watingpatientBean.getData().get(0).getPhotos().size() == 0) {
            ll_watingdetailphoto.setVisibility(View.GONE);
            tv_watingdetail_nohave.setVisibility(View.VISIBLE);
        } else {
            ll_watingdetailphoto.setVisibility(View.VISIBLE);
            tv_watingdetail_nohave.setVisibility(View.GONE);
        }
        //图片的展示
        int size = watingpatientBean.getData().get(0).getPhotos().size();
        if (size == 1) {
            x.image().bind(iv_watingdetail_photoone, ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(0), options);
            iv_watingdetail_photoone.setVisibility(View.VISIBLE);
            iv_watingdetail_phototwo.setVisibility(View.GONE);
            iv_watingdetail_photothree.setVisibility(View.GONE);
        } else if (size == 2) {
            x.image().bind(iv_watingdetail_photoone, ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(0), options);
            x.image().bind(iv_watingdetail_phototwo, ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(1), options);
            iv_watingdetail_photoone.setVisibility(View.VISIBLE);
            iv_watingdetail_phototwo.setVisibility(View.VISIBLE);
            iv_watingdetail_photothree.setVisibility(View.GONE);
        } else if (size == 3) {
            x.image().bind(iv_watingdetail_photoone, ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(0), options);
            x.image().bind(iv_watingdetail_phototwo, ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(1), options);
            x.image().bind(iv_watingdetail_photothree, ContextUrl.qiniu + watingpatientBean.getData().get(0).getPhotos().get(2), options);
            iv_watingdetail_photoone.setVisibility(View.VISIBLE);
            iv_watingdetail_phototwo.setVisibility(View.VISIBLE);
            iv_watingdetail_photothree.setVisibility(View.VISIBLE);
        }
        /**
         * 填充宠物信息
         */
        //姓名
        tv_watingdetail_pet_name.setText(watingpatientBean.getData().get(0).getPetsVo().getName());
        //品种
        // tv_watingdetail_pet_breed.setText();
        //年龄
        tv_watingdetail_pet_age.setText(DateUtil.getAge(watingpatientBean.getData().get(0).getPetsVo().getBirthday()) + "岁");
        //绝育状态
        tv_wating_detail_pet_sterrilization.setText(watingpatientBean.getData().get(0).getPetsVo().getIsNeuter() == 1 ? "已绝育" : "未绝育");
        //体内驱虫时间
        tv_watingdetail_pet_in_parasite.setText(DateUtil.getpetVaccine(watingpatientBean.getData().get(0).getPetsVo().getInsideDebugTime()));
        //联苗针时间
        tv_watingdetail_pet_needle.setText(DateUtil.getpetVaccine(watingpatientBean.getData().get(0).getPetsVo().getMultiVaccineTime()));
        //性别
        tv_watingdetail_pet_gender.setText(watingpatientBean.getData().get(0).getPetsVo().getGender() == 1 ? "雌性" : "雄性");
        //狂犬疫苗
        //tv_watingdetail_pet_rabies_vaccine.setText(DateUtil.getpetVaccine(watingpatientBean.getData().get(0).getPetsVo().getMultiVaccineTime()));
        //体外驱虫
        tv_watingdetail_pet_out_parasite.setText(DateUtil.getpetVaccine(watingpatientBean.getData().get(0).getPetsVo().getOutsideDebugTime()));
    }

    private void initImageoptions() {
        options = new ImageOptions.Builder()
                //设置加载过程中的图片
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                        //设置加载失败后的图片
                .setFailureDrawableId(R.mipmap.ic_launcher)
                        //设置支持gif
                .setIgnoreGif(false)
                .build();
    }

    private void initImageoptions02() {
        options02 = new ImageOptions.Builder()
                //设置加载过程中的图片
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                        //设置加载失败后的图片
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .setCircular(true)
                        //设置支持gif
                .setIgnoreGif(false)
                .build();
    }
}
