package com.bawei.immodulenew;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.bawei.immodulenew.msg.MsgManager;
import com.bawei.immodulenew.msg.XMPPImpl;

public class ListenerService extends Service {
    public ListenerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MsgManager.getInstance().receiveMsg();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
