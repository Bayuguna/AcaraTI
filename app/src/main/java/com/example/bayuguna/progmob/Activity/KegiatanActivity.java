package com.example.bayuguna.progmob.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bayuguna.progmob.R;

public class KegiatanActivity extends  AppCompatActivity {

    private TextView nama,open_rec,description;
    private ImageView pamflet;
    private Button login;

    public void init() {
        login = (Button) findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KegiatanActivity.this, RegisterKepanitianActivity.class);

                startActivity(intent);
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kegiatan_detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        nama = (TextView) findViewById(R.id.kegiatan_title);
//        open_rec = (TextView) findViewById(R.id.kegitan_open_rec);
        description = (TextView) findViewById(R.id.kegiatan_desc);
        pamflet = (ImageView) findViewById(R.id.kegiatan_pic);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("Title");
//        String tanggal = intent.getExtras().getString("Tanggal");
        String desc = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Pamflet");

        nama.setText(title);
//        open_rec.setText(tanggal);
        description.setText(desc);
        pamflet.setImageResource(image);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
