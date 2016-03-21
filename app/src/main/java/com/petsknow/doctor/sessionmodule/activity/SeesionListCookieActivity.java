package com.petsknow.doctor.sessionmodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.easemob.chat.TextMessageBody;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.BaseActivity;
import com.petsknow.doctor.sessionmodule.bean.SeesionCookieListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yukuo on 2016/3/17.
 * 这是一个查看聊天记录并选取内容粘贴的页面
 */
public class SeesionListCookieActivity extends BaseActivity implements View.OnClickListener {

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
    @Bind(R.id.rcl_seesion_cookie)
    RecyclerView rclSeesionCookie;
    private String toChatUsername;
    private EMConversation conversation;
    private List<EMMessage> emMessages = new ArrayList<>();
    private List<SeesionCookieListBean> seesionCookieListBeanList = new ArrayList<>();

    @Override
    public void initdata(Bundle extras) {
        toChatUsername = extras.getString("toChatUsername");
        publicTitlebg.setVisibility(View.GONE);
        ibMainMypatient.setVisibility(View.GONE);
        ivBack.setVisibility(View.VISIBLE);
        tvPublicTitle.setVisibility(View.VISIBLE);
        tvRight.setVisibility(View.VISIBLE);
        tvPublicTitle.setText("聊天记录");
        tvRight.setText("确定");
        //获取此会话的所有消息
        conversation = EMChatManager.getInstance().getConversation(toChatUsername);
        emMessages = conversation.getAllMessages();
        for (int i = 0; i < emMessages.size(); i++) {
            if (emMessages.get(i).getType() == EMMessage.Type.TXT) {
                TextMessageBody txtbody = (TextMessageBody) emMessages.get(i).getBody();
                SeesionCookieListBean seesionCookieListBean = new SeesionCookieListBean();
                seesionCookieListBean.setText(txtbody.getMessage());
                seesionCookieListBeanList.add(seesionCookieListBean);
            }
        }
        //设置布局管理器
        rclSeesionCookie.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        rclSeesionCookie.setAdapter(new SeesionCookieAdapter());
        //设置Item增加、移除动画
        rclSeesionCookie.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_seesionlist;
    }

    @Override
    @OnClick({R.id.tv_right, R.id.iv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right:
                String text = "";
                for (int i = 0; i < seesionCookieListBeanList.size(); i++) {
                    if (seesionCookieListBeanList.get(i).isFlag()) {
                        text = text + "\n" + seesionCookieListBeanList.get(i).getText();
                    }
                }
                Intent intent = new Intent(SeesionListCookieActivity.this, EditInfoActivity.class);
                intent.putExtra("msg", text);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    class SeesionCookieAdapter extends RecyclerView.Adapter<SeesionCookieAdapter.MyViewHolder> {

        @Override
        public SeesionCookieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    SeesionListCookieActivity.this).inflate(R.layout.item_seesioncookie, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(SeesionCookieAdapter.MyViewHolder holder, final int position) {
            holder.tv.setText(seesionCookieListBeanList.get(position).getText());
            holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    seesionCookieListBeanList.get(position).setFlag(isChecked);
                }
            });
        }

        @Override
        public int getItemCount() {
            return seesionCookieListBeanList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;
            CheckBox cb;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.item_sesioncookie);
                cb = (CheckBox) view.findViewById(R.id.cb_item_sesioncookie);
            }
        }
    }
}
