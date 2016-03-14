package com.petsknow.doctor.sessionmodule.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easemob.chat.EMMessage;
import com.easemob.chat.TextMessageBody;
import com.petsknow.doctor.R;
import com.petsknow.doctor.commonmodule.constant.Constant;

import java.util.List;

/**
 * Created by yukuo on 2016/3/5.
 * 这是一个聊天界面的适配器
 */
public class ChartListVIewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<EMMessage> list;

    public ChartListVIewAdapter(List<EMMessage> list) {
        super();
        this.list = list;
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
            ((SendTxtVIewHolder) holder).tv_item_send_txt.setText(content);
        } else if (holder instanceof SendImageVIewHolder) {

        } else if (holder instanceof FromTxtVIewHolder) {
            TextMessageBody txtbody = (TextMessageBody) list.get(position).getBody();
            String content = txtbody.getMessage();
            ((FromTxtVIewHolder) holder).tv_item_from_txt.setText(content);
        } else if (holder instanceof FromImageVIewHolder) {
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SendTxtVIewHolder extends RecyclerView.ViewHolder {
        private TextView tv_item_send_txt;

        public SendTxtVIewHolder(View view) {
            super(view);
            tv_item_send_txt = (TextView) view.findViewById(R.id.tv_item_send_txt);
        }
    }

    class SendImageVIewHolder extends RecyclerView.ViewHolder {
        public SendImageVIewHolder(View view) {
            super(view);
        }
    }

    class FromTxtVIewHolder extends RecyclerView.ViewHolder {
        private TextView tv_item_from_txt;

        public FromTxtVIewHolder(View view) {
            super(view);
            tv_item_from_txt = (TextView) view.findViewById(R.id.tv_item_from_txt);
        }
    }

    class FromImageVIewHolder extends RecyclerView.ViewHolder {
        public FromImageVIewHolder(View view) {
            super(view);
        }
    }
}
