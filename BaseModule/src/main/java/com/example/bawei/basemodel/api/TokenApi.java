package com.example.bawei.basemodel.api;



import entity.TokenEnity;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/***
 * @Author yxh
 * @CreateDate 2019-12-02
 * @Desc 获取Token
 * @Version 1.0
 */
public interface TokenApi {
    @POST("/token")
    @FormUrlEncoded
    Call<TokenEnity> getToken(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);

}
