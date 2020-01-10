package com.bawei.immodulenew.msg;

import com.bawei.immodulenew.entity.BaseMsg;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/9 13:53
 * @Email 1151403054@qq.com
 */
public interface IMsg {
    /**
     * 发消息
     * @param msg 消息实体
     */
    void sendMsg(BaseMsg msg);

    /**
     * 接收消息
     */
    void receiveMsg();
}
