package com.example.bawei.addfriend.adapter;

import com.bawei6.common.PhoneDto;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bawei.addfriend.R;

import java.util.List;

public class GoodsAdapter extends BaseMultiItemQuickAdapter<PhoneDto, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public GoodsAdapter(List<PhoneDto> data) {
        super(data);
        addItemType(1, R.layout.itemphonenum);
        addItemType(0,R.layout.itemphonebook_lable);
    }



    @Override
    protected void convert(BaseViewHolder helper, PhoneDto item) {
        if(helper.getItemViewType()==1){
            helper.setText(R.id.tv_name,item.getName());
            helper.setText(R.id.tv_num,item.getTelPhone());
        }else{
            helper.setText(R.id.tv_phonebook_Label,item.getPhoneBookLael());
        }
    }
}
