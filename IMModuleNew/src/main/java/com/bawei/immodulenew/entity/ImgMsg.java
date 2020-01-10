package com.bawei.immodulenew.entity;

import com.google.gson.Gson;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/9 14:01
 * @Email 1151403054@qq.com
 */
public class ImgMsg extends BaseMsg {
    private String imgPath;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.IMG;
    }

    @Override
    public String getMsg() {
        return new Gson().toJson(this);
    }
}
