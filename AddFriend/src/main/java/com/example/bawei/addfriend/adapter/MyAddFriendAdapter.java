package com.example.bawei.addfriend.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bawei.addfriend.R;
import com.example.bawei.addfriend.bean.MyBeana;

import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/2 21:22
 * @Email 1151403054@qq.com
 */
public class MyAddFriendAdapter extends BaseQuickAdapter<MyBeana.DataBean, BaseViewHolder> {
    public MyAddFriendAdapter(int layoutResId, @Nullable List<MyBeana.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyBeana.DataBean item) {
        helper.setText(R.id.addfriend_name,item.getUsername());
    }
}
