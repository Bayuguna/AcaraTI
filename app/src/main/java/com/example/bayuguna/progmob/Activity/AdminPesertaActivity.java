package com.example.bayuguna.progmob.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.bayuguna.progmob.Adapter.PesertaAdapter;
import com.example.bayuguna.progmob.Model.Peserta;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPesertaActivity extends AppCompatActivity {

    RecyclerView myrey;
    PesertaAdapter myadapter;

    ApiService service;
    Call<List<Peserta>> call;
    List<Peserta> lists =  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_peserta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        service = RetrofitBuilder.creatService(ApiService.class);

        Intent intent = getIntent();
        int id_kegiatan = intent.getExtras().getInt("Id");
//        Log.d("Ini Admin Peserta", "ID Kegiatan: " + id_kegiatan);

        lists = new ArrayList<>();
        getData(id_kegiatan);

        myrey = (RecyclerView) findViewById(R.id.recyclerview_peserta);
        myadapter = new PesertaAdapter(this, lists);
        myrey.setLayoutManager(new GridLayoutManager(this, 2));
        myrey.setAdapter(myadapter);

    }

    public void getData(int id_kegiatan){
        call = service.getPeserta(id_kegiatan);
        call.enqueue(new Callback<List<Peserta>>() {
            @Override
            public void onResponse(Call<List<Peserta>> call, Response<List<Peserta>> response) {
//                Toast.makeText(AdminPesertaActivity.this, "Welcome " + response.body(),Toast.LENGTH_LONG).show();
                lists = response.body();
                myadapter.setPeserta(lists);
            }

            @Override
            public void onFailure(Call<List<Peserta>> call, Throwable t) {

            }
        });

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
