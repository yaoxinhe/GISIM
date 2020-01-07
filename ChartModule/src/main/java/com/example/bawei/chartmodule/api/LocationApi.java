package com.example.bawei.chartmodule.api;

import com.example.bawei.chartmodule.bean.BooleanBaseEntity;
import com.example.bawei.chartmodule.bean.LocaltionEntity;


import entity.BaseBeanEntity;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/7 19:30
 * @Email 1151403054@qq.com
 */
public interface LocationApi {

    @POST("api/UserLocation/uploadLocatoin")
    Observable<BooleanBaseEntity> uploadLocatoin(@Body LocaltionEntity localtionEntity);
}
