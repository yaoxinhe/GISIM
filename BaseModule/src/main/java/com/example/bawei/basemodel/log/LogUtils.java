package com.example.bawei.basemodel.log;

import android.util.Log;

public class LogUtils {

    private static final String TAG = "LogUtils";
    private static boolean debug = true;

    public static void i(String msg) {
        if (debug) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (debug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (debug) {
            Log.e(TAG, msg);
        }
    }

    public void setDebug(boolean isDubug) {
        debug = isDubug;
    }
}