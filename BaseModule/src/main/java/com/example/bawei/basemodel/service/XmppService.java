package com.example.bawei.basemodel.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.baweigame.xmpplibrary.XmppManager;
import com.example.bawei.basemodel.log.LogUtils;

public class XmppService extends Service {
    public XmppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            XmppManager instance = XmppManager.getInstance();
            if (instance.checkConnect()) {
                instance.addConnectionListener();
                LogUtils.e( XmppManager.getInstance().toString());
                LogUtils.e("已完成初始化");
            }
        }).start();
        return START_STICKY;
    }
}
