package com.example.bayuguna.progmob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        btn_regis = (Button) findViewById(R.id.btn_regis);
        insert_nim = (EditText) findViewById(R.id.nim);
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

                        String name = insert_nama.getText().toString();
                        String email = insert_gmail.getText().toString();
                        String password = insert_password.getText().toString();

                        call = service.register(name,email,password);
                        call.enqueue(new retrofit2.Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {

                                if (response.isSuccessful()){
                                    Toast.makeText(SignUpActivity.this, "You are Registered",Toast.LENGTH_LONG).show();

                                }else {
                                    
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.w(TAG, "onFailure: " + t.getMessage() );;
                            }
                        });


//                        if (insert_nim.getText().toString().isEmpty() && insert_nama.getText().toString().isEmpty() && insert_username.getText().toString().isEmpty() && insert_password.getText().toString().isEmpty()){
//                            Toast.makeText(SignUpActivity.this, "Please Fill The Box",Toast.LENGTH_LONG).show();
//                        }else{
//                            boolean isInserted = myDb.insertData( insert_nim.getText().toString(),
//                                    insert_nama.getText().toString(),
//                                    insert_gmail.getText().toString(),
//                                    insert_telp.getText().toString(),
//                                    insert_alamat.getText().toString(),
//                                    insert_username.getText().toString(),
//                                    insert_password.getText().toString());
//
//                            if (isInserted == true)
//                                Toast.makeText(SignUpActivity.this, "You are Registered",Toast.LENGTH_LONG).show();
//                            else
//                                Toast.makeText(SignUpActivity.this, "You Not Registered",Toast.LENGTH_LONG).show();
//
//                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
//
//                            startActivity(intent);
//                        }
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
