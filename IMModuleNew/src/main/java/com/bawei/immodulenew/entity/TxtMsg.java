package com.bawei.immodulenew.entity;

import com.google.gson.Gson;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/9 13:58
 * @Email 1151403054@qq.com
 */
public class TxtMsg extends BaseMsg {
    private String txt;

    public String getTxt() {
        return txt;
    }

    public void setTxt(String msg) {
        this.txt = msg;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TXT;
    }

    @Override
    public String getMsg() {
        return new Gson().toJson(this);
    }
}
