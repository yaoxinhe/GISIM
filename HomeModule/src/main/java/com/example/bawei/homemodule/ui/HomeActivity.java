package com.example.bawei.homemodule.ui;

import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.bawei6.homemodule.R;
import com.example.bawei.basemodel.rx.BaseObservable;
import com.example.bawei.basemodel.rx.BaseObserver;
import com.example.bawei.basemodel.ui.BaseActivity;
import com.example.bawei.basemodel.ui.BaseMVPActivity;

public class HomeActivity extends BaseMVPActivity {
    MapView mMapView = null;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mMapView = (MapView) findViewById(R.id.home_map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
           AMap aMap = mMapView.getMap();



    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doEvent() {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
