package com.example.bayuguna.progmob.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bayuguna.progmob.Model.Sie;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminTambahSieActivity extends AppCompatActivity {

    Button save;
    EditText nama,insert_sie,insert_kuota,insert_koor,insert_line,insert_job_desc;
    TextView getId;
    ApiService service;
    retrofit2.Call<Sie> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_tambah_sie);

//        getId = (TextView) findViewById(R.id.id_sie);

        save = (Button) findViewById(R.id.btn_save_sie);
        nama = (EditText) findViewById(R.id.nama_kegiatan);
        insert_sie = (EditText) findViewById(R.id.sie);
        insert_kuota = (EditText) findViewById(R.id.kuota);
        insert_koor = (EditText) findViewById(R.id.nama_koor);
        insert_line = (EditText) findViewById(R.id.line_id);
        insert_job_desc = (EditText) findViewById(R.id.job_desc);

        Intent intent = getIntent();
        int id_kegiatan = intent.getExtras().getInt("Id_kegiatan");
        Log.d("ID IN ADMIN KEGIATAN", id_kegiatan + "");

        String getNama = intent.getExtras().getString("Nama_kepanitiaan");

        nama.setText(getNama);
//        getId.setText(String.valueOf(id_kegiatan));

        service = RetrofitBuilder.creatService(ApiService.class);
        addData(id_kegiatan);


    }

    public void addData(final int id_kegiatan){

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int ids = id_kegiatan;
                String sie = insert_sie.getText().toString();
                String kuota = insert_kuota.getText().toString();
                String koor = insert_koor.getText().toString();
                String line = insert_line.getText().toString();
                String job_desc = insert_job_desc.getText().toString();

                call = service.addDetKegiatan(id_kegiatan,sie,job_desc,kuota,koor,line);
                call.enqueue(new Callback<Sie>() {
                    @Override
                    public void onResponse(Call<Sie> call, Response<Sie> response) {

                        if (insert_sie.getText().toString().isEmpty() && insert_kuota.getText().toString().isEmpty() && insert_koor.getText().toString().isEmpty() && insert_line.getText().toString().isEmpty()){
                            Toast.makeText(AdminTambahSieActivity.this, "Isi dulu fieldnya",Toast.LENGTH_LONG).show();
                        }else {
                            if (response.isSuccessful()){
                                Toast.makeText(AdminTambahSieActivity.this, "Sie Berhasil Ditambahkan",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(AdminTambahSieActivity.this, AdminKegiatanActivity.class);
                                intent.putExtra("Id_kegiatan", response.body().getId());
                                startActivity(intent);

                            }else {
                                Toast.makeText(AdminTambahSieActivity.this, "Tidak semudah itu Alakardo",Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<Sie> call, Throwable t) {
                        Toast.makeText(AdminTambahSieActivity.this, "Gagal Tambah Sie",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}
