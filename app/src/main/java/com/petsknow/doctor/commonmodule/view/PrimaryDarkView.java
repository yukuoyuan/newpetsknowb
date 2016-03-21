package com.petsknow.doctor.commonmodule.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.petsknow.doctor.commonmodule.utils.ScreenUtils;

/**
 * Created by yukuo on 2016/3/18.
 */
public class PrimaryDarkView extends LinearLayout {

    public PrimaryDarkView(Context context) {
        super(context);
        setInit();
    }

    public PrimaryDarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setInit();
    }

    public PrimaryDarkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setInit();
    }

    private void setInit() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setPadding(getPaddingLeft(),
                    getPaddingTop() + ScreenUtils.getStatusHeight(getContext()),
                    getPaddingRight(),
                    getPaddingBottom());
        }
    }

}

