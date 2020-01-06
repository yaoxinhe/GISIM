package com.example.bawei.chartmodule.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bawei.chartmodule.R;
import com.example.bawei.chartmodule.bean.MyChartBean;

import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/5 17:19
 * @Email 1151403054@qq.com
 */
public class ChartRecycleViewAdapter extends BaseMultiItemQuickAdapter<MyChartBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ChartRecycleViewAdapter(List<MyChartBean> data) {
        super(data);
        addItemType(1,R.layout.itemrecever);
        addItemType(0, R.layout.itemsend);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyChartBean item) {
        ImageView view = helper.getView(R.id.iv_mesgImage);
        View view1 = helper.getView(R.id.item_text);
        View view2 = helper.getView(R.id.iv_audio);
        if(item.getMessage().contains(".jpg")){
            view2.setVisibility(View.GONE);
            view1.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.getMessage()).into(view);
        }else if(item.getMessage().contains(".mp3")){
            view1.setVisibility(View.GONE);
            view2.setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);
            view.setImageResource(R.drawable.audio_animation_list_right_3);
        } else{
            view2.setVisibility(View.GONE);
            view1.setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);
            helper.setText(R.id.item_text,item.getMessage());
        }

    }
}
