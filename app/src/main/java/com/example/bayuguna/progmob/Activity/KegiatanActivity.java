package com.example.bayuguna.progmob.Activity;

import android.content.Intent;
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
import com.example.bayuguna.progmob.Adapter.DetailKegiatanAdapter;
import com.example.bayuguna.progmob.DatabaseH.DatabaseHelper;
import com.example.bayuguna.progmob.Model.DetKegiatan;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KegiatanActivity extends  AppCompatActivity {

    private TextView nama,open_rec,description,sie;
    private ImageView pamflet;
    private Button ikut;
    int id_kegiatan,id_user;

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView myrey;
    DetailKegiatanAdapter myadapter;
    String getPic, getStatus;

    ApiService service;
    Call<List<DetKegiatan>> call;
    List<DetKegiatan> lists =  new ArrayList<>();

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kegiatan_detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        nama = (TextView) findViewById(R.id.kegiatan_title);
        description = (TextView) findViewById(R.id.kegiatan_desc);
        pamflet = (ImageView) findViewById(R.id.kegiatan_pic);
        ikut = (Button) findViewById(R.id.register);
        swipeRefreshLayout = findViewById(R.id.swipe);

        service = RetrofitBuilder.creatService(ApiService.class);
        myDb = new DatabaseHelper(this);

        Intent intent = getIntent();
        id_kegiatan = intent.getExtras().getInt("Id");
        id_user = intent.getExtras().getInt("Id_user");
        Log.d("Kegiatan Activity", "" + id_user);
        String title = intent.getExtras().getString("Title");
        String desc = intent.getExtras().getString("Description");
        getPic = intent.getExtras().getString("Picture");
        getStatus = intent.getExtras().getString("Status");
        Log.d("Kegiatan Activity", "" + getStatus);

        nama.setText(title);
        description.setText(desc);
        String url = "http://172.17.100.2:8000/"+getPic;
        Glide.with(KegiatanActivity.this).load(url).into(pamflet);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init(id_kegiatan,id_user);
        lists = new ArrayList<>();
        getData(id_kegiatan);

        myrey = (RecyclerView) findViewById(R.id.recyclerview_sie);
        myadapter = new DetailKegiatanAdapter(this, lists);
        myrey.setLayoutManager(new GridLayoutManager(this, 3));
        myrey.setAdapter(myadapter);

        refresh();

    }

    public void init(final int id_kegiatan, final int id_user) {
        ikut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KegiatanActivity.this, RegisterKepanitianActivity.class);

                intent.putExtra("Id_kegiatan", id_kegiatan);
                intent.putExtra("Id_user", id_user);
                intent.putExtra("Status", getStatus);
                Log.d("IDS Kegiatan", id_user + "");
                startActivity(intent);
            }
        });


    }

    public void getData(int id_kegiatan){
        call = service.getDetKegiatan(id_kegiatan);
        call.enqueue(new Callback<List<DetKegiatan>>() {
            @Override
            public void onResponse(Call<List<DetKegiatan>> call, Response<List<DetKegiatan>> response) {
                lists = response.body();
                    Log.d("Kegiatan Activity Sie", "" + lists);
                myadapter.setSie(lists);

                if (getStatus.equals("Aktif")){
                    ikut.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<List<DetKegiatan>> call, Throwable t) {
                Toast.makeText(KegiatanActivity.this, "Lost Connection", Toast.LENGTH_LONG).show();
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
