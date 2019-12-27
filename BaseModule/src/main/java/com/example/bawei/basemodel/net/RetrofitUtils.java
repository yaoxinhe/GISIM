package com.example.bawei.basemodel.net;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.bawei.basemodel.device.AppInfoConfig;
import com.example.bawei.basemodel.device.DeviceInfoConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/27 16:03
 * @Email 1151403054@qq.com
 */
public class RetrofitUtils {
    private static final String TAG = "RetrofitUtils";
    private static final String BASEURL = "https://www.city2sky.cn/";
    private static volatile RetrofitUtils instance;
    private Retrofit mRetrofit;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static RetrofitUtils getInstance() {
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                if (instance == null) {
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private RetrofitUtils() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(createRequestInterceptor())
                .addNetworkInterceptor(createLogInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();


        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private Interceptor createRequestInterceptor() {
        return chain -> {
            //拦截请求
            Request request = chain.request();
            //拦截响应
            Response response = chain.proceed(request);

            //添加头信息
            Request.Builder requestBuilder = request.newBuilder()
                    .addHeader("Content-Type", "application-json")
                    .addHeader("charest", "utf-8")
                    .addHeader("manufacturer", DeviceInfoConfig.getInstance().getMANUFACTURER())
                    .addHeader("model", DeviceInfoConfig.getInstance().getModel())
                    .addHeader("deviceid", DeviceInfoConfig.getInstance().getDeviceId())
                    .addHeader("utdid", DeviceInfoConfig.getInstance().getUtdid())
                    .addHeader("packagename", AppInfoConfig.getInstance().getPackageName())
                    .addHeader("versoincode", AppInfoConfig.getInstance().getVersionCode())
                    .addHeader("versionname", AppInfoConfig.getInstance().getVersionName())
                    .addHeader("location", DeviceInfoConfig.getInstance().getLocation())
                    .addHeader("macaddress", DeviceInfoConfig.getInstance().getMacAddress())
                    .addHeader("display", DeviceInfoConfig.getInstance().getX())
                    .addHeader("osversion", DeviceInfoConfig.getInstance().getosVersion());
            return chain.proceed(requestBuilder.build());
        };
    }
    private HttpLoggingInterceptor createLogInterceptor() {
        HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {

            @Override
            public void log(String message) {
                Log.i(TAG, message);
            }
        });
        mLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return mLoggingInterceptor;
    }
    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
