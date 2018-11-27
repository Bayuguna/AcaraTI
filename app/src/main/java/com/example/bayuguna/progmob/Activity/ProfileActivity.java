package com.example.bayuguna.progmob.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bayuguna.progmob.Model.User;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    Call<User> userCall;
    ApiService service;
    TextView nim, nama, gmail, telp, alamat;
    String nameString;
    Button edit;
    int getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_dashboard);


        service = RetrofitBuilder.creatService(ApiService.class);


        nim = (TextView) findViewById(R.id.profil_nim);
        nama = (TextView) findViewById(R.id.profil_name);
        gmail = (TextView) findViewById(R.id.profil_gmail);
        telp = (TextView) findViewById(R.id.profil_telp);
        alamat = (TextView) findViewById(R.id.profil_alamat);

        edit = (Button) findViewById(R.id.btn_edit);

        Intent intent = getIntent();
        getId = intent.getExtras().getInt("Id");
        getData(getId);
        editData();

    }

    public void getData(int getId) {
        userCall = service.userlogin(getId);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                nim.setText(response.body().getNim());
                nama.setText(response.body().getName());
                gmail.setText(response.body().getEmail());
                telp.setText(response.body().getTelp());
                alamat.setText(response.body().getAlamat());
//                Toast.makeText(ProfileActivity.this,"nim :" + response.body().getNim(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ProfileActivity.this,"Gagal Oyy", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void editData(){
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                getId = intent.getExtras().getInt("Id");
                String name = nama.getText().toString();
                String email = gmail.getText().toString();
                String telps = telp.getText().toString();
                String alamats = alamat.getText().toString();

                userCall = service.editUser(getId,name,email,telps,alamats);
                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()){
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
        });

            }
}
