package com.example.bawei.basemodel.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Method;


public class DeviceInfoConfig {
    Context context;

    private static volatile DeviceInfoConfig instance;

    private DeviceInfoConfig() {
    }

    public void init(Context context) {
        this.context = context;
    }

    public static DeviceInfoConfig getInstance() {
        if (instance == null) {
            synchronized (DeviceInfoConfig.class) {
                if (instance == null) {
                    instance = new DeviceInfoConfig();
                }
            }
        }
        return instance;
    }

    public String getMacAddress() {
//        return UMUtils.getMac(context);
        return "";
    }

    public String getX() {
//        UMUtils.getDisplayResolution(context);/
        return "";
    }

    public String getUtdid() {
//        return UMUtils.getUTDID(context);
        return "";
    }

    public String getMANUFACTURER() {
        return Build.MANUFACTURER;
    }

    public String getModel() {
        return Build.MODEL;
    }

    public String getosVersion() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    @SuppressLint("WrongConstant")
    public String getLocation() {
//        LocationManager systemService = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//
//        @SuppressLint("MissingPermission") Location lastKnownLocation = systemService.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//    return String.valueOf(lastKnownLocation.getLongitude())+lastKnownLocation.getLatitude();
        return "";
}



    /**
     * 获取设备号
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public String getDeviceId( ){
        if(Build.VERSION.SDK_INT<23){
            Log.e("asd","<23");
            Log.e("asd",">23");

            String deviceid= getIMEI1(TelephonyManager.PHONE_TYPE_GSM);
            if(TextUtils.isEmpty(deviceid)){
                deviceid=getMEID1();
            }
            return deviceid;
        }else{
            Log.e("asd",">23");

            String deviceid= getIMEI(context);
            if(TextUtils.isEmpty(deviceid)){
                deviceid=getMeID(context);
            }
            return deviceid;
        }

    }

    /**
     * Android 5.0之前使用
     * @param slotId
     * @return
     */
    private String getIMEI1(int slotId){
        try {
            Class clazz = Class.forName("android.os.SystemProperties");
            Method method = clazz.getMethod("get", String.class, String.class);
            String imei = (String) method.invoke(null, "ril.gsm.imei", "");
            if(!TextUtils.isEmpty(imei)){
                String[] split = imei.split(",");
                if(split.length > slotId){
                    imei = split[slotId];
                }
                return imei;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Android 5.0之前使用
     * @return
     */
    private String getMEID1(){
        try {
            Class clazz = Class.forName("android.os.SystemProperties");
            Method method = clazz.getMethod("get", String.class, String.class);

            String meid = (String) method.invoke(null, "ril.cdma.meid", "");
            if(!TextUtils.isEmpty(meid)){
                return meid;
            }
        } catch (Exception  e) {
            e.printStackTrace();
        }
        return "";
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private String getMeID(Context context) {
        TelephonyManager systemService = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint({"MissingPermission", "HardwareIds"})
        String deviceId = systemService.getDeviceId(TelephonyManager.PHONE_TYPE_CDMA);
        return deviceId;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    private String getIMEI(Context context) {
        TelephonyManager systemService = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = systemService.getDeviceId(TelephonyManager.PHONE_TYPE_GSM);
        return deviceId;
    }
}