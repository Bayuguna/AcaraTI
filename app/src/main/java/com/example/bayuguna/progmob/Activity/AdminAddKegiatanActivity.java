package com.example.bayuguna.progmob.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bayuguna.progmob.Model.Kegiatans;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAddKegiatanActivity extends AppCompatActivity {

    EditText insert_nama, insert_tanggal, insert_deskripsi;
    Button save;

    ApiService service;
    Call<Kegiatans> call;
    private static final String TAG = "AdminAddKegiatanActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_tambah_kepanitiaan);

        insert_nama = (EditText) findViewById(R.id.nama_kegiatan);
        insert_tanggal = (EditText) findViewById(R.id.tanggal_kegiatan);
        insert_deskripsi = (EditText) findViewById(R.id.deskripsi_kegiatan);
        save = (Button) findViewById(R.id.btn_save_kegiatan);
        addData();

        service = RetrofitBuilder.creatService(ApiService.class);
    }

    public void addData(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = insert_nama.getText().toString();
                String tanggal = insert_tanggal.getText().toString();
                String deskripsi = insert_deskripsi.getText().toString();

                call = service.addKegiatan(nama,tanggal,deskripsi);
                call.enqueue(new Callback<Kegiatans>() {
                    @Override
                    public void onResponse(Call<Kegiatans> call, Response<Kegiatans> response) {

                        if (insert_nama.getText().toString().isEmpty() && insert_tanggal.getText().toString().isEmpty() && insert_deskripsi.getText().toString().isEmpty()){
                            Toast.makeText(AdminAddKegiatanActivity.this, "Isi dulu fieldnya",Toast.LENGTH_LONG).show();
                        }else {
                            if (response.isSuccessful()){
                                Toast.makeText(AdminAddKegiatanActivity.this, "Kegiatan berhasil ditambah",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(AdminAddKegiatanActivity.this, AdminNavigationActivity.class);
                                startActivity(intent);

                            }else {
                                Toast.makeText(AdminAddKegiatanActivity.this, "Tidak semudah itu Aguero",Toast.LENGTH_LONG).show();
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<Kegiatans> call, Throwable t) {
                        Toast.makeText(AdminAddKegiatanActivity.this, "Lost Connection",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
