package com.example.bayuguna.progmob.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bayuguna.progmob.Adapter.AdminKegiatanAdapter;
import com.example.bayuguna.progmob.DatabaseH.DatabaseHelper;
import com.example.bayuguna.progmob.Model.ListKegiatan;
import com.example.bayuguna.progmob.Model.User;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminNavigationActivity extends AppCompatActivity {

    ImageView add;
    android.support.v7.widget.Toolbar toolbar = null;
    SwipeRefreshLayout swipeRefreshLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView nama;
    ImageView img;

    RecyclerView myrey;

    AdminKegiatanAdapter myadapter;
    SharedPreferences userPreference;
    String token,user_name,user_status,pic;
    int id_user;

    ApiService service;
    Call<List<ListKegiatan>> call;
    List<ListKegiatan> lists =  new ArrayList<>();
    private static final String TAG = "AdminNavigationActivity";

    Call<User> callUser;

    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_navigation_dashboard);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View header = navigationView.getHeaderView(0);
        nama = (TextView) header.findViewById(R.id.nama_header_1);
        img = header.findViewById(R.id.pic_nav);

        //API Service
        service = RetrofitBuilder.creatService(ApiService.class);

        //SQLite Database
        myDb = new DatabaseHelper(this);

        //Adapter Reyclerview
        lists = new ArrayList<>();
        getData();

        myrey = (RecyclerView) findViewById(R.id.recyclerview_kegiatan);
        myadapter = new AdminKegiatanAdapter(this, lists);
        myrey.setLayoutManager(new GridLayoutManager(this, 2));
        myrey.setAdapter(myadapter);

        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_dashboard);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigation, R.string.close_navigation);
        drawerLayout.setDrawerListener(toggle);

        toggle.syncState();

        //shared Preference
        userPreference = this.getSharedPreferences("login", Context.MODE_PRIVATE);

        // get token from shared preference
        token = userPreference.getString("token", "missing");
        user_name = userPreference.getString("user_name", "missing");
        id_user = userPreference.getInt("id_user", 0);
        user_status = userPreference.getString("user_status", "missing");
        pic = userPreference.getString("user_pic", "missing");

        if(user_name != "missing" ){
            nama.setText(user_name);

        }

        if(pic != "missing"){
            String url = "http://192.168.43.200:8000/"+pic;
            Glide.with(AdminNavigationActivity.this).load(url).into(img);
        }

        if ( token == "missing") {
            Intent intent = new Intent(AdminNavigationActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        init(id_user);
        init();
        refresh();
    }

    public void init() {
        add = (ImageView) findViewById(R.id.add_kegiatan);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminNavigationActivity.this, AdminAddKegiatanActivity.class ));
            }
        });

    }

    public void getData(){
        call = service.getKegiatan();
        call.enqueue(new Callback<List<ListKegiatan>>() {
            @Override
            public void onResponse(Call<List<ListKegiatan>> call, Response<List<ListKegiatan>> response) {
                Toast.makeText(AdminNavigationActivity.this, "Berhasil",Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    lists = response.body();
//                    Log.d(TAG, "onResponse: "+lists);
                    myadapter.setKegiatan(lists);

                    myDb.deleteKegiatan("kegiatan_table");

                    //request is valid and success
                    for(ListKegiatan listKegiatan : response.body()){


                        boolean isInserted = myDb.insertKegiatan(
                                listKegiatan.getPic(),
                                listKegiatan.getNama(),
                                listKegiatan.getTanggal(),
                                listKegiatan.getDeskripsi(),
                                listKegiatan.getStatus()

                        );

                        if(!isInserted){
                            Toast.makeText(AdminNavigationActivity.this, "Cannot Syncron",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(AdminNavigationActivity.this, "Syncronize",Toast.LENGTH_LONG).show();
                        }
                    }

                } else {
                    Toast.makeText(AdminNavigationActivity.this, response.message(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<ListKegiatan>> call, Throwable t) {
                Toast.makeText(AdminNavigationActivity.this, "You Are Offline",Toast.LENGTH_LONG).show();
                sqlite();
            }
        });

    }

    public void sqlite(){
        lists = new ArrayList<>();
        Cursor res = myDb.getKegiatanSQL();
        if(res.getCount() == 0){
            Toast.makeText(AdminNavigationActivity.this, "No Data ", Toast.LENGTH_LONG).show();
            myadapter.setKegiatan(lists);
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            Toast.makeText(AdminNavigationActivity.this, "Get Data ", Toast.LENGTH_LONG).show();
            ListKegiatan listKegiatan = new ListKegiatan();
            listKegiatan.setNama(res.getString(2));
            listKegiatan.setTanggal(res.getString(3));

            lists.add(listKegiatan);
        }

        myadapter.setKegiatan(lists);
    }

    public void refresh(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },5000);
            }
        });
    }


    public void init(final int id_user) {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View header = navigationView.getHeaderView(0);
        ImageView imageView = (ImageView) header.findViewById(R.id.pic_nav);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AdminNavigationActivity.this, ProfileActivity.class);
//                intent.putExtra("Id", id_user);

                startActivity(intent);
            }

//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//
//                    }
//                });
//
//            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                int id = menuItem.getItemId();
                switch (id) {

                    case R.id.kegiatan:
                        Intent kegiatan = new Intent(AdminNavigationActivity.this, AdminNavigationActivity.class);

                        kegiatan.putExtra("Nama", nama.getText().toString());
//                        kegiatan.putExtra("Id", getId);
                        startActivity(kegiatan);
                        break;
                    case R.id.kepanitiaan:
                        Intent kepanitiaan = new Intent(AdminNavigationActivity.this, AdminKepanitiaanBerlangsungActivity.class);

                        kepanitiaan.putExtra("Nama", nama.getText().toString());
//                        kepanitiaan.putExtra("Id", getId);
                        startActivity(kepanitiaan);
                        break;
                    case R.id.about:
                        Intent about = new Intent(AdminNavigationActivity.this, AboutActivity.class);

                        startActivity(about);
                        break;
                    case R.id.logout:
                        logout();
                        break;
                }

                return true;
            }
        });
    }

    public void logout(){
        new AlertDialog.Builder(AdminNavigationActivity.this)
                .setTitle("Really Logout")
                .setMessage("Are you sure want to logout ?")
                .setNegativeButton(android.R.string.no,null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
                        sharedPref.edit().clear().commit();

                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).create().show();

    }
}
