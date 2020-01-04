package com.example.bawei.addfriend.api;



import com.example.bawei.addfriend.bean.AddBean;
import com.example.bawei.addfriend.bean.AddFridentBean;
import com.example.bawei.addfriend.bean.MyBeana;
import com.example.bawei.addfriend.bean.MyFriendBean;

import java.util.List;

import entity.BaseBeanEntity;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/30 17:58
 * @Email 1151403054@qq.com
 */

public interface IAddApi {
    @GET("api/Friend/searchFriend")
    Observable<MyBeana> addFriend(@Query("username")String Username, @Query("nick")String nick);
    @GET("api/Friend/getFriends")
    Observable<MyFriendBean> getFriend(@Query("usercode")String Usercode );
    @POST("api/Friend/addFriend")
    Observable<AddBean> add(@Query("usercode") String usercode, @Query("friendcode")String friendcode);

}
