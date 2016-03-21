package com.petsknow.doctor.sessionmodule.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.CmdMessageBody;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.easemob.chat.ImageMessageBody;
import com.easemob.chat.TextMessageBody;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.commonmodule.bean.CommonBean;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.constant.PetsknowDoctorApplication;
import com.petsknow.doctor.commonmodule.netutil.OkHttpUtil;
import com.petsknow.doctor.commonmodule.netutil.RequestPacket;
import com.petsknow.doctor.commonmodule.netutil.ResponseListener;
import com.petsknow.doctor.commonmodule.utils.KeyBoardUtils;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.commonmodule.utils.T;
import com.petsknow.doctor.commonmodule.view.imageselector.MultiImageSelectorActivity;
import com.petsknow.doctor.sessionmodule.adapter.ChartListVIewAdapter;
import com.petsknow.doctor.sessionmodule.bean.FaceContent;
import com.petsknow.doctor.sessionmodule.bean.FaceList;
import com.squareup.okhttp.Request;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by yukuo on 2016/1/25.
 * 这是一个聊天的页面
 */
public class ChatActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
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
    @Bind(R.id.lv_chart)
    RecyclerView lvChart;
    @Bind(R.id.et_chart_inputxttmsg)
    EditText et_chart_inputxttmsg;
    @Bind(R.id.bt_send_msg)
    Button bt_send_msg;
    @Bind(R.id.iv_more_sendmsg)
    ImageView iv_more_sendmsg;
    @Bind(R.id.tv_send_photomsg)
    TextView tv_send_photomsg;
    @Bind(R.id.gv_send_emojmsg)
    GridView gv_send_emojmsg;
    private List<EMMessage> emMessages = new ArrayList<>();
    private String toChatUsername;
    private EMConversation conversation;
    private ChartListVIewAdapter chartListVIewAdapter;
    private int illnessid;
    private NewMessageBroadcastReceiver msgReceiver;
    private String userAvator;
    private static final int INTENT_REQUEST_GET_IMAGES = 2;
    private long time;
    private String desc;
    private String petname;
    private int petid;
    private int sessionid;
    private int ownerid;
    private Intent intent;

    @Override
    public void initdata(Bundle extras) {
        toChatUsername = extras.getString("toChatUsername");
        illnessid = extras.getInt("id");
        userAvator = extras.getString("avator");
        time = extras.getLong("time");
        desc = extras.getString("desc");
        petname = extras.getString("petname");
        petid = extras.getInt("petid");
        sessionid = extras.getInt("sessionid");
        ownerid = extras.getInt("ownerid");
        publicTitlebg.setVisibility(View.GONE);
        ibMainMypatient.setVisibility(View.GONE);
        tvRight.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);
        tvPublicTitle.setVisibility(View.VISIBLE);
        tvPublicTitle.setText("问诊中");
        tvRight.setText("结束回话");
        et_chart_inputxttmsg.addTextChangedListener(this);
        conversation = EMChatManager.getInstance().getConversation(toChatUsername);
        getallmessage();
        gv_send_emojmsg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FaceContent faceContent = (FaceContent) parent.getAdapter().getItem(position);
                et_chart_inputxttmsg.append(faceContent.getSign());
            }
        });
    }

    private void initemmessage() {
        //只有注册了广播才能接收到新消息，目前离线消息，在线消息都是走接收消息的广播（离线消息目前无法监听，在登录以后，接收消息广播会执行一次拿到所有的离线消息）
        msgReceiver = new NewMessageBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(EMChatManager.getInstance().getNewMessageBroadcastAction());
        intentFilter.setPriority(3);
        registerReceiver(msgReceiver, intentFilter);
        EMChat.getInstance().setAppInited();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0) {
            bt_send_msg.setVisibility(View.VISIBLE);
            iv_more_sendmsg.setVisibility(View.GONE);
        } else {
            bt_send_msg.setVisibility(View.GONE);
            iv_more_sendmsg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    private class NewMessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            L.i("收到了新消息", "收到了");
            lvChart.smoothScrollToPosition(emMessages.size());
            chartListVIewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_chart;
    }

    @Override
    @OnClick({R.id.iv_back, R.id.tv_right, R.id.bt_send_msg, R.id.iv_more_sendmsg, R.id.et_chart_inputxttmsg
            , R.id.tv_send_photomsg, R.id.iv_send_face})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_right:
                //结束回话,并跳转到发送诊断书界面
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
                builder.setMessage("您确认要结束该会话吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finshMessage();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
                break;
            case R.id.bt_send_msg:
                /**
                 * 发送文本信息
                 */
                sendtxtmsg();
                break;
            case R.id.iv_more_sendmsg:
                //打开发送图片信息的按钮
                //隐藏键盘
                KeyBoardUtils.closeKeybord(et_chart_inputxttmsg, PetsknowDoctorApplication.context);
                if (tv_send_photomsg.getVisibility() == View.VISIBLE) {
                    tv_send_photomsg.setVisibility(View.GONE);
                } else {
                    tv_send_photomsg.setVisibility(View.VISIBLE);
                }
                if (gv_send_emojmsg.getVisibility() == View.VISIBLE) {
                    gv_send_emojmsg.setVisibility(View.GONE);
                }
                break;
            case R.id.et_chart_inputxttmsg:
                //输入框点击之后隐藏下边所有的东西
                tv_send_photomsg.setVisibility(View.GONE);
                gv_send_emojmsg.setVisibility(View.GONE);
                break;
            case R.id.tv_send_photomsg:
                //打开选择图片的按钮
                Intent intent = new Intent(getApplicationContext(), MultiImageSelectorActivity.class);
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
                startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);
                break;
            case R.id.iv_send_face:
                //隐藏键盘
                KeyBoardUtils.closeKeybord(et_chart_inputxttmsg, PetsknowDoctorApplication.context);
                if (tv_send_photomsg.getVisibility() == View.VISIBLE) {
                    tv_send_photomsg.setVisibility(View.GONE);
                }
                if (gv_send_emojmsg.getVisibility() == View.VISIBLE) {
                    gv_send_emojmsg.setVisibility(View.GONE);
                } else {
                    gv_send_emojmsg.setVisibility(View.VISIBLE);
                    gv_send_emojmsg.setAdapter(new FaceAdapter());
                }
                break;
        }
    }

    /**
     * 这是一个获取图片路径信息的方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTENT_REQUEST_GET_IMAGES) {
            if (resultCode == ChatActivity.RESULT_OK) {
                // 获取返回的图片列表
                List<String> paths = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                for (String path : paths) {
                    File file = new File(path);
                    if (!file.exists() || !file.isFile()) {
                        Toast.makeText(getApplicationContext(), "获取图片失败", Toast.LENGTH_SHORT).show();
                        continue;
                    }
                    sendimagemsg(path);
                }
            }
        }

    }

    /**
     * 这是一个表情的gridview的适配器
     *
     * @return
     */
    private class FaceAdapter extends BaseAdapter {

        private List<FaceContent> mList = FaceList.getInstance().getList();

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Override
        public FaceContent getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_face, null);
            FaceContent faceContent = getItem(position);
            Field field = null;
            try {
                field = R.drawable.class.getDeclaredField("ee_" + faceContent.getImage());
                int resId = Integer.parseInt(field.get(null).toString());
                ((ImageView) convertView).setImageResource(resId);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return convertView;
        }
    }

    /**
     * 这是一个发送图片消息的方法
     *
     * @param path
     */
    private void sendimagemsg(String path) {
        EMMessage message = EMMessage.createSendMessage(EMMessage.Type.IMAGE);
        ImageMessageBody body = new ImageMessageBody(new File(path));
        // 默认超过100k的图片会压缩后发给对方，可以设置成发送原图
        // body.setSendOriginalImage(true);
        message.addBody(body);
        message.setReceipt(toChatUsername);
        conversation.addMessage(message);
        EMChatManager.getInstance().sendMessage(message, new EMCallBack() {
            @Override
            public void onSuccess() {
                L.i("图片消息发送成功", "是的");
            }

            @Override
            public void onError(int i, String s) {
            }

            @Override
            public void onProgress(int i, String s) {
            }
        });
        lvChart.smoothScrollToPosition(emMessages.size());
        chartListVIewAdapter.notifyDataSetChanged();
        tv_send_photomsg.setVisibility(View.GONE);
    }

    /**
     * 这是一个结束回话的方法
     */
    private void finshMessage() {
        RequestPacket requestPacket = new RequestPacket();
        requestPacket.url = ConstantUrl.BaseUrl() + ConstantUrl.oversession;
        requestPacket.addArgument("id", illnessid);
        OkHttpUtil.Request(RequestPacket.POST, requestPacket, new ResponseListener<CommonBean>() {
            @Override
            public void onSuccess(CommonBean commonBean) {
                if (commonBean.getStatus() == 0) {
                    //结束会话成功,退出当前页面,跳转至发送诊断书的页面
                }else {
                    T.show(ChatActivity.this, commonBean.getMsg(), 0);
                }
            }

            @Override
            public void onFailure(Request request) {

            }
        });
        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD);
        String action = "DOCTORSTOPCHAT";//action可以自定义，在广播接收时可以收到
        CmdMessageBody cmdBody = new CmdMessageBody(action);
        cmdMsg.setReceipt(toChatUsername);//发送给某个人
        cmdMsg.setAttribute("a", "a");//支持自定义扩展
        cmdMsg.addBody(cmdBody);
        EMChatManager.getInstance().sendMessage(cmdMsg, new EMCallBack() {
            @Override
            public void onSuccess() {
                intent = new Intent(ChatActivity.this, EditAegrotatActivity.class);
                intent.putExtra("id", illnessid);//问诊单id
                intent.putExtra("toChatUsername", toChatUsername);
                intent.putExtra("time", time);//问诊时间
                intent.putExtra("desc", desc);//描述信息
                intent.putExtra("petname", petname);//宠物名字
                intent.putExtra("petid", petid);//宠物id
                intent.putExtra("sessionid", sessionid);//会话id
                intent.putExtra("ownerid", ownerid);//所属用户的id
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(int i, String s) {
            }

            @Override
            public void onProgress(int i, String s) {
            }
        });
        EventBus.getDefault().post("Admissions");
    }

    /**
     * 获取当前会话所有的会话列表
     */
    public void getallmessage() {
        //获取此会话的所有消息
        emMessages = conversation.getAllMessages();
        chartListVIewAdapter = new ChartListVIewAdapter(emMessages, ChatActivity.this, userAvator);
        lvChart.setLayoutManager(new LinearLayoutManager(this));
        lvChart.setAdapter(chartListVIewAdapter);
        lvChart.smoothScrollToPosition(emMessages.size());
        chartListVIewAdapter.notifyDataSetChanged();
        L.i("消息个数", emMessages.size() + "");
    }

    /**
     * 这是一个发送文本信息的方法
     */
    private void sendtxtmsg() {
        //创建一条文本消息
        EMMessage message = EMMessage.createSendMessage(EMMessage.Type.TXT);
        //设置消息body
        TextMessageBody txtBody = new TextMessageBody(et_chart_inputxttmsg.getText().toString().trim());
        message.addBody(txtBody);
        //设置接收人
        message.setReceipt(toChatUsername);
        //把消息加入到此会话对象中
        conversation.addMessage(message);
        //发送消息
        EMChatManager.getInstance().sendMessage(message, new EMCallBack() {
            @Override
            public void onSuccess() {
                L.i("消息发送成功", "是的");
                lvChart.smoothScrollToPosition(emMessages.size());
                chartListVIewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int i, String s) {
                L.i("消息发送失败", s);
            }

            @Override
            public void onProgress(int i, String s) {
            }
        });
        et_chart_inputxttmsg.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        initemmessage();
    }

    @Override
    public void onPause() {
        super.onPause();
        //反注册
        unregisterReceiver(msgReceiver);
    }
}