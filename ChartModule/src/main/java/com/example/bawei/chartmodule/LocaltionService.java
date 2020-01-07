package com.example.bawei.chartmodule;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.net.RetrofitUtils;
import com.example.bawei.basemodel.service.XmppService;
import com.example.bawei.chartmodule.api.LocationApi;
import com.example.bawei.chartmodule.bean.BooleanBaseEntity;
import com.example.bawei.chartmodule.bean.LocaltionEntity;
import com.example.bawei.chartmodule.view.ChartActivity;

import entity.BaseBeanEntity;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LocaltionService extends Service {
    static int a=0;
    private static final String TAG = "LocaltionService";
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    private String str;
    private MyBinder myBinder;

    @Override
    public IBinder onBind(Intent intent) {
        myBinder = new MyBinder();
        return myBinder;
    }


    public LocaltionService() {
    }


    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            startService(new Intent(LocaltionService.this, XmppService.class));
            bindService(new Intent(LocaltionService.this, XmppService.class), serviceConnection, Context.BIND_IMPORTANT);
        }
    };

    @SuppressLint("CheckResult")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences yxh = getApplication().getSharedPreferences("yxh", 0);
        str= yxh.getString("usercode", "");
        if (intent != null) {
            //初始化定位
            mLocationClient = new AMapLocationClient(getApplicationContext());
            //设置定位回调监听
            mLocationClient.setLocationListener(mLocationListener);

            AMapLocationClientOption option = new AMapLocationClientOption();
            /**
             * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
             */
            option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
            if (null != mLocationClient) {
                ////设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
                option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
                option.setInterval(5000);
                option.setNeedAddress(true);
                option.setOnceLocation(false);

                mLocationClient.setLocationOption(option);
                //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
                mLocationClient.stopLocation();
                mLocationClient.startLocation();
            }
        }
        return START_REDELIVER_INTENT;
    }

    private LatLng oldLatLng;

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation == null) {
                return;
            }
            a++;
                double latitude = aMapLocation.getLatitude();
                double longitude = aMapLocation.getLongitude();
            LatLng newLatLng = new LatLng(latitude, longitude);
            if(a%2!=0){
                if (oldLatLng == null) {
                    oldLatLng = new LatLng(latitude, longitude);
                    uploadLocation(longitude, latitude);
                } else {
                    int distance = (int) AMapUtils.calculateLineDistance(oldLatLng, newLatLng);
                    oldLatLng=newLatLng;
                    LogUtils.d(String.valueOf(distance));
                    if (distance > 100 && str != null) {
                        Log.e("fanhua","asd");
                        uploadLocation(longitude, latitude);
                    }
                }
            }


        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("CheckResult")
    private void uploadLocation(double longitude, double latitude) {
        RetrofitUtils.getInstance()
                .create(LocationApi.class)
                .uploadLocatoin(new LocaltionEntity(0, str, longitude, latitude, String.valueOf(System.currentTimeMillis())))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(booleanBaseEntity -> Log.e("asdf", booleanBaseEntity.isData() + ""));
    }


    private class MyBinder extends IMyAidlInterface.Stub {
        @Override
        public String getData(String name) {
            return "";
        }
    }

}
