package com.bawei.immodulenew.msg;

import android.util.Log;

import com.bawei.immodulenew.entity.BaseMsg;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/9 14:42
 * @Email 1151403054@qq.com
 */
public class MsgManager {
    private static volatile MsgManager instance;
    private MsgManager(){}
    public static MsgManager getInstance(){
        if (null==instance){
            synchronized (MsgManager.class){
                if (null==instance){
                    instance=new MsgManager();
                }
            }
        }
        return instance;
    }

    private IMsg msg;

    /**
     * 初始化过程推荐在applicaiton中进行
     * @param _msg
     */
    public void init(IMsg _msg){
        msg=_msg;
    }
//    public MsgManager(IMsg _msg){
//        msg=_msg;
//    }

    /**
     * 发消息
     * @param msg 消息实体
     */
    public void sendMsg(BaseMsg msg){
        this.msg.sendMsg(msg);
    }

    /**
     * 接收消息
     */
    public void receiveMsg(){
        this.msg.receiveMsg();
    }
}
