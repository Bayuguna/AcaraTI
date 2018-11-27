package com.example.bayuguna.progmob.Activity;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.bayuguna.progmob.Adapter.DetailKegiatanAdapter;
import com.example.bayuguna.progmob.Model.Sie;
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

    RecyclerView myrey;
    DetailKegiatanAdapter myadapter;

    ApiService service;
    Call<List<Sie>> call;
    List<Sie> lists =  new ArrayList<>();

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

        service = RetrofitBuilder.creatService(ApiService.class);

        Intent intent = getIntent();
        int id_kegiatan = intent.getExtras().getInt("Id");
        String title = intent.getExtras().getString("Title");
        String desc = intent.getExtras().getString("Description");

        nama.setText(title);
        description.setText(desc);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init(id_kegiatan);
        lists = new ArrayList<>();
        getData(id_kegiatan);

        myrey = (RecyclerView) findViewById(R.id.recyclerview_sie);
        myadapter = new DetailKegiatanAdapter(this, lists);
        myrey.setLayoutManager(new GridLayoutManager(this, 3));
        myrey.setAdapter(myadapter);

    }

    public void init(final int id_kegiatan) {
        ikut = (Button) findViewById(R.id.register);
        ikut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KegiatanActivity.this, RegisterKepanitianActivity.class);

                intent.putExtra("Id_kegiatan", id_kegiatan);
                Log.d("IDS Kegiatan", id_kegiatan + "");
                startActivity(intent);
            }
        });


    }

    public void getData(int id_kegiatan){
        call = service.getDetSie(id_kegiatan);
        call.enqueue(new Callback<List<Sie>>() {
            @Override
            public void onResponse(Call<List<Sie>> call, Response<List<Sie>> response) {
                lists = response.body();
//                    Log.d(TAG, "onResponse: "+lists);
                myadapter.setKegiatan(lists);
            }

            @Override
            public void onFailure(Call<List<Sie>> call, Throwable t) {
                Toast.makeText(KegiatanActivity.this, "Lost Connection", Toast.LENGTH_LONG).show();
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
