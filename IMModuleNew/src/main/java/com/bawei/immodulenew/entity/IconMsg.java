package com.bawei.immodulenew.entity;

import com.google.gson.Gson;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/9 14:03
 * @Email 1151403054@qq.com
 */
public class IconMsg extends BaseMsg {
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.ICON;
    }

    @Override
    public String getMsg() {
        return new Gson().toJson(this);
    }
}
