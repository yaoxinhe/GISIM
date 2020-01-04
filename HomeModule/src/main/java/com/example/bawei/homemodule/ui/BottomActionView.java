package com.example.bawei.homemodule.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bawei6.homemodule.R;

public class BottomActionView extends LinearLayout {

    private ImageView bottom_img1;
    private ImageView bottom_img2;
    private ImageView bottom_img3;
    private ImageView bottom_img4;
    private ImageView bottom_img5;

    public BottomActionView(Context context) {
        super(context);
    }

    public BottomActionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.bottonactionview, null);
        bottom_img1=inflate.findViewById(R.id.botom_img1);
        bottom_img2=inflate.findViewById(R.id.botom_img2);
        bottom_img3=inflate.findViewById(R.id.botom_img3);
        bottom_img4=inflate.findViewById(R.id.botom_img4);
        bottom_img5=inflate.findViewById(R.id.botom_img5);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomActionView);
        int img1 = typedArray.getResourceId(R.styleable.BottomActionView_bottom_img1, 0);
        int img2 = typedArray.getResourceId(R.styleable.BottomActionView_bottom_img2, 0);
        int img3 = typedArray.getResourceId(R.styleable.BottomActionView_bottom_img3, 0);
        int img4 = typedArray.getResourceId(R.styleable.BottomActionView_bottom_img4, 0);
        int img5 = typedArray.getResourceId(R.styleable.BottomActionView_bottom_img5, 0);

        if(img1!=0){
            bottom_img1.setImageResource(img1);
        }

        if(img2!=0){
            bottom_img2.setImageResource(img2);
        }

        if(img3!=0){
            bottom_img3.setImageResource(img3);
        }
        if(img4!=0){
            bottom_img4.setImageResource(img4);
        }

        if(img5!=0){
            bottom_img5.setImageResource(img5);
        }

        typedArray.recycle();
        this.addView(inflate);
    }

    public BottomActionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void Onclick1(OnClickListener listener){
        bottom_img1.setOnClickListener(listener);
    }

    public void Onclick2(OnClickListener listener){
        bottom_img2.setOnClickListener(listener);
    }

    public void Onclick3(OnClickListener listener){
        bottom_img3.setOnClickListener(listener);
    }

    public void Onclick4(OnClickListener listener){
        bottom_img4.setOnClickListener(listener);
    }

    public void Onclick5(OnClickListener listener){
        bottom_img5.setOnClickListener(listener);
    }


}
