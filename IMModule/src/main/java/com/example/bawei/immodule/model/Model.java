package com.example.bawei.immodule.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.bawei.basemodel.net.RetrofitUtils;
import com.example.bawei.immodule.Bean.MyLoginBean;
import com.example.bawei.immodule.Bean.RegisterBean;
import com.example.bawei.immodule.api.IApi;
import com.example.bawei.immodule.contract.Contract;

import entity.BaseBeanEntity;
import io.reactivex.Observable;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/31 13:43
 * @Email 1151403054@qq.com
 */
public class Model implements Contract.Model {
    @Override
    public void onDestroy() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Observable<BaseBeanEntity<RegisterBean>> register(RegisterBean registerBean) {
        return RetrofitUtils.getInstance().create(IApi.class).register(registerBean);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Observable<BaseBeanEntity<RegisterBean>> login(MyLoginBean loginBean) {
        return RetrofitUtils.getInstance().create(IApi.class).login(loginBean);
    }
}
