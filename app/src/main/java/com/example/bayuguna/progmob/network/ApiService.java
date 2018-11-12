package com.example.bayuguna.progmob.network;


import com.example.bayuguna.progmob.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("register")
    @FormUrlEncoded
    Call<User> register(@Field("nim") String nim,
                        @Field("name") String name,
                        @Field("email")String email,
                        @Field("telp") String telp,
                        @Field("alamat") String alamat,
                        @Field("username") String username,
                        @Field("password")String password);

    @POST("login")
    @FormUrlEncoded
    Call<User> login(@Field("email") String email,
                        @Field("password") String password);

}
