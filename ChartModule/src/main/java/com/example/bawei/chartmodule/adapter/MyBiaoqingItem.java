package com.example.bawei.chartmodule.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bawei.chartmodule.R;
import com.example.bawei.chartmodule.bean.BianqingBean;

import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/6 20:05
 * @Email 1151403054@qq.com
 */
public class MyBiaoqingItem extends BaseQuickAdapter<String, BaseViewHolder> {
    public MyBiaoqingItem(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.biaoqing_tv,item);
    }
}
