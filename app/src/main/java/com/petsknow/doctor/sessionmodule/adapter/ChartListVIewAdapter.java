package com.petsknow.doctor.sessionmodule.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.chat.EMMessage;
import com.easemob.chat.ImageMessageBody;
import com.easemob.chat.TextMessageBody;
import com.easemob.util.DateUtils;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.activity.PhotoBrowerActivity;
import com.petsknow.doctor.commonmodule.constant.Constant;
import com.petsknow.doctor.commonmodule.constant.ConstantUrl;
import com.petsknow.doctor.commonmodule.glide.GlideUtils;
import com.petsknow.doctor.commonmodule.utils.L;
import com.petsknow.doctor.sessionmodule.utils.SmileUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by yukuo on 2016/3/5.
 * 这是一个聊天界面的适配器
 */
public class ChartListVIewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<EMMessage> list;
    private Intent intent;
    private Activity activity;
    private String avator;

    public ChartListVIewAdapter(List<EMMessage> list, Activity activity, String avator) {
        super();
        this.list = list;
        this.activity = activity;
        this.avator = avator;
    }

    /**
     * 这是一个添加一条数据并刷新界面的方法
     *
     * @param msg
     */
    public void addData(EMMessage msg) {
        list.add(list.size(), msg);
        notifyItemInserted(list.size());
    }


    @Override
    public int getItemViewType(int position) {
        EMMessage msg = list.get(position);
        if (msg.direct == EMMessage.Direct.SEND) {
            if (msg.getType() == EMMessage.Type.TXT) {
                return Constant.SENDTXT;
            } else if (msg.getType() == EMMessage.Type.IMAGE) {
                return Constant.SENDIMAGE;
            }
        } else if (msg.direct == EMMessage.Direct.RECEIVE) {
            if (msg.getType() == EMMessage.Type.TXT) {
                return Constant.FROMTXT;
            } else if (msg.getType() == EMMessage.Type.IMAGE) {
                return Constant.FROMIMAGE;
            }
        }

        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == Constant.SENDTXT) {
            //发送者文本
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_send_txt, parent, false);
            return new SendTxtVIewHolder(view);
        } else if (viewType == Constant.SENDIMAGE) {
            //发送者图片
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_send_image, parent, false);
            return new SendImageVIewHolder(view);
        } else if (viewType == Constant.FROMTXT) {
            //接受者文本
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_from_txt, parent, false);
            return new FromTxtVIewHolder(view);
        } else if (viewType == Constant.FROMIMAGE) {
            //接受者图片
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_from_image, parent, false);
            return new FromImageVIewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SendTxtVIewHolder) {
            TextMessageBody txtbody = (TextMessageBody) list.get(position).getBody();
            String content = txtbody.getMessage();

            ((SendTxtVIewHolder) holder).tv_item_send_txt.setText(SmileUtils.getSmiledText(activity, content));
            // 两条消息时间离得如果稍长，显示时间
            if (position == 0) {

            } else {
                if (DateUtils.isCloseEnough(list.get(position).getMsgTime(), list.get(position - 1).getMsgTime())) {
                    ((SendTxtVIewHolder) holder).tv_send_msg_date.setVisibility(View.GONE);
                } else {
                    ((SendTxtVIewHolder) holder).tv_send_msg_date.setText(DateUtils.getTimestampString(new Date(list.get(position).getMsgTime())));
                    ((SendTxtVIewHolder) holder).tv_send_msg_date.setVisibility(View.VISIBLE);
                }
            }

        } else if (holder instanceof SendImageVIewHolder) {
            final ImageMessageBody imageMessageBody = (ImageMessageBody) list.get(position).getBody();
            L.i("图片地址", imageMessageBody.getLocalUrl() + "****" + imageMessageBody.getRemoteUrl());
//            Glide.with(PetsknowDoctorApplication.context).load(imageMessageBody.getLocalUrl())
//                    .centerCrop().into(((SendImageVIewHolder) holder).iv_item_send_image);
            GlideUtils.roundImageCenterGroup(imageMessageBody.getLocalUrl(), ((SendImageVIewHolder) holder).iv_item_send_image, 8);
            ((SendImageVIewHolder) holder).iv_item_send_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(activity, PhotoBrowerActivity.class);
                    intent.putExtra("url", imageMessageBody.getLocalUrl());
                    activity.startActivity(intent);
                }
            });
        } else if (holder instanceof FromTxtVIewHolder) {
            TextMessageBody txtbody = (TextMessageBody) list.get(position).getBody();
            String content = txtbody.getMessage();
            ((FromTxtVIewHolder) holder).tv_item_from_txt.setText(SmileUtils.getSmiledText(activity, content));
            GlideUtils.circleImage(ConstantUrl.qiniu + avator, ((FromTxtVIewHolder) holder).from_person_avator);
            // Glide.with(PetsknowDoctorApplication.context).load(ConstantUrl.qiniu + avator).error(R.drawable.default_icon_headphoto).into(((FromTxtVIewHolder) holder).from_person_avator);
            // 两条消息时间离得如果稍长，显示时间
            if (position == 0) {

            } else {
                if (DateUtils.isCloseEnough(list.get(position).getMsgTime(), list.get(position - 1).getMsgTime())) {
                    ((FromTxtVIewHolder) holder).tv_from_msg_date.setVisibility(View.GONE);
                } else {
                    ((FromTxtVIewHolder) holder).tv_from_msg_date.setText(DateUtils.getTimestampString(new Date(list.get(position).getMsgTime())));
                    ((FromTxtVIewHolder) holder).tv_from_msg_date.setVisibility(View.VISIBLE);
                }
            }
        } else if (holder instanceof FromImageVIewHolder) {
            final ImageMessageBody imageMessageBody = (ImageMessageBody) list.get(position).getBody();
            L.i("图片地址接受者", imageMessageBody.getLocalUrl() + "****" + imageMessageBody.getRemoteUrl());
//            Glide.with(PetsknowDoctorApplication.context).load(ConstantUrl.qiniu + avator)
//                    .error(R.drawable.default_icon_headphoto).into(((FromImageVIewHolder) holder).iv_item_from_image);
            GlideUtils.circleImage(ConstantUrl.qiniu + avator,((FromImageVIewHolder) holder).iv_item_from_image);
            GlideUtils.roundImageCenterGroup(ConstantUrl.qiniu + imageMessageBody.getRemoteUrl(), ((FromImageVIewHolder) holder).from_person_avator, 8);
//            Glide.with(PetsknowDoctorApplication.context).load(ConstantUrl.qiniu + imageMessageBody.getRemoteUrl())
//                    .centerCrop().into(((FromImageVIewHolder) holder).from_person_avator);
            ((FromImageVIewHolder) holder).iv_item_from_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(activity, PhotoBrowerActivity.class);
                    intent.putExtra("url", imageMessageBody.getRemoteUrl());
                    activity.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SendTxtVIewHolder extends RecyclerView.ViewHolder {
        private TextView tv_item_send_txt;
        private TextView tv_send_msg_date;

        public SendTxtVIewHolder(View view) {
            super(view);
            tv_item_send_txt = (TextView) view.findViewById(R.id.tv_item_send_txt);
            tv_send_msg_date = (TextView) view.findViewById(R.id.tv_send_msg_date);
        }
    }

    class SendImageVIewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_item_send_image;

        public SendImageVIewHolder(View view) {
            super(view);
            iv_item_send_image = (ImageView) view.findViewById(R.id.iv_send_image);
        }
    }

    class FromTxtVIewHolder extends RecyclerView.ViewHolder {
        private TextView tv_item_from_txt;
        private ImageView from_person_avator;
        private TextView tv_from_msg_date;

        public FromTxtVIewHolder(View view) {
            super(view);
            tv_item_from_txt = (TextView) view.findViewById(R.id.tv_item_from_txt);
            from_person_avator = (ImageView) view.findViewById(R.id.from_person_avator);
            tv_from_msg_date = (TextView) view.findViewById(R.id.tv_from_msg_date);
        }
    }

    class FromImageVIewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_item_from_image;
        private ImageView from_person_avator;

        public FromImageVIewHolder(View view) {
            super(view);
            iv_item_from_image = (ImageView) view.findViewById(R.id.iv_from_image);
            from_person_avator = (ImageView) view.findViewById(R.id.from_person_avator);
        }
    }
}
