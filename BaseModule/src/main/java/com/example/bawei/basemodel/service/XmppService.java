package com.example.bawei.basemodel.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.baweigame.xmpplibrary.XmppManager;
import com.example.bawei.basemodel.IMyAidlInterface;
import com.example.bawei.basemodel.log.LogUtils;

public class XmppService extends Service {
    private MyBinder myBinder;
    public XmppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        myBinder = new MyBinder();
        return myBinder;
    }
    ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            startService(new Intent(XmppService.this, RemoteService.class));
            bindService(new Intent(XmppService.this, RemoteService.class),serviceConnection, Context.BIND_IMPORTANT);
        }
    };
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(this, RemoteService.class),serviceConnection, Context.BIND_IMPORTANT);
        startDo();
        return START_STICKY;

    }

    private void startDo() {
        new Thread(() -> {
            XmppManager instance = XmppManager.getInstance();
            if (instance.checkConnect()) {
                instance.addConnectionListener();
                LogUtils.e( XmppManager.getInstance().toString());
                LogUtils.e("已完成初始化");
            }
        }).start();
    }

    private class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public String getData(String name) throws RemoteException {
            return "";
        }
    }
}
