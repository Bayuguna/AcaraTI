package com.example.bayuguna.progmob;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class KegiatanActivity extends  AppCompatActivity {

    private TextView nama,open_rec,description;
    private ImageView pamflet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kegiatan_detail_activity);

        nama = (TextView) findViewById(R.id.kegiatan_title);
        open_rec = (TextView) findViewById(R.id.kegitan_open_rec);
        description = (TextView) findViewById(R.id.kegiatan_desc);
        pamflet = (ImageView) findViewById(R.id.kegiatan_pic);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("Title");
        String tanggal = intent.getExtras().getString("Tanggal");
        String desc = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Pamflet");

        nama.setText(title);
        open_rec.setText(tanggal);
        description.setText(desc);
        pamflet.setImageResource(image);





    }
}
