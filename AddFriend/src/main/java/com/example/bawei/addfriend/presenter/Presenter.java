package com.example.bawei.addfriend.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.baweigame.xmpplibrary.XmppManager;
import com.example.bawei.addfriend.bean.AddBean;
import com.example.bawei.addfriend.bean.AddFridentBean;
import com.example.bawei.addfriend.bean.MyBeana;
import com.example.bawei.addfriend.bean.MyFriendBean;
import com.example.bawei.addfriend.contract.Contract;
import com.example.bawei.addfriend.model.Model;
import com.example.bawei.addfriend.view.AddFragment;
import com.example.bawei.addfriend.view.FridentFragment;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.rx.BaseObservable;
import com.example.bawei.basemodel.rx.BaseObserver;

import entity.BaseBeanEntity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/2 18:52
 * @Email 1151403054@qq.com
 */
public class Presenter extends Contract.Presenter {
    AddFragment fragment;
    Model mModel;
    FridentFragment fridentFragment;

    public Presenter(FridentFragment fridentFragment) {
        if (mModel == null) {
            mModel = new Model();
        }
        this.fridentFragment = fridentFragment;
    }

    public Presenter(AddFragment fragment) {
        mModel = new Model();

        this.fragment = fragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void addFriend(String username) {
        BaseObservable.doObserver(mModel.addfriend(username), new BaseObserver<MyBeana>() {
            @Override
            public void onNext(MyBeana addFridentBeanBaseBeanEntity) {
                super.onNext(addFridentBeanBaseBeanEntity);
                fragment.Success(addFridentBeanBaseBeanEntity, 101);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                fragment.Failed();
            }
        }, fragment);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void friend(String usercode) {
        BaseObservable.doObserver(mModel.friend(usercode), new BaseObserver<MyFriendBean>() {
            @Override
            public void onNext(MyFriendBean myFriendBean) {
                super.onNext(myFriendBean);
                if (myFriendBean != null) {
                    fridentFragment.Success(myFriendBean, 101);

                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                fridentFragment.Failed();
            }
        }, fridentFragment);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void add(String usercode, String otherUsercode, String myuserName, String friendUsername) {
        BaseObservable.doObserver(Observable.create( emitter -> emitter.onNext(XmppManager.getInstance().getXmppFriendManager().addFriend(myuserName, friendUsername))), new BaseObserver<Boolean>() {
            @Override
            public void onNext(Boolean addFridentBeanBaseBeanEntity) {
                super.onNext(addFridentBeanBaseBeanEntity);
                    if ( addFridentBeanBaseBeanEntity) {
                        BaseObservable.doObserver(mModel.add(usercode,otherUsercode),new BaseObserver<AddBean>(){
                            @Override
                            public void onNext(AddBean addBean) {
                                super.onNext(addBean);
                                if(addBean.isData()){
                                    fragment.Success(addFridentBeanBaseBeanEntity, 102);

                                }else{
                                    fragment.Failed();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);

                            }
                        },fragment);
                    }

                }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LogUtils.i("" + e.getMessage());

                fragment.Failed();
            }
        }, fragment);
    }
}
