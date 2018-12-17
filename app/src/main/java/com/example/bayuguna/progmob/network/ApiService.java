package com.example.bayuguna.progmob.network;


import com.example.bayuguna.progmob.Model.DetKegiatan;
import com.example.bayuguna.progmob.Model.DetKegiatanItem;
import com.example.bayuguna.progmob.Model.Kegiatans;
import com.example.bayuguna.progmob.Model.Kepanitiaan;
import com.example.bayuguna.progmob.Model.ListKegiatan;
import com.example.bayuguna.progmob.Model.Peserta;
import com.example.bayuguna.progmob.Model.RiwayatKepanitiaan;
import com.example.bayuguna.progmob.Model.RiwayatKepanitiaanResponse;
import com.example.bayuguna.progmob.Model.SieSpinner;
import com.example.bayuguna.progmob.Model.User;
import com.example.bayuguna.progmob.Model.UserResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @Multipart
    @POST("addkegiatan")
    Call<Kegiatans> addKegiatan(
                        @Part MultipartBody.Part pic,
                        @Part("nama") RequestBody nama,
                        @Part("tanggal") RequestBody tanggal,
                        @Part("deskripsi")RequestBody deskripsi);

    @POST("login")
    @FormUrlEncoded
    Call<UserResponse<User>> login(@Field("username") String username,
                                  @Field("password") String password);

    @POST("me")
    @FormUrlEncoded
    Call<UserResponse<User>> getUser(@Query("token") String token);

    @Multipart
    @POST("editUser/{id}")
    Call<User> editUser(
            @Path("id") int id,
            @Part MultipartBody.Part pic,
            @Part("name") RequestBody name,
            @Part("email")RequestBody email,
            @Part("telp") RequestBody telp,
            @Part("alamat") RequestBody alamat
    );

    @Multipart
    @POST("editProfileUser/{id}")
    Call<User> editPictureProfile(
            @Path("id") int id,
            @Part MultipartBody.Part pic
    );

    @Multipart
    @POST("editKegiatan/{id}")
    Call<Kegiatans> editKegiatan(
            @Path("id") int id,
            @Part MultipartBody.Part pic,
            @Part("nama") RequestBody nama,
            @Part("tanggal")RequestBody tanggal,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("status") RequestBody status
    );

    @GET("showKegiatan")
    Call<List<ListKegiatan>> getKegiatan();

    @GET("showKegiatanBerlangsung")
    Call<List<ListKegiatan>> getKegiatanBerlangsung();

    @GET("showDetKegiatan/{id}")
    Call<List<DetKegiatan>> getDetKegiatan(
            @Path("id") int id
    );

    @GET("riwayat/{id}")
    Call<List<RiwayatKepanitiaanResponse>> getRiwayat(
            @Path("id") int id);

    @POST("addDetKegiatan")
    @FormUrlEncoded
    Call<DetKegiatanItem> addDetKegiatan(@Field("id_kegiatan") int id_kegiatan,
                             @Field("sie") String sie,
                             @Field("job_desc") String job_desc,
                             @Field("kuota") String kuota,
                             @Field("nama_koor") String nama_koor,
                             @Field("line_id") String line_id
    );

    @GET("peserta/{id}")
    Call<List<Kepanitiaan>> getPeserta(
            @Path("id") int id
    );

    @POST("aktivasiPeserta/{id}")
    Call<Peserta> aktivasiPeserta(
            @Path("id") int id
    );

    @POST("NonAktivasiPeserta/{id}")
    Call<Peserta> NonAktivasiPeserta(
            @Path("id") int id
    );

    @GET("sie/{id}")
    Call<List<SieSpinner>> getSieSpinner(
            @Path("id") int id
    );


    @FormUrlEncoded
    @POST("ikut_kepanitiaan")
    Call<RiwayatKepanitiaan> ikutKepanitiaan(
            @Query("token") String token,
            @Field("id_users") int id_users,
            @Field("id_det_kegiatan") int id_det_kegiatan,
            @Field("alasan") String alasan
    );

    @GET("deleteKepanitiaan/{id}")
    Call<RiwayatKepanitiaanResponse> deleteKepanitiaan(
            @Path("id") int id
    );

    @GET("deleteDetKegiatan/{id}")
    Call<DetKegiatanItem> deleteDetKegiatan(
            @Path("id") int id
    );

}
