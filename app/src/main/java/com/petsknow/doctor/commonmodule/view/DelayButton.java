package com.petsknow.doctor.commonmodule.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.Button;

import com.petsknow.doctor.R;

/**
 * Created by yukuo on 2016/3/16.
 * 这是一个倒计时的按钮
 */
public class DelayButton extends Button {
    public interface OnDelayListener {
        //开始计时
        public void onStartDelay(int second);

        //计时中
        public void onRun(int second);

        //计时完成
        public void onFinish();
    }

    private boolean mIsDelaying = false;
    private OnDelayListener mListener;

    public DelayButton(Context context) {
        super(context);
    }

    public DelayButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DelayButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setDelayListener(OnDelayListener listener) {
        mListener = listener;
    }

    public boolean isDelay(){
        return mIsDelaying;
    }

    public void delay(final int second) {
        if (mListener != null) {
            mListener.onStartDelay(second);
        }
        mIsDelaying = true;
        DelayButton.this.setEnabled(false);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what < 0) {
                    stop();
                } else {
                    if (mListener != null)
                        mListener.onRun(msg.what);
                    (DelayButton.this).setText(msg.what + "秒后重试");
                }
            }
        };
        new Thread(new Runnable() {
            int reduceTime = second;

            @Override
            public void run() {
                while (reduceTime > 0 && mIsDelaying) {
                    handler.sendEmptyMessage(reduceTime);
                    reduceTime--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(-1);
            }
        }).start();
    }

    public void stop() {
        mIsDelaying = false;
        (DelayButton.this).setText(getResources().getString(R.string.get_verify_code));
        DelayButton.this.setEnabled(true);
        if (mListener != null)
            mListener.onFinish();
    }
}
