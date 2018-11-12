package com.example.bayuguna.progmob.network;


import com.example.bayuguna.progmob.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("register")
    @FormUrlEncoded
    Call<User> register(@Field("name") String name, @Field("email")String email, @Field("password")String password);
}
