package com.example.bawei.immodule.api;

import com.example.bawei.immodule.Bean.MyLoginBean;
import com.example.bawei.immodule.Bean.RegisterBean;

import entity.BaseBeanEntity;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author yaoxinhe
 * @CreateDate 2019/12/30 17:58
 * @Email 1151403054@qq.com
 */
public interface IApi {
    @POST("api/User/register")
    Observable<BaseBeanEntity<RegisterBean>> register(@Body RegisterBean registerBean);
    @POST("api/User/login")
    Observable<BaseBeanEntity<RegisterBean>> login(@Body MyLoginBean myLoginBean);
}
