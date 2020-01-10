package com.bawei.immodulenew.msg;

import android.util.Log;

import com.bawei.immodulenew.entity.BaseMsg;
import com.bawei.immodulenew.entity.MsgType;
import com.bawei.immodulenew.exception.ReceiveException;
import com.bawei.immodulenew.notify.NotifyManager;
import com.bawei.immodulenew.task.TaskManager;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IMsgCallback;
import com.baweigame.xmpplibrary.entity.MsgEntity;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/9 14:10
 * @Email 1151403054@qq.com
 */
public class XMPPImpl implements IMsg {
    @Override
    public void sendMsg(final BaseMsg msg) {
        TaskManager.getInstance().doTask(new Runnable() {
            @Override
            public void run() {
                Log.e("asd","发送的方法"+msg.getMsg()+msg.getTo());
                Log.e("####",msg.getMsgType()+"");
                XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(msg.getTo(),msg.getMsg());
            }
        });

    }

    @Override
    public void receiveMsg() {
        XmppManager.getInstance().addMessageListener(new IMsgCallback() {
            @Override
            public void Success(MsgEntity msgEntity) {
                Log.e("####",msgEntity.getMsgType()+"");
                BaseMsg msg=convertMsgForMsgEntity(msgEntity);
                //通知所有观察者
                NotifyManager.getInstance().notifyAllObserver(msg);
            }

            @Override
            public void Failed(Throwable throwable) {
                try {
                    throw new ReceiveException(throwable.getMessage());
                } catch (ReceiveException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private BaseMsg convertMsgForMsgEntity(MsgEntity msgEntity) {
        MsgEntity.MsgType msgType = msgEntity.getMsgType();
        BaseMsg msg=new BaseMsg(msgEntity.getFrom(),msgEntity.getTo(),msgEntity.getMsg(), convertTypeForMsgEntity(msgType));
        return msg;
    }

    private MsgType convertTypeForMsgEntity(MsgEntity.MsgType msgType) {
        switch (msgType){
            case Img:
                return MsgType.IMG;
            case Txt:
                return MsgType.TXT;
            case Audio:
                return MsgType.AUDIO;
            case Video:
                return MsgType.VIDEO;
            case Location:
                return MsgType.LOCATION;
            case ICON:
                return MsgType.ICON;
        }
        return MsgType.OTHER;
    }
}
