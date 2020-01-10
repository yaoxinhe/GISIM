package com.bawei.immodulenew.entity;

import com.google.gson.Gson;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/9 14:02
 * @Email 1151403054@qq.com
 */
public class AudioMsg extends BaseMsg {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.AUDIO;
    }

    @Override
    public String getMsg() {
        return new Gson().toJson(this);
    }
}
