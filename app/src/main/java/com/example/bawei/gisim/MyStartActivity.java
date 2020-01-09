package com.example.bawei.gisim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.baweigame.xmpplibrary.XmppManager;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.service.RemoteService;
import com.example.bawei.basemodel.service.XmppService;
import com.example.bawei.basemodel.ui.BaseMVPActivity;
import com.example.bawei.immodule.ui.UserActivity;

/**
 * 这个是引导页判断如果是第一次登录跳转到主界面如果是非第二次登陆跳转到主界面
 */
public class MyStartActivity extends BaseMVPActivity {
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            /**
             * 拿到sp里面储存的数据
             */
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

        //双服务保活起订双服务保活
        //本地服务是启动Xmpp初始化的服务
        //远程服务是用来计算位置坐标判断移动距离超过metedata标签下定义的距离就直接上传服务器
        new Thread(new Runnable() {
            @Override
            public void run() {
                startService(new Intent(MyStartActivity.this,XmppService.class));
                startService(new Intent(MyStartActivity.this, RemoteService.class));
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
