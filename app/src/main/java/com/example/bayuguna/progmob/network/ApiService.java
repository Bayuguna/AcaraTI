package com.example.bayuguna.progmob.network;


import com.example.bayuguna.progmob.Model.Kegiatan;
import com.example.bayuguna.progmob.Model.Kegiatans;
import com.example.bayuguna.progmob.Model.Peserta;
import com.example.bayuguna.progmob.Model.ListKegiatan;
import com.example.bayuguna.progmob.Model.Riwayat;
import com.example.bayuguna.progmob.Model.RiwayatKepanitiaanResponse;
import com.example.bayuguna.progmob.Model.Sie;
import com.example.bayuguna.progmob.Model.User;
import com.example.bayuguna.progmob.Model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @POST("addkegiatan")
    @FormUrlEncoded
    Call<Kegiatans> addKegiatan(@Field("nama") String nama,
                             @Field("tanggal") String tanggal,
                             @Field("deskripsi")String deskripsi);

    @POST("login")
    @FormUrlEncoded
    Call<UserResponse<User>> login(@Field("username") String username,
                                  @Field("password") String password);

    @GET("userLogin/{id}")
    Call<User> userlogin(
            @Path("id") int id
    );

    @GET("kegiatan")
    Call<List<Riwayat>> getAllKegiatan();

    @PUT("editUser/{id}")
    @FormUrlEncoded
    Call<User> editUser(
            @Path("id") int id,
            @Field("name") String name,
            @Field("email")String email,
            @Field("telp") String telp,
            @Field("alamat") String alamat
    );

    @GET("showKegiatan")
    Call<List<ListKegiatan>> getKegiatan();

//    @GET("showDetKegiatan/{id}")
//    Call<List<ListKegiatan>> getDetKegiatans(
//            @Path("id") int id
//    );

    @GET("showDetKegiatan/{id}")
    Call<List<Sie>> getDetSie(
            @Path("id") int id
    );

    @GET("showKegiatan")
    Call<List<Kegiatan>> getKegiatans();

    @GET("riwayat/{id}")
    Call<List<RiwayatKepanitiaanResponse>> getRiwayat(
            @Path("id") int id);

    @POST("addDetKegiatan")
    @FormUrlEncoded
    Call<Sie> addDetKegiatan(@Field("id_kegiatan") int id_kegiatan,
                             @Field("sie") String sie,
                             @Field("job_desc") String job_desc,
                             @Field("kuota") String kuota,
                             @Field("nama_koor") String nama_koor,
                             @Field("line_id") String line_id
    );

    @GET("peserta/{id}")
    Call<List<Peserta>> getPeserta(
            @Path("id") int id);


}
