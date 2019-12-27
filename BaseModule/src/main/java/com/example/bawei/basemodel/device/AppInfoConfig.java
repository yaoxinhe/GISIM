package com.example.bawei.basemodel.device;

import android.content.Context;
import android.content.pm.PackageManager;

public class AppInfoConfig {
    Context context;

    private static AppInfoConfig instance;

    public void init(Context context) {
        this.context = context;
    }
    private AppInfoConfig() {
    }

    public static AppInfoConfig getInstance() {
        if (instance == null) {
            instance = new AppInfoConfig();
        }
        return instance;
    }
    public String getPackageName(){
        PackageManager packageManager = context.getPackageManager();
        try {
           return packageManager.getPackageInfo(context.getPackageName(),0).packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getVersionName(){
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(context.getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getVersionCode(){
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(context.getPackageName(),0).versionCode+"";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}