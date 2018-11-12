package com.example.bayuguna.progmob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RiwayatActivity extends  AppCompatActivity {

    List<Riwayat> riwayatList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayat_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        riwayatList = new ArrayList<>();
        riwayatList.add(new Riwayat("ITCC", "Kamper","Aktif", "11-11-2011"));
        riwayatList.add(new Riwayat("ITCC", "Kamper","Aktif", "11-11-2011"));
        riwayatList.add(new Riwayat("ITCC", "Kamper","Aktif", "11-11-2011"));
        riwayatList.add(new Riwayat("ITCC", "Kamper","Aktif", "11-11-2011"));
        riwayatList.add(new Riwayat("ITCC", "Kamper","Aktif", "11-11-2011"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        RecyclerView myrey = (RecyclerView) findViewById(R.id.recyclerview_riwayat);
        RiwayatAdapter myadapter = new RiwayatAdapter(this, riwayatList);
        myrey.setLayoutManager(new GridLayoutManager(this, 2));
        myrey.setAdapter(myadapter);
        myrey.setLayoutManager(layoutManager);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




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
