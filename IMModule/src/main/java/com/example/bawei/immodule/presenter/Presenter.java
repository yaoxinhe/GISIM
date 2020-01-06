package com.example.bawei.immodule.presenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.baweigame.xmpplibrary.XmppManager;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.rx.BaseObservable;
import com.example.bawei.basemodel.rx.BaseObserver;
import com.example.bawei.immodule.Bean.MyLoginBean;
import com.example.bawei.immodule.Bean.RegisterBean;
import com.example.bawei.immodule.contract.Contract;
import com.example.bawei.immodule.model.Model;
import com.example.bawei.immodule.ui.UserActivity;

import entity.BaseBeanEntity;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/31 13:43
 * @Email 1151403054@qq.com
 */
public class Presenter extends Contract.Presenter {
    public Presenter() {
        mModel = new Model();
    }

    @Override
    public void register(RegisterBean myUserRegistBean) {
        BaseObservable.doObserver(mModel.register(myUserRegistBean), Observable.create(emitter -> {
            XmppManager.getInstance().getXmppUserManager().createAccount(myUserRegistBean.getUsername(), myUserRegistBean.getPwd());
        }),new BaseObserver<BaseBeanEntity<RegisterBean>>(){
            @Override
            public void onNext(BaseBeanEntity<RegisterBean> registerBeanBaseBeanEntity) {
                super.onNext(registerBeanBaseBeanEntity);
                mView.Success(registerBeanBaseBeanEntity,0);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.Failed();
            }
        },mView.getOwner());
    }

    @Override
    public void login(MyLoginBean loginBean) {
        BaseObservable.doObserver(mModel.login(loginBean), new BaseObserver<BaseBeanEntity<RegisterBean>>(){
            @Override
            public void onNext(BaseBeanEntity<RegisterBean> registerBeanBaseBeanEntity) {
                super.onNext(registerBeanBaseBeanEntity);
                boolean login = XmppManager.getInstance().getXmppUserManager().login(loginBean.getUsername(), loginBean.getPwd());
                if(login){
                    mView.Success(registerBeanBaseBeanEntity,1);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.Failed();

            }
        },mView.getOwner());
    }
}
