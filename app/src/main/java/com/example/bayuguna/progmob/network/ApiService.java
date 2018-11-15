package com.example.bayuguna.progmob.network;


import com.example.bayuguna.progmob.Model.KegiatanList;
import com.example.bayuguna.progmob.Model.Riwayat;
import com.example.bayuguna.progmob.Model.User;
import com.example.bayuguna.progmob.Model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
    Call<UserResponse<User>> login(@Field("username") String username,
                                  @Field("password") String password);

    @GET("userLogin/{id}")
    Call<User> userlogin(
            @Path("id") int id
    );

    @GET("kegiatan")
    Call<List<KegiatanList>> getKegiatanList();

    @GET("kegiatan")
    Call<List<Riwayat>> getAllKegiatan();


}
