package com.example.bawei.basemodel.chartmodule.presenter;

import com.example.bawei.basemodel.rx.BaseObservable;
import com.example.bawei.basemodel.rx.BaseObserver;
import com.example.bawei.basemodel.chartmodule.bean.MyAddressBean;
import com.example.bawei.basemodel.chartmodule.contract.Contract;
import com.example.bawei.basemodel.chartmodule.model.Model;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/7 22:20
 * @Email 1151403054@qq.com
 */
public class Presenter extends Contract.ChartPresenter {
    public Presenter() {
        mModel=new Model();
    }

    @Override
    public void getAddress(String usercode) {
        BaseObservable.doObserver(mModel.getAddress(usercode),new BaseObserver<MyAddressBean>(){
            @Override
            public void onNext(MyAddressBean myAddressBean) {
                super.onNext(myAddressBean);
                mView.Success(myAddressBean,0);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.Failed();

            }
        },mView.getOwner());
    }
}
