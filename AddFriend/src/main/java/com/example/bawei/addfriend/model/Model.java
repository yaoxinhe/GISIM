package com.example.bawei.addfriend.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.bawei.addfriend.api.IAddApi;
import com.example.bawei.addfriend.bean.AddBean;
import com.example.bawei.addfriend.bean.AddFridentBean;
import com.example.bawei.addfriend.bean.MyBeana;
import com.example.bawei.addfriend.bean.MyFriendBean;
import com.example.bawei.addfriend.contract.Contract;
import com.example.bawei.basemodel.net.RetrofitUtils;

import entity.BaseBeanEntity;
import io.reactivex.Observable;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/2 18:52
 * @Email 1151403054@qq.com
 */
public class Model implements Contract.Model {


    @RequiresApi(api = Build.VERSION_CODES.M)
    public Observable<MyBeana> addfriend(String Username) {
        return RetrofitUtils.getInstance().create(IAddApi.class).addFriend(Username,"");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Observable<MyFriendBean> friend(String usercode) {
        return RetrofitUtils.getInstance().create(IAddApi.class).getFriend(usercode);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Observable<AddBean> add(String usercode, String otherUsercode) {
        return RetrofitUtils.getInstance().create(IAddApi.class).add(usercode,otherUsercode);
    }

    @Override
    public void onDestroy() {

    }
}
