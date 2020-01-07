package com.example.bawei.gisim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.contract.IXmppUser;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.service.XmppService;
import com.example.bawei.basemodel.ui.BaseActivity;
import com.example.bawei.basemodel.ui.BaseMVPActivity;
import com.example.bawei.chartmodule.LocaltionService;
import com.example.bawei.immodule.ui.UserActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MyStartActivity extends BaseMVPActivity {
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            SharedPreferences yxh = getSharedPreferences("yxh", 0);
            boolean isDL = yxh.getBoolean("isDL", false);
            if (isDL) {
                Intent intent = new Intent(MyStartActivity.this, MainActivity.class);
                String username = yxh.getString("username", "");
                String psw = yxh.getString("psw", "");

                LogUtils.i(psw);
                if (!username.equals("")) {
                    intent.putExtra("username", username);
                }
                XmppManager.getInstance().getXmppUserManager().login(username, psw);
                startActivity(intent);

                MyStartActivity.this.finish();
            } else {
                Intent intent = new Intent(MyStartActivity.this, UserActivity.class);
                startActivityForResult(intent, 1);
            }
        }
    };

    @Override
    protected int getLayoutId() {

        return R.layout.activity_my_start;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                startService(new Intent(MyStartActivity.this,XmppService.class));
                startService(new Intent(MyStartActivity.this, LocaltionService.class));
                handler.sendEmptyMessage(101);
            }
        }).start();




    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doEvent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            boolean aBoolean = data.getBooleanExtra("boolean", false);
            if (aBoolean) {
                String username = data.getStringExtra("username");
                Intent intent = new Intent(MyStartActivity.this, MainActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                MyStartActivity.this.finish();
            }
        }
    }
}
