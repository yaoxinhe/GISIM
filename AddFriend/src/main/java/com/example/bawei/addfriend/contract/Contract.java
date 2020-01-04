package com.example.bawei.addfriend.contract;

import androidx.lifecycle.LifecycleOwner;

import com.example.bawei.addfriend.bean.AddBean;
import com.example.bawei.addfriend.bean.AddFridentBean;
import com.example.bawei.addfriend.bean.MyBeana;
import com.example.bawei.addfriend.bean.MyFriendBean;
import com.example.bawei.basemodel.mvp.BasePresenter;
import com.example.bawei.basemodel.mvp.IModel;
import com.example.bawei.basemodel.mvp.IView;

import entity.BaseBeanEntity;
import io.reactivex.Observable;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/2 18:52
 * @Email 1151403054@qq.com
 */
public interface Contract {
    interface Model extends IModel {
        Observable<MyBeana> addfriend(String  username);
        Observable<MyFriendBean> friend(String usercode);
        Observable<AddBean> add(String usercode, String otherUsercode);
    }

    interface View extends IView {
        <T>void Success(T bean,int flag);
        void Failed();

        LifecycleOwner getOwner();
    }

    abstract class Presenter  {
        abstract public void addFriend(String  username);
        abstract public void friend(String usercode);
        abstract public void add(String usercode,String otherUsercode,String myuserName,String friendUsername);

    }
}
