package com.example.bayuguna.progmob;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    private Button login;
    private TextView signup;
    RelativeLayout relativeLayout1, relativeLayout2;
    EditText username, password;

    Handler handler = new Handler();
    Runnable runable = new Runnable() {
        @Override
        public void run() {
            relativeLayout1.setVisibility(View.VISIBLE);
            relativeLayout2.setVisibility(View.VISIBLE);

        }
    };

    public void init() {


        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NavigationActivity.class);

                startActivity(intent);
            }
        });

        signup = (TextView) findViewById(R.id.sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);

                startActivity(intent);
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout1 = (RelativeLayout) findViewById(R.id.rellay1);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.rellay2);
        handler.postDelayed(runable, 2000);

        myDb = new DatabaseHelper(this);
        init();
    }

    public void onButtonClick(View v){
        if (v.getId() == R.id.btn_login){
            username = (EditText) findViewById(R.id.username);
            password = (EditText) findViewById(R.id.password);
        }
    }
}
