package com.example.bawei.immodule.contract;

import androidx.lifecycle.LifecycleOwner;

import com.example.bawei.basemodel.mvp.BasePresenter;
import com.example.bawei.basemodel.mvp.IModel;
import com.example.bawei.basemodel.mvp.IView;
import com.example.bawei.immodule.Bean.MyLoginBean;
import com.example.bawei.immodule.Bean.RegisterBean;

import entity.BaseBeanEntity;
import io.reactivex.Observable;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/31 13:43
 * @Email 1151403054@qq.com
 */
public interface Contract {
    interface Model extends IModel {
        Observable<BaseBeanEntity<RegisterBean>> register(RegisterBean registerBean);
        Observable<BaseBeanEntity<RegisterBean>> login(MyLoginBean loginBean);
    }

    interface View extends IView {
        <T>void Success(T bean,int flag);
        void Failed();

        LifecycleOwner getOwner();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        abstract public void register(RegisterBean myUserRegistBean);

        abstract public void login(MyLoginBean loginBean);
    }
}
