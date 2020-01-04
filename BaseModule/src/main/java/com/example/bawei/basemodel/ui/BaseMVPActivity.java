package com.example.bawei.basemodel.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.baweigame.xmpplibrary.XmppManager;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.mvp.BasePresenter;
import com.example.bawei.basemodel.service.XmppService;

import java.util.List;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/31 15:30
 * @Email 1151403054@qq.com
 */
public abstract class BaseMVPActivity<P extends BasePresenter>extends BaseActivity {
    public P presenter;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        startService(new Intent(this, XmppService.class));
        Permissions();
//        getNotchParams();
        initView(savedInstanceState);
        initData();
        doEvent();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected  void Permissions(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES, Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.READ_CONTACTS}, 100);
    }

    public void getNotchParams() {
        final View decorView = getWindow().getDecorView();

        decorView.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void run() {
                WindowInsets rootWindowInsets = decorView.getRootWindowInsets();
                if (rootWindowInsets == null) {
                    Log.e("TAG", "rootWindowInsets为空了");
                    return;
                }
                DisplayCutout displayCutout = rootWindowInsets.getDisplayCutout();
                LogUtils.e("安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout.getSafeInsetLeft());
                LogUtils.e( "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.getSafeInsetRight());
                LogUtils.e( "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.getSafeInsetTop());
                LogUtils.e("安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.getSafeInsetBottom());

                List<Rect> rects = displayCutout.getBoundingRects();
                if (rects == null || rects.size() == 0) {
                    LogUtils.e("不是刘海屏");
                } else {
                    LogUtils.e( "刘海屏数量:" + rects.size());
                    for (Rect rect : rects) {
                        LogUtils.e( "刘海屏区域：" + rect);
                    }
                }
            }
        });
    }



    /***
     * 获取 Activity 的布局文件
     * @return
     */
    protected abstract int getLayoutId();

    /***
     * 初始化视图
     */
    protected abstract void initView(Bundle savedInstanceState);

    /***
     * 初始化数据
     */
    protected abstract void initData();


    /***
     * 点击事件
     */
    protected abstract void doEvent();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
