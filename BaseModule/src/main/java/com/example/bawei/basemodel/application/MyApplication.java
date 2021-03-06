package com.example.bawei.basemodel.application;

import android.app.Application;

import androidx.core.provider.FontRequest;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.text.FontRequestEmojiCompatConfig;

import com.bawei.immodulenew.msg.MsgManager;
import com.bawei.immodulenew.msg.XMPPImpl;
import com.bawei6.common.Config;
import com.example.bawei.basemodel.BuildConfig;
import com.example.bawei.basemodel.R;
import com.example.bawei.basemodel.device.AliyunUtils;
import com.example.bawei.basemodel.device.AppInfoConfig;
import com.example.bawei.basemodel.device.DeviceInfoConfig;
import com.example.bawei.basemodel.toast.ToastUtils;
import com.example.library.ZXing3;
import com.squareup.leakcanary.LeakCanary;

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
        ZXing3.init(this);
        AliyunUtils.getInstance().init(this);
        Config.getInstance().getSP(this);
        FontRequest fontRequest = new FontRequest("com.example.fontprovider", "com.example", "emoji compat Font Query", R.array.com_google_android_gms_fonts_certs);
        EmojiCompat.Config config = new FontRequestEmojiCompatConfig(this, fontRequest);
        EmojiCompat.init(config);
        LeakCanary.install(this);
        MsgManager.getInstance().init(new XMPPImpl());
        ToastUtils.getInstance().init(this);

    }
}
