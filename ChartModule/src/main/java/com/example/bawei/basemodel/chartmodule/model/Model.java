package com.example.bawei.basemodel.chartmodule.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.bawei.basemodel.net.RetrofitUtils;
import com.example.bawei.basemodel.chartmodule.api.LocationApi;
import com.example.bawei.basemodel.chartmodule.bean.MyAddressBean;
import com.example.bawei.basemodel.chartmodule.contract.Contract;

import io.reactivex.Observable;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/7 22:20
 * @Email 1151403054@qq.com
 */
public class Model implements Contract.ChartModel {
    @Override
    public void onDestroy() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Observable<MyAddressBean> getAddress(String usercode) {
        return  RetrofitUtils.getInstance().create(LocationApi.class).getAddress(usercode);
    }
}
