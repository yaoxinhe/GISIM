package com.example.bawei.chartmodule.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/5 17:07
 * @Email 1151403054@qq.com
 */
public class MyChartBean implements MultiItemEntity {
    private int type;
    private String message;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MyChartBean(int type, String message) {
        this.type = type;
        this.message = message;
    }

    @Override
    public int getItemType() {
        return getType();
    }
}
