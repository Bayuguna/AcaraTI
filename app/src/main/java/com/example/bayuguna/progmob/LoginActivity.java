package com.example.bayuguna.progmob;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;
import com.example.bayuguna.progmob.network.TokenManager;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    private Button login;
    private TextView signup;
    RelativeLayout relativeLayout1, relativeLayout2;
    EditText login_email, login_pass;
    ApiService service;
    TokenManager tokenManager;
    retrofit2.Call<User> call;

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
//
//
//        login = (Button) findViewById(R.id.btn_login);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
//
//                startActivity(intent);
//            }
//        });
//
        signup = (TextView) findViewById(R.id.sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);

                startActivity(intent);
            }
        });

//
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        login = (Button) findViewById(R.id.btn_login);
        login_email = (EditText) findViewById(R.id.email);
        login_pass = (EditText) findViewById(R.id.password);
        relativeLayout1 = (RelativeLayout) findViewById(R.id.rellay1);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.rellay2);
        handler.postDelayed(runable, 2000);


        service = RetrofitBuilder.creatService(ApiService.class);

        myDb = new DatabaseHelper(this);
        init();
        login();


    }

    public  void login(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getText().toString();
                String password = login_pass.getText().toString();

                call = service.login(email,password);
                call.enqueue(new retrofit2.Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        if (login_email.getText().toString().isEmpty() && login_pass.getText().toString().isEmpty()){
                            Toast.makeText(LoginActivity.this, "Isi dulu fieldnya",Toast.LENGTH_LONG).show();
                        }else {
                            if (response.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "You are Login",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                                startActivity(intent);

                                finish();

                            }else {
                                Toast.makeText(LoginActivity.this, "Tidak semudah itu ferguso",Toast.LENGTH_LONG).show();
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.w(TAG, "onFailure: " + t.getMessage() );
                        Toast.makeText(LoginActivity.this, "Gagal Login",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    public void onButtonClick(View v){
        if (v.getId() == R.id.btn_login){
            login_email = (EditText) findViewById(R.id.username);
            login_pass = (EditText) findViewById(R.id.password);
        }
    }
}
