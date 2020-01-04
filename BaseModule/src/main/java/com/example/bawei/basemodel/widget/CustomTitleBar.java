package com.example.bawei.basemodel.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.bawei.basemodel.R;

/**
 * 自定义标题栏
 */
public class CustomTitleBar extends LinearLayout {
    private ImageView iv_customview_titlebat_left;
    private TextView tv_customview_titlebat_title;
    private TextView tv_customview_titlebat_right;
    private ImageView iv_customview_titlebat_right;

    public CustomTitleBar(Context context) {
        super(context);
    }

    public CustomTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }


    public CustomTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.customtview_title, null);
        iv_customview_titlebat_left = view.findViewById(R.id.iv_customview_titlebat_left);
        tv_customview_titlebat_title = view.findViewById(R.id.tv_customview_titlebat_title);
        tv_customview_titlebat_right = view.findViewById(R.id.tv_customview_titlebat_right);
        iv_customview_titlebat_right=view.findViewById(R.id.iv_customview_titlebat_right);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        int titleName = typedArray.getResourceId(R.styleable.CustomTitleBar_titlebarName, 0);
        int leftSrc = typedArray.getResourceId(R.styleable.CustomTitleBar_leftSrc, 0);
        int rightSrc = typedArray.getResourceId(R.styleable.CustomTitleBar_rightSrc, 0);
        boolean aBoolean = typedArray.getBoolean(R.styleable.CustomTitleBar_righttypeText, false);
        if (titleName != 0) {
            tv_customview_titlebat_title.setText(titleName);
        }
        if (0 != leftSrc) {
            iv_customview_titlebat_left.setImageResource(leftSrc);
        }
        if(aBoolean){
            if (0 != rightSrc) {
                iv_customview_titlebat_right.setImageResource(rightSrc);
            }
        }else{
            if (0 != rightSrc) {
                Log.e("asd", "aaaa");
                tv_customview_titlebat_right.setText(rightSrc);
            }
        }


        typedArray.recycle();
        this.addView(view);
    }

    //左边控件的点击
    public void leftOnclick(OnClickListener listener) {
        iv_customview_titlebat_left.setOnClickListener(listener);
    }

    //右边的点击事件
    public void rightOnclick(OnClickListener listener) {
        tv_customview_titlebat_right.setOnClickListener(listener);
    }
    public void rightivOnclick(OnClickListener listener) {
        iv_customview_titlebat_right.setOnClickListener(listener);
    }


}
