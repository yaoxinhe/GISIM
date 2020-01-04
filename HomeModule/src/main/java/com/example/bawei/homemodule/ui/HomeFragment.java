package com.example.bawei.homemodule.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.bawei6.homemodule.R;
import com.example.bawei.addfriend.view.FriendActivity;
import com.example.bawei.basemodel.ui.BaseFragment;

import java.util.ArrayList;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/28 07:49
 * @Email 1151403054@qq.com
 */
public class HomeFragment extends BaseFragment {
    MapView mMapView = null;
    AMap aMap = null;
    private MapView home_map;

    private RecyclerView home_Recycle;
    ArrayList<MyActivityBean> dataList = new ArrayList<>();
    private BottomActionView bav;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mMapView = (MapView) mRoot.findViewById(R.id.home_map);
        home_Recycle = mRoot.findViewById(R.id.home_Recycle);
        bav=mRoot.findViewById(R.id.home_bav);
        dataList.add(new MyActivityBean("2019/12/31/10:00", "活动一", ""));
        dataList.add(new MyActivityBean("2019/12/31/10:00", "活动一", ""));
        dataList.add(new MyActivityBean("2019/12/31/10:00", "活动一", ""));
        dataList.add(new MyActivityBean("2019/12/31/10:00", "活动一", ""));
        dataList.add(new MyActivityBean("2019/12/31/10:00", "活动一", ""));
        dataList.add(new MyActivityBean("2019/12/31/10:00", "活动一", ""));
        dataList.add(new MyActivityBean("2019/12/31/10:00", "活动一", ""));
        dataList.add(new MyActivityBean("2019/12/31/10:00", "活动一", ""));
        bav.Onclick1(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FriendActivity.class));
            }
        });
        home_Recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        home_Recycle.setAdapter(new MyAdapter(R.layout.itemtimer, dataList));
        mMapView.onCreate(savedInstanceState);
        AMap aMap = null;
        if (aMap == null) {
            aMap = mMapView.getMap();
            aMap.getUiSettings().setZoomControlsEnabled(false);

        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doEvent() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
