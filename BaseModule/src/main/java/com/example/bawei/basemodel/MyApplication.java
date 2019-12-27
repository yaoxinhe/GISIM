package com.example.bawei.basemodel;

import android.app.Application;

import com.example.bawei.basemodel.device.AppInfoConfig;
import com.example.bawei.basemodel.device.DeviceInfoConfig;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/27 16:43
 * @Email 1151403054@qq.com
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppInfoConfig.getInstance().init(this);
        DeviceInfoConfig.getInstance().init(this);
    }
}