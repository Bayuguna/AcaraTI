package com.example.bayuguna.progmob.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bayuguna.progmob.Adapter.RiwayatAdapter;
import com.example.bayuguna.progmob.Model.ListKegiatan;
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
//    Call<List<Riwayat>> call;

    Call<List<ListKegiatan>> call;
//    List<Riwayat> lists = new ArrayList<>();
    List<ListKegiatan> lists;
    private static final String TAG = "RiwayatActivity";


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

        service = RetrofitBuilder.creatService(ApiService.class);
        lists = new ArrayList<>();
        getData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        myrey = (RecyclerView) findViewById(R.id.recyclerview_riwayat);
        myadapter = new RiwayatAdapter(this, lists);
        myrey.setLayoutManager(new GridLayoutManager(this, 2));
        myrey.setAdapter(myadapter);
        myrey.setLayoutManager(layoutManager);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }

//    public void getData(){
//        call = service.getAllKegiatan();
//        call.enqueue(new Callback<List<Riwayat>>() {
//            @Override
//            public void onResponse(Call<List<Riwayat>> call, Response<List<Riwayat>> response) {
//                if (response.isSuccessful()) {
//                    lists = response.body();
////                    Log.d(TAG, "onResponse: "+lists);
//                    myadapter.setKegiatan(lists);
//
//                } else {
//                    Toast.makeText(RiwayatActivity.this, "Error Cuy",Toast.LENGTH_LONG).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<List<Riwayat>> call, Throwable t) {
//                Toast.makeText(RiwayatActivity.this, "Gagal Oyy",Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }

    public void getData(){
        call = service.getListKegiatan();
        call.enqueue(new Callback<List<ListKegiatan>>() {
            @Override
            public void onResponse(Call<List<ListKegiatan>> call, Response<List<ListKegiatan>> response) {
                if (response.isSuccessful()) {
                    lists = response.body();
//                    Log.d(TAG, "onResponse: "+lists);
                    myadapter.setKegiatan(lists);

                } else {
                    Toast.makeText(RiwayatActivity.this, "Error Cuy",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<ListKegiatan>> call, Throwable t) {

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
