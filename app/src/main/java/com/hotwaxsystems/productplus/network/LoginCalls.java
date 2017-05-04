package com.hotwaxsystems.productplus.network;

import com.hotwaxsystems.productplus.pojo.utilities.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ps11 on 23/11/16.
 */

public interface LoginCalls {


    @FormUrlEncoded
    @POST("api/control/authorizeUser.php")
    Call<Login> authorizeUser(@Field("username") String username, @Field("password") String password);


}
