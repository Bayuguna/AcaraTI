package com.example.bayuguna.progmob.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bayuguna.progmob.Model.User;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.api.widget.Widget;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    Call<User> userCall;
    ApiService service;
    TextView nim, nama, gmail, telp, alamat;
    ImageView img;
    String nameString,ImagePath;
    Button edit,foto;
    int getId;
    SharedPreferences userPreference;
    String token,user_name,user_nim,user_email,user_telp,user_alamat;
    int id_user;
    private Bitmap bitmap;
    String path,filename,pic;
    private ArrayList<AlbumFile> mAlbumFiles;

    private static int IMG_REQUEST = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_dashboard);


        service = RetrofitBuilder.creatService(ApiService.class);

        img = findViewById(R.id.img_pic);
        nim = (TextView) findViewById(R.id.profil_nim);
        nama = (TextView) findViewById(R.id.profil_name);
        gmail = (TextView) findViewById(R.id.profil_gmail);
        telp = (TextView) findViewById(R.id.profil_telp);
        alamat = (TextView) findViewById(R.id.profil_alamat);
        edit = (Button) findViewById(R.id.btn_edit);
        foto = findViewById(R.id.btn_foto);



        init();

        userPreference = this.getSharedPreferences("login", Context.MODE_PRIVATE);

        // get token from shared preference
        token = userPreference.getString("token", "missing");
        user_name = userPreference.getString("user_name", "missing");
        user_nim = userPreference.getString("user_nim", "missing");
        user_email = userPreference.getString("user_email", "missing");
        user_telp = userPreference.getString("user_telp", "missing");
        user_alamat = userPreference.getString("user_alamat", "missing");
        id_user = userPreference.getInt("id_user", 0);
        pic = userPreference.getString("user_pic", "missing");


        if(user_name != "missing" ){
            nim.setText(user_nim);
            nama.setText(user_name);
            gmail.setText(user_email);
            telp.setText(user_telp);
            alamat.setText(user_alamat);
        }

        if(pic != "missing"){
            String url = "http://172.17.100.2:8000/"+pic;
            Glide.with(ProfileActivity.this).load(url).into(img);
        }

        if ( token == "missing") {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        updateData();

    }

    public void updateData(){

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody name = RequestBody.create(MediaType.parse("text/plain"),user_name);
                RequestBody email = RequestBody.create(MediaType.parse("text/plain"),user_email);
                RequestBody telps = RequestBody.create(MediaType.parse("text/plain"),user_telp);
                RequestBody alamats = RequestBody.create(MediaType.parse("text/plain"),user_alamat);
                File file = new File(path);
                RequestBody requestBody = RequestBody.create(MediaType.parse("pic"), file);
                MultipartBody.Part pic = MultipartBody.Part.createFormData("pic", file.getName(),requestBody);

                editData(id_user,pic,name,email,telps,alamats);
            }
        });

    }

    public void editData(int id_user, MultipartBody.Part pic, RequestBody name, RequestBody email, RequestBody telps, RequestBody alamats){
                userCall = service.editUser(id_user,pic,name,email,telps,alamats);
                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()){
                            editProfile();
                            Toast.makeText(ProfileActivity.this,"Profile Berhasil Diperbaharui" , Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(ProfileActivity.this,response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(ProfileActivity.this,"Lost Connection", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void editProfile(){
                String shared_name = nama.getText().toString();
                String shared_email = gmail.getText().toString();
                String shared_telp = telp.getText().toString();
                String shared_alamat = telp.getText().toString();
                SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("user_name", shared_name);
                editor.putString("user_email", shared_email);
                editor.putString("user_telp", shared_telp);
                editor.putString("user_alamat", shared_alamat);
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
            }

    public void init(){
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (data == null){
                Toast.makeText(this, "Unable to Choose Image", Toast.LENGTH_SHORT).show();
                return;
            }

            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                img.setImageBitmap(bitmap);

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

//    private String getRealPathFromUri(Uri uri){
//        String[] projection = {MediaStore.Images.Media.DATA};
//        CursorLoader loader = new CursorLoader(getApplicationContext(),uri,projection,null,null,null);
//        Cursor cursor = loader.loadInBackground();
//        int column_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String result = cursor.getString(column_idx);
//        cursor.close();
//        return result;
//    }

//    private String imageToString(){
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//        byte[] imgByte = byteArrayOutputStream.toByteArray();
//        return Base64.encodeToString(imgByte,Base64.DEFAULT);
//    }

    private void selectImage() {
        Album.image(this)
                .singleChoice()
                .camera(true)
                .widget(
                        Widget.newDarkBuilder(this)
                                .build()
                )
                .onResult((Action<ArrayList<AlbumFile>>) result -> {
                    path = result.get(0).getPath();
                    Toast.makeText(ProfileActivity.this,"path : "+path,Toast.LENGTH_SHORT).show();
                    Glide.with(ProfileActivity.this).load(path).into(img);
                    filename = path.substring(path.lastIndexOf("/")+1);
//                    et_logo_kategori.setText(filename);
                    mAlbumFiles = result;
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        Toast.makeText(ProfileActivity.this, "cancell", Toast.LENGTH_LONG).show();
                    }
                })
                .start();
    }


}
