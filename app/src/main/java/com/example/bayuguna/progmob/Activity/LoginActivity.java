package com.example.bayuguna.progmob.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bayuguna.progmob.DatabaseH.DatabaseHelper;
import com.example.bayuguna.progmob.Model.User;
import com.example.bayuguna.progmob.Model.UserResponse;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;
import com.example.bayuguna.progmob.network.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    private Button login;
    private TextView signup;
    RelativeLayout relativeLayout1, relativeLayout2;
    EditText login_username, login_pass;
    ApiService service;
    TokenManager tokenManager;
    retrofit2.Call<UserResponse<User>> call;
    private SharedPreferences userPreference;
    String token,user_status;

    private static final String TAG = "LoginActivity";

    Handler handler = new Handler();
    Runnable runable = new Runnable() {
        @Override
        public void run() {
            relativeLayout1.setVisibility(View.VISIBLE);
            relativeLayout2.setVisibility(View.VISIBLE);

        }
    };

    public void init() {
        signup = (TextView) findViewById(R.id.sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);

                startActivity(intent);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        login = (Button) findViewById(R.id.btn_login);
        login_username = (EditText) findViewById(R.id.username);
        login_pass = (EditText) findViewById(R.id.password);
        relativeLayout1 = (RelativeLayout) findViewById(R.id.rellay1);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.rellay2);
        handler.postDelayed(runable, 2000);


        service = RetrofitBuilder.creatService(ApiService.class);

        myDb = new DatabaseHelper(this);
        init();
        login();

        // get token from shared preference
        userPreference = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = userPreference.getString("token", "missing");
        user_status = userPreference.getString("user_status", "missing");

        // check token
        if (token != "missing" && user_status.equals("Member")) {
            Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
            startActivity(intent);
            finish();
        }else if(token != "missing" && user_status.equals("Admin") ){
            Intent intent = new Intent(LoginActivity.this, AdminNavigationActivity.class);
            startActivity(intent);
            finish();
        }


    }

    public  void login(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = login_username.getText().toString();
                String password = login_pass.getText().toString();

                call = service.login(username,password);
                call.enqueue(new Callback<UserResponse<User>>() {
                    @Override
                    public void onResponse(Call<UserResponse<User>> call, Response<UserResponse<User>> response) {


                        if (login_username.getText().toString().isEmpty() && login_pass.getText().toString().isEmpty()){
                            Toast.makeText(LoginActivity.this, "Isi dulu fieldnya",Toast.LENGTH_LONG).show();
                        }else {
                            if (response.isSuccessful()){
                                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("token", response.body().getToken());
                                editor.putString("user_nim", response.body().getDataUser().getNim());
                                editor.putString("user_pic", response.body().getDataUser().getPic());
                                editor.putString("user_status", response.body().getDataUser().getAs());
                                editor.putString("user_name", response.body().getDataUser().getName());
                                editor.putString("user_email", response.body().getDataUser().getEmail());
                                editor.putString("user_telp", response.body().getDataUser().getTelp());
                                editor.putString("user_alamat", response.body().getDataUser().getAlamat());
                                editor.putInt("id_user", response.body().getDataUser().getId());
                                editor.apply();

                                Toast.makeText(LoginActivity.this, response.body().getDataUser().getName(),Toast.LENGTH_LONG).show();


                                if (response.body().getDataUser().getAs().equals("Member")){
                                    Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);

//                                    intent.putExtra("user_pic", response.body().getDataUser().getPic());
//                                    intent.putExtra("Nama", response.body().getDataUser().getName());
                                    startActivity(intent);
                                    finish();

                                }else if (response.body().getDataUser().getAs().equals("Admin")){
                                    Intent intent = new Intent(LoginActivity.this, AdminNavigationActivity.class);
                                    Toast.makeText(LoginActivity.this, "Ini Admin",Toast.LENGTH_LONG).show();

                                    intent.putExtra("Id", response.body().getDataUser().getPic());
//                                    intent.putExtra("Nama", response.body().getDataUser().getName());
                                    startActivity(intent);

                                    finish();
                                }


                            }else {
                                Toast.makeText(LoginActivity.this, response.message(),Toast.LENGTH_LONG).show();
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<UserResponse<User>> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "You Are Offline",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
