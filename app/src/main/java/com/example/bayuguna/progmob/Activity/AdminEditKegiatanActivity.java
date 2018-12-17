package com.example.bayuguna.progmob.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bayuguna.progmob.Model.Kegiatans;
import com.example.bayuguna.progmob.Model.ListKegiatan;
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
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminEditKegiatanActivity extends AppCompatActivity {

    EditText insert_nama, insert_tanggal, insert_deskripsi;
    Button edit,date,back,foto;
    int getId;
    String getNama,getDeskripsi,getTanggal,path,filename,itemValue,getStatus;
    ImageView img;
    Spinner spinner;

    private ArrayList<AlbumFile> mAlbumFiles;
    Calendar c;
    DatePickerDialog date_picker;

    ApiService service;
    Call<Kegiatans> call;
    Call<List<ListKegiatan>> calls;
    private static final String TAG = "AdminAddKegiatanActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_edit_kegiatan);

        date = findViewById(R.id.btn_date);
        insert_nama = (EditText) findViewById(R.id.nama_kegiatan);
        insert_tanggal = (EditText) findViewById(R.id.tanggal_kegiatan);
        insert_deskripsi = (EditText) findViewById(R.id.deskripsi_kegiatan);
        edit = (Button) findViewById(R.id.btn_save_kegiatan);
        back = findViewById(R.id.btn_back);
        img = findViewById(R.id.pic_kegiatan);
        foto = findViewById(R.id.btn_foto);
        spinner = findViewById(R.id.spinner);

        Intent intent = getIntent();
        getId = intent.getExtras().getInt("Id_kegiatan");
        getNama = intent.getExtras().getString("Nama_kegiatan");
        getTanggal = intent.getExtras().getString("Tanggal_kegiatan");
        getDeskripsi = intent.getExtras().getString("Deskripsi_kegiatan");
        getStatus = intent.getExtras().getString("Status");
        Log.d("Admin Edit Kegiatan", "onCreate: " + getStatus);

        init();

        insert_nama.setText(getNama);
        insert_tanggal.setText(getTanggal);
        insert_deskripsi.setText(getDeskripsi);
//        ArrayAdapter myAdap = (ArrayAdapter) spinner.getAdapter(); //cast to an ArrayAdapter
//
//        int spinnerPosition = myAdap.getPosition(getStatus);
//
//        spinner.setSelection(spinnerPosition);

        service = RetrofitBuilder.creatService(ApiService.class);

        updateData();
        spiners();
    }

    public void spiners(){

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Aktif");
        categories.add("Non Aktif");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemValue = parent.getItemAtPosition(position).toString();

                Toast.makeText(AdminEditKegiatanActivity.this, "Status "+itemValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

//    public void getData(int getId) {
//        calls = service.getDetKegiatans(getId);
//        call.enqueue(new Callback<List<ListKegiatan>>() {
//            @Override
//            public void onResponse(Call<List<ListKegiatan>> call, Response<List<ListKegiatan>> response) {
//                insert_nama.setText(response.body().getNim());
//                nama.setText(response.body().getName());
//                gmail.setText(response.body().getEmail());
//                telp.setText(response.body().getTelp());
//                alamat.setText(response.body().getAlamat());
////                Toast.makeText(ProfileActivity.this,"nim :" + response.body().getNim(), Toast.LENGTH_LONG).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<List<ListKegiatan>> call, Throwable t) {
//                Toast.makeText(AdminEditKegiatanActivity.this,"Lost Connection", Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    public void updateData(){
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            RequestBody name = RequestBody.create(MediaType.parse("text/plain"),insert_nama.getText().toString());
            RequestBody tanggal = RequestBody.create(MediaType.parse("text/plain"),insert_tanggal.getText().toString());
            RequestBody deskripsi = RequestBody.create(MediaType.parse("text/plain"),insert_deskripsi.getText().toString());
            RequestBody status = RequestBody.create(MediaType.parse("text/plain"),itemValue);
            File file = new File(path);
            RequestBody requestBody = RequestBody.create(MediaType.parse("pic"), file);
            MultipartBody.Part pic = MultipartBody.Part.createFormData("pic", file.getName(),requestBody);

            editData(getId,pic,name,tanggal,deskripsi,status);


            }
        });
    }

    public void editData(int id_user, MultipartBody.Part pic, RequestBody name, RequestBody tanggal, RequestBody deskripsi,RequestBody status){
                call = service.editKegiatan(id_user,pic,name,tanggal,deskripsi,status);
                call.enqueue(new Callback<Kegiatans>() {
                    @Override
                    public void onResponse(Call<Kegiatans> call, Response<Kegiatans> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(AdminEditKegiatanActivity.this,"Kegiatan Berhasil Diperbaharui" , Toast.LENGTH_LONG).show();
                            onBackPressed();
                            finish();
                        }else {
                            Toast.makeText(AdminEditKegiatanActivity.this,response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Kegiatans> call, Throwable t) {
                        Toast.makeText(AdminEditKegiatanActivity.this,"You Are Offline", Toast.LENGTH_LONG).show();
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

                date_picker = new DatePickerDialog(AdminEditKegiatanActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        insert_tanggal.setText(mYear + "-" + mMonth + "-" + mDay);
                    }
                }, year, month,day);
                date_picker.show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
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
                    Toast.makeText(AdminEditKegiatanActivity.this,"path : "+path,Toast.LENGTH_SHORT).show();
                    Glide.with(AdminEditKegiatanActivity.this).load(path).into(img);
                    filename = path.substring(path.lastIndexOf("/")+1);
//                    et_logo_kategori.setText(filename);
                    mAlbumFiles = result;
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        Toast.makeText(AdminEditKegiatanActivity.this, "cancell", Toast.LENGTH_LONG).show();
                    }
                })
                .start();
    }

}
