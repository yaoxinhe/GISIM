package com.example.bawei.basemodel.chartmodule.contract;

import androidx.lifecycle.LifecycleOwner;

import com.example.bawei.basemodel.mvp.BasePresenter;
import com.example.bawei.basemodel.mvp.IModel;
import com.example.bawei.basemodel.mvp.IView;
import com.example.bawei.basemodel.chartmodule.bean.MyAddressBean;

import io.reactivex.Observable;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/7 22:20
 * @Email 1151403054@qq.com
 */
public interface Contract {
    interface ChartModel extends IModel {
        Observable<MyAddressBean> getAddress(String usercode);
    }

    interface ChartView extends IView {
        <T>void Success(T bean,int flag);
        void Failed();

        LifecycleOwner getOwner();
    }

    abstract class ChartPresenter extends BasePresenter<ChartView, ChartModel> {
        abstract public void getAddress(String usercode);
    }
}
