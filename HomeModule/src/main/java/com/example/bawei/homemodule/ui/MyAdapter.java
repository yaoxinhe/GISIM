package com.example.bawei.homemodule.ui;

import androidx.annotation.Nullable;

import com.bawei6.homemodule.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/31 09:47
 * @Email 1151403054@qq.com
 */
public class MyAdapter extends BaseQuickAdapter<MyActivityBean, BaseViewHolder> {
    public MyAdapter(int layoutResId, @Nullable List<MyActivityBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyActivityBean item) {
        helper.setText(R.id.tv_desc,item.time);
        helper.setText(R.id.tv_time,item.getActivityName());
    }
}
