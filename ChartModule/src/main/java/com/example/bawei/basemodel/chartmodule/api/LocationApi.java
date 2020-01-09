package com.example.bawei.basemodel.chartmodule.api;

import entity.BooleanBaseEntity;
import entity.LocaltionEntity;
import com.example.bawei.basemodel.chartmodule.bean.MyAddressBean;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/7 19:30
 * @Email 1151403054@qq.com
 */
public interface LocationApi {

    @POST("api/UserLocation/uploadLocatoin")
    Observable<BooleanBaseEntity> uploadLocatoin(@Body LocaltionEntity localtionEntity);
    @GET("api/UserLocation/getLastestLoc")
    Observable<MyAddressBean> getAddress(@Query("usercode")String usercode);
}
