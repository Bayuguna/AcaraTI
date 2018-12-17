package com.example.bayuguna.progmob.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bayuguna.progmob.Adapter.AdminDetailKegiatanAdapter;
import com.example.bayuguna.progmob.DatabaseH.DatabaseHelper;
import com.example.bayuguna.progmob.Model.DetKegiatan;
import com.example.bayuguna.progmob.Model.DetKegiatanItem;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminKegiatanActivity extends  AppCompatActivity {

    private TextView nama,open_rec,description,sie;
    private ImageView pamflet;
    private Button tambah_sie,peserta,edit_kegiatan;
    String getTanggal,getPic,getStatus;
    int id_kegiatan;

    RecyclerView myrey;
    AdminDetailKegiatanAdapter myadapter;
    DatabaseHelper myDb;

    ApiService service;
    Call<List<DetKegiatan>> call;
    List<DetKegiatan> lists =  new ArrayList<>();

    Call<DetKegiatanItem> delete;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_kegiatan_detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nama = (TextView) findViewById(R.id.kegiatan_title);
        description = (TextView) findViewById(R.id.kegiatan_desc);
        pamflet = (ImageView) findViewById(R.id.kegiatan_pic);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);

        service = RetrofitBuilder.creatService(ApiService.class);

        Intent intent = getIntent();
        id_kegiatan = intent.getExtras().getInt("Id");
//        Log.d("ID IN ADMIN KEGIATAN", id_kegiatan + "");
        String title = intent.getExtras().getString("Title");
        String desc = intent.getExtras().getString("Description");
        getTanggal = intent.getExtras().getString("Tanggal");
        getPic = intent.getExtras().getString("Picture");
        getStatus = intent.getExtras().getString("Status");
        Log.d("STATUS ADMIN KEGIATAN", getStatus + "");

        nama.setText(title);
        description.setText(desc);
        String url = "http://172.17.100.2:8000/"+getPic;
        Glide.with(AdminKegiatanActivity.this).load(url).into(pamflet);

        lists = new ArrayList<>();
        getData(id_kegiatan);

        myrey = (RecyclerView) findViewById(R.id.recyclerview_sie);
        myadapter = new AdminDetailKegiatanAdapter(this, lists);
        myrey.setLayoutManager(new GridLayoutManager(this, 3));
        myrey.setAdapter(myadapter);

        myDb = new DatabaseHelper(this);
        init(id_kegiatan);
        refresh();
    }

    public void getData(final int id_kegiatan){
        call = service.getDetKegiatan(id_kegiatan);
        call.enqueue(new Callback<List<DetKegiatan>>() {
            @Override
            public void onResponse(Call<List<DetKegiatan>> call, Response<List<DetKegiatan>> response) {
                lists = response.body();
                Log.d("sie"," " + response.body());
                myadapter.setSie(lists);

                myDb.deleteDetKegiatan("det_kegiatan_table");

                //request is valid and success
                for(DetKegiatan listsie : response.body()) {



                    boolean isInserted = myDb.insertDetKegiatan(
                            listsie.getIdKegiatan(),
                            listsie.getSie(),
                            listsie.getJob(),
                            listsie.getKuota(),
                            listsie.getKoor(),
                            listsie.getLine()
                    );

                    if (!isInserted) {
                        Toast.makeText(AdminKegiatanActivity.this, "Cannot Syncron", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AdminKegiatanActivity.this, "Syncronize", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DetKegiatan>> call, Throwable t) {
                Toast.makeText(AdminKegiatanActivity.this, "Lost Connection", Toast.LENGTH_LONG).show();
                sqlite(id_kegiatan);
            }
        });

    }

    public void sqlite(int id_kegiatan){
        lists = new ArrayList<>();
        Cursor res = myDb.getDetKegiatanSQL(id_kegiatan);
        if(res.getCount() == 0){
            Toast.makeText(AdminKegiatanActivity.this, "No Data ", Toast.LENGTH_LONG).show();
            myadapter.setSie(lists);
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            Toast.makeText(AdminKegiatanActivity.this, "Get Data ", Toast.LENGTH_LONG).show();
            DetKegiatan listKegiatan = new DetKegiatan();
            listKegiatan.setSie(res.getString(2));
            listKegiatan.setKoor(res.getString(5));
            listKegiatan.setLine(res.getString(6));

            lists.add(listKegiatan);
        }

        myadapter.setSie(lists);
    }

    public void init(final int id_kegiatan){
        tambah_sie = (Button) findViewById(R.id.btn_tambah_sie);
        tambah_sie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminKegiatanActivity.this, AdminTambahSieActivity.class);

                intent.putExtra("Id_kegiatan", id_kegiatan);
                intent.putExtra("Nama_kegiatan", nama.getText().toString());
                startActivity(intent);
            }
        });

        edit_kegiatan = (Button) findViewById(R.id.btn_edit_kegiatan);
        edit_kegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminKegiatanActivity.this, AdminEditKegiatanActivity.class);

                intent.putExtra("Id_kegiatan", id_kegiatan);
                intent.putExtra("Nama_kegiatan", nama.getText().toString());
                intent.putExtra("Tanggal_kegiatan", getTanggal);
                intent.putExtra("Deskripsi_kegiatan", description.getText().toString());
                intent.putExtra("Status", getStatus);
                startActivity(intent);
            }
        });

        peserta = (Button) findViewById(R.id.btn_peserta);
        peserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminKegiatanActivity.this, AdminPesertaActivity.class);

                intent.putExtra("Id", id_kegiatan);
                startActivity(intent);
            }
        });
    }

    public void refresh(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(id_kegiatan);

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
