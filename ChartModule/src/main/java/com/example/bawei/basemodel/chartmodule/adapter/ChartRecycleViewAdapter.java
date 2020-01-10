package com.example.bawei.basemodel.chartmodule.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bawei.immodulenew.entity.BaseMsg;
import com.bawei.immodulenew.entity.ImgMsg;
import com.bawei.immodulenew.entity.MsgType;
import com.bawei.immodulenew.entity.TxtMsg;
import com.bawei.immodulenew.entity.VideoMsg;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.bawei.basemodel.chartmodule.R;
import com.example.bawei.basemodel.chartmodule.bean.MyChartBean;
import com.example.bawei.basemodel.log.LogUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/5 17:19
 * @Email 1151403054@qq.com
 */
public class ChartRecycleViewAdapter extends BaseMultiItemQuickAdapter<MyChartBean, BaseViewHolder> {
    Gson gson;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ChartRecycleViewAdapter(List<MyChartBean> data) {
        super(data);
        addItemType(1, R.layout.itemrecever);
        addItemType(0, R.layout.itemsend);
        gson = new Gson();

    }

    @Override
    protected void convert(BaseViewHolder helper, MyChartBean item) {
        ImageView view = helper.getView(R.id.iv_mesgImage);
        View view1 = helper.getView(R.id.item_text);
        View view2 = helper.getView(R.id.iv_audio);
        if (item.getBaseMsg().getMsgType().equals(MsgType.IMG) || item.getBaseMsg().getMsgType().equals(MsgType.VIDEO)) {
            view2.setVisibility(View.GONE);
            view1.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
            if (item.getBaseMsg().getMsgType().equals(MsgType.IMG)) {
                ImgMsg imgMsg = gson.fromJson(item.getBaseMsg().getMsg(), ImgMsg.class);
                Glide.with(mContext).load(imgMsg.getImgPath()).into(view);
            } else {
                VideoMsg videoMsg = gson.fromJson(item.getBaseMsg().getMsg(), VideoMsg.class);
                Glide.with(mContext).load(videoMsg.getPath()).into(view);
            }
        } else if (item.getBaseMsg().getMsgType().equals(MsgType.AUDIO)) {
            view1.setVisibility(View.GONE);
            view2.setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);

        } else {
            view2.setVisibility(View.GONE);
            view1.setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);
            LogUtils.d(item.getBaseMsg().getMsg());
            TxtMsg txtMsg = gson.fromJson(item.getBaseMsg().getMsg(), TxtMsg.class);
            helper.setText(R.id.item_text, txtMsg.getTxt());
        }

    }
}
