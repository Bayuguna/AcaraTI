package com.example.bayuguna.progmob.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bayuguna.progmob.Adapter.RiwayatAdapter;
import com.example.bayuguna.progmob.DatabaseH.DatabaseHelper;
import com.example.bayuguna.progmob.Model.RiwayatKepanitiaanResponse;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatActivity extends  AppCompatActivity {

    RecyclerView myrey;

    RiwayatAdapter myadapter;

    ApiService service;
    DatabaseHelper myDb;
    SwipeRefreshLayout swipeRefreshLayout;

    Call<List<RiwayatKepanitiaanResponse>> call;
    List<RiwayatKepanitiaanResponse> lists;
    private static final String TAG = "RiwayatActivity";

    String token;
    int id_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayat_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



//        riwayatList = new ArrayList<>();
//        riwayatList.add(new Riwayat("ITCC", "Kamper","Aktif", "11-11-2011"));
//        riwayatList.add(new Riwayat("ITCC", "Kamper","Aktif", "11-11-2011"));
//        riwayatList.add(new Riwayat("ITCC", "Kamper","Aktif", "11-11-2011"));
//        riwayatList.add(new Riwayat("ITCC", "Kamper","Aktif", "11-11-2011"));
//        riwayatList.add(new Riwayat("ITCC", "Kamper","Aktif", "11-11-2011"));

//        SharedPreferences sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE);
//        String token = sharedPreferences.getString("token", "missing");

        swipeRefreshLayout = findViewById(R.id.swipe);

        service = RetrofitBuilder.creatService(ApiService.class);
        myDb = new DatabaseHelper(this);

        SharedPreferences userPreference = this.getSharedPreferences("login", Context.MODE_PRIVATE);

        // get token from shared preference
        token = userPreference.getString("token", "missing");
        id_user = userPreference.getInt("id_user", 0);



        if ( token == "missing") {
            Intent intent = new Intent(RiwayatActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        lists = new ArrayList<>();
        getData(id_user);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        myrey = (RecyclerView) findViewById(R.id.recyclerview_riwayat);
        myadapter = new RiwayatAdapter(this, lists);
        myrey.setLayoutManager(new GridLayoutManager(this, 2));
        myrey.setAdapter(myadapter);
        myrey.setLayoutManager(layoutManager);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        refresh();


    }

    public void getData(int id_user){
        call = service.getRiwayat(id_user);
        call.enqueue(new Callback<List<RiwayatKepanitiaanResponse>>() {
            @Override
            public void onResponse(Call<List<RiwayatKepanitiaanResponse>> call, Response<List<RiwayatKepanitiaanResponse>> response) {
                if (response.isSuccessful()) {
                    lists = response.body();
//                    Log.d(TAG, "onResponse: "+lists);
                    myadapter.setRiwayat(lists);

                    myDb.deleteKepanitiaan("kepanitiaan_table");

                    for (RiwayatKepanitiaanResponse riwayatKepanitiaan : response.body()){

                        boolean isInserted = myDb.insertKepanitiaan(
                                riwayatKepanitiaan.getId(),
                                riwayatKepanitiaan.getNama(),
                                riwayatKepanitiaan.getSie(),
                                riwayatKepanitiaan.getAlasan(),
                                riwayatKepanitiaan.getTanggal(),
                                riwayatKepanitiaan.getStatus()
                        );

                        if(!isInserted){
                            Toast.makeText(RiwayatActivity.this, "Cannot Syncron",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(RiwayatActivity.this, "Syncronize",Toast.LENGTH_LONG).show();
                        }
                    }

                } else {
                    Toast.makeText(RiwayatActivity.this, response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<RiwayatKepanitiaanResponse>> call, Throwable t) {
                Toast.makeText(RiwayatActivity.this, "You Are Offline",Toast.LENGTH_LONG).show();
                sqlite();
            }
        });
    }

    public void sqlite(){
        lists = new ArrayList<>();
        Cursor res = myDb.getKepanitiaanSQL();
        if(res.getCount() == 0){
            Toast.makeText(RiwayatActivity.this, "No Data ", Toast.LENGTH_LONG).show();
            myadapter.setRiwayat(lists);
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            Toast.makeText(RiwayatActivity.this, "Get Data ", Toast.LENGTH_LONG).show();
            RiwayatKepanitiaanResponse riwayatKepanitiaanResponse = new RiwayatKepanitiaanResponse();
            riwayatKepanitiaanResponse.setNama(res.getString(2));
            riwayatKepanitiaanResponse.setSie(res.getString(3));
            riwayatKepanitiaanResponse.setTanggal(res.getString(5));

            lists.add(riwayatKepanitiaanResponse);
        }

        myadapter.setRiwayat(lists);
    }

    public void refresh(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(id_user);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },5000);
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
