package com.example.bawei.basemodel.chartmodule.bean;

import com.bawei.immodulenew.entity.BaseMsg;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/5 17:07
 * @Email 1151403054@qq.com
 */
public class MyChartBean implements MultiItemEntity {
    private int type;
    private BaseMsg baseMsg;

    public MyChartBean(int type, BaseMsg baseMsg) {
        this.type = type;
        this.baseMsg = baseMsg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BaseMsg getBaseMsg() {
        return baseMsg;
    }

    public void setBaseMsg(BaseMsg baseMsg) {
        this.baseMsg = baseMsg;
    }

    @Override
    public int getItemType() {
        return getType();
    }
}
