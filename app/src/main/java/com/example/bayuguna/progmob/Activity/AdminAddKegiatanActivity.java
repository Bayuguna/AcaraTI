package com.example.bayuguna.progmob.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bayuguna.progmob.Model.Kegiatans;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.api.widget.Widget;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAddKegiatanActivity extends AppCompatActivity {

    EditText insert_nama, insert_tanggal, insert_deskripsi;
    Button save,date,foto;
    String path,filename;
    ImageView pic_kegiatan;

    Calendar c;
    DatePickerDialog date_picker;

    private ArrayList<AlbumFile> mAlbumFiles;

    ApiService service;
    Call<Kegiatans> call;
    private static final String TAG = "AdminAddKegiatanActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_tambah_kegiatan);

        date = findViewById(R.id.btn_date);
        insert_nama = (EditText) findViewById(R.id.nama_kegiatan);
        insert_tanggal = (EditText) findViewById(R.id.tanggal_kegiatan);
        insert_deskripsi = (EditText) findViewById(R.id.deskripsi_kegiatan);
        pic_kegiatan = findViewById(R.id.pic_kegiatan);
        save = (Button) findViewById(R.id.btn_save_kegiatan);
        foto = findViewById(R.id.btn_foto);
        init();

        service = RetrofitBuilder.creatService(ApiService.class);

        addKegiatan();
    }

    public void addKegiatan(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody nama = RequestBody.create(MediaType.parse("text/plain"),insert_nama.getText().toString());
                RequestBody tanggal = RequestBody.create(MediaType.parse("text/plain"),insert_tanggal.getText().toString());
                RequestBody deskripsi = RequestBody.create(MediaType.parse("text/plain"),insert_deskripsi.getText().toString());
                File file = new File(path);
                RequestBody requestBody = RequestBody.create(MediaType.parse("pic"), file);
                MultipartBody.Part pic = MultipartBody.Part.createFormData("pic", file.getName(),requestBody);

                addData(pic,nama,tanggal,deskripsi);
            }
        });
    }

    public void addData(MultipartBody.Part pic,RequestBody nama,RequestBody tanggal,RequestBody deskripsi){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String nama = insert_nama.getText().toString();
//                String tanggal = insert_tanggal.getText().toString();
//                String deskripsi = insert_deskripsi.getText().toString();

                call = service.addKegiatan(pic,nama,tanggal,deskripsi);
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
                                Toast.makeText(AdminAddKegiatanActivity.this, response.message(),Toast.LENGTH_LONG).show();
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

    public void init(){
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                date_picker = new DatePickerDialog(AdminAddKegiatanActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        insert_tanggal.setText(mYear + "-" + mMonth + "-" + mDay);
                    }
                }, year, month,day);
                date_picker.show();

            }
        });

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void selectImage() {
        Album.image(this)
                .singleChoice()
                .camera(true)
                .widget(
                        Widget.newDarkBuilder(this)
                                .build()
                )
                .onResult((Action<ArrayList<AlbumFile>>) result -> {
                    path = result.get(0).getPath();
                    Toast.makeText(AdminAddKegiatanActivity.this,"path : "+path,Toast.LENGTH_SHORT).show();
                    Glide.with(AdminAddKegiatanActivity.this).load(path).into(pic_kegiatan);
                    filename = path.substring(path.lastIndexOf("/")+1);
//                    et_logo_kategori.setText(filename);
                    mAlbumFiles = result;
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        Toast.makeText(AdminAddKegiatanActivity.this, "cancell", Toast.LENGTH_LONG).show();
                    }
                })
                .start();
    }
}
