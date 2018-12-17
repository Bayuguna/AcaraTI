package com.example.bayuguna.progmob.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bayuguna.progmob.Model.RiwayatKepanitiaan;
import com.example.bayuguna.progmob.Model.SieSpinner;
import com.example.bayuguna.progmob.Model.User;
import com.example.bayuguna.progmob.Model.UserResponse;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterKepanitianActivity extends AppCompatActivity {
    ApiService service;
    retrofit2.Call<RiwayatKepanitiaan> call;
    EditText alasan;
    Button save;
    Spinner sie;
    Call<List<SieSpinner>> call_spiner;
    Call<UserResponse<User>> callUser;
    List<SieSpinner> allSie = new ArrayList<>();
    int id_det_kegiatan;
    SharedPreferences userPreference;
    private String token,user_name;
    private  int id_user;


    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_kepanitian);

        alasan = findViewById(R.id.alasan);
        save = findViewById(R.id.kirim);
        sie = findViewById(R.id.sie_spinner);

        Intent intent = getIntent();
        int id_kegiatan = intent.getExtras().getInt("Id_kegiatan");
//        Log.d("IDS REGISTER ", id_kegiatan + "");


        service = RetrofitBuilder.creatService(ApiService.class);

        userPreference = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        // get token from shared preference
        token = userPreference.getString("token", "missing");
        id_user = userPreference.getInt("id_user", 0);
        user_name = userPreference.getString("user_name", "missing");

        Log.d("Token Response", "onCreate: " + token);
        Log.d("ID Response", "onCreate: " + id_user);
        Log.d("Name Response", "onCreate: " + user_name);

        if (token == "missing") {
            Intent intens = new Intent(RegisterKepanitianActivity.this, LoginActivity.class);
            startActivity(intens);
        }

        sie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                for (int i = 0; i < allSie.size(); i++){
                    if (selectedName.equals(allSie.get(i).getSie())){
                        id_det_kegiatan = allSie.get(i).getId();

                        addData(id_det_kegiatan);
                        Toast.makeText(RegisterKepanitianActivity.this, "Kamu Memilih Sie " + selectedName, Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getData(id_kegiatan);
    }

    public void getData(int id_kegiatan){
        call_spiner = service.getSieSpinner(id_kegiatan);
        call_spiner.enqueue(new Callback<List<SieSpinner>>() {
            @Override
            public void onResponse(Call<List<SieSpinner>> call, Response<List<SieSpinner>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterKepanitianActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    allSie = response.body();
                    List<String> lists = new ArrayList<String>();
                    for (int i = 0; i < allSie.size(); i++){
                        lists.add(allSie.get(i).getSie());
                    }
                    adapter = new ArrayAdapter(RegisterKepanitianActivity.this, android.R.layout.simple_spinner_item,lists);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sie.setAdapter(adapter);

                } else {
                    Toast.makeText(RegisterKepanitianActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SieSpinner>> call, Throwable t) {
                Toast.makeText(RegisterKepanitianActivity.this, "You Are Offline", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addData(final int id_det_kegiatan){

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String insert_alasan = alasan.getText().toString();
                Log.d("Alasan REGISTER ", id_det_kegiatan + "");

                call = service.ikutKepanitiaan(token, id_user, id_det_kegiatan,insert_alasan);
                call.enqueue(new Callback<RiwayatKepanitiaan>() {
                    @Override
                    public void onResponse(Call<RiwayatKepanitiaan> call, Response<RiwayatKepanitiaan> response) {
                        if (alasan.getText().toString().isEmpty()){
                            Toast.makeText(RegisterKepanitianActivity.this, "Isi dulu fieldnya",Toast.LENGTH_LONG).show();
                        }else {
                            if (response.isSuccessful()){
                                Toast.makeText(RegisterKepanitianActivity.this, "Register Berhasil Dilakuakan",Toast.LENGTH_LONG).show();
                                onBackPressed();
                                finish();
                            }else {
                                Toast.makeText(RegisterKepanitianActivity.this, response.message(),Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<RiwayatKepanitiaan> call, Throwable t) {
                        Toast.makeText(RegisterKepanitianActivity.this, "Lost Connection",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

}
