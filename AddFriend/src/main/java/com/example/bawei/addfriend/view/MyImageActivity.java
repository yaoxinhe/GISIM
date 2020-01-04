package com.example.bawei.addfriend.view;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bawei.addfriend.R;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.library.ZXing3;

public class MyImageActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_image);
        initView();
        SharedPreferences yxh = this.getSharedPreferences("yxh", 0);
        String usercode = yxh.getString("usercode", "");
        if (usercode != "") {
            Bitmap bitmap = ZXing3.newQRCode(this, usercode);
            iv.setImageBitmap(bitmap);
        }
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
    }
}
