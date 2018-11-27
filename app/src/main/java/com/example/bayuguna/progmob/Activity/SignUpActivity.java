package com.example.bayuguna.progmob.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bayuguna.progmob.DatabaseHelper;
import com.example.bayuguna.progmob.Model.User;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    DatabaseHelper myDb = new DatabaseHelper(this);
    Button btn_regis;
    EditText insert_nim, insert_nama, insert_username, insert_password,insert_gmail, insert_telp, insert_alamat;
    ApiService service;
    Call<User> call;
    private static final String TAG = "SignUpActivity";
//    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        btn_regis = (Button) findViewById(R.id.btn_regis);
        insert_nim = (EditText) findViewById(R.id.profil_nim);
        insert_nama = (EditText) findViewById(R.id.name);
        insert_gmail = (EditText) findViewById(R.id.gmail);
        insert_telp = (EditText) findViewById(R.id.telp);
        insert_alamat = (EditText) findViewById(R.id.alamat);
        insert_username = (EditText) findViewById(R.id.username);
        insert_password = (EditText) findViewById(R.id.password);
        addData();

        service = RetrofitBuilder.creatService(ApiService.class);
    }

    public void addData(){
        btn_regis.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nim = insert_nim.getText().toString();
                        String name = insert_nama.getText().toString();
                        String email = insert_gmail.getText().toString();
                        String telp = insert_telp.getText().toString();
                        String alamat = insert_alamat.getText().toString();
                        String username = insert_username.getText().toString();
                        String password = insert_password.getText().toString();

                        call = service.register(nim,name,email,telp,alamat,username,password);
                        call.enqueue(new retrofit2.Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {

                                if (insert_nim.getText().toString().isEmpty() && insert_nama.getText().toString().isEmpty() && insert_username.getText().toString().isEmpty() && insert_password.getText().toString().isEmpty()){
                                    Toast.makeText(SignUpActivity.this, "Isi dulu fieldnya",Toast.LENGTH_LONG).show();
                                }else {
                                    if (response.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this, "You are Registered",Toast.LENGTH_LONG).show();
                                        getSharedPreferences("login", Context.MODE_PRIVATE)
                                                .edit()
                                                .putString("token", response.body().getToken())
                                                .apply();
                                        Intent intent = new Intent(SignUpActivity.this, NavigationActivity.class);
                                        startActivity(intent);

                                    }else {
                                        Toast.makeText(SignUpActivity.this, response.message(),Toast.LENGTH_LONG).show();
                                    }

                                }

                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.w(TAG, "onFailure: " + t.getMessage() );
                                Toast.makeText(SignUpActivity.this, "Lost Connection",Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null) {
            call.cancel();
            call = null;
        }
    }
}
