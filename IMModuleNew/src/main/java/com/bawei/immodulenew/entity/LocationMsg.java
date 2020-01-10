package com.bawei.immodulenew.entity;

import com.google.gson.Gson;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/9 14:03
 * @Email 1151403054@qq.com
 */
public class LocationMsg extends BaseMsg {
    private Double lon;
    private Double lat;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.LOCATION;
    }

    @Override
    public String getMsg() {
        return new Gson().toJson(this);
    }
}
