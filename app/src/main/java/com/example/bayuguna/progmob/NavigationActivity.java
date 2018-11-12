package com.example.bayuguna.progmob;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar = null;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    List<Kegiatan> listkegiatan;

//    ImageView imageView;
//
//    public void init() {
//        imageView = (ImageView) findViewById(R.id.pic_nav);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(NavigationActivity.this, ProfileActivity.class);
//
//                startActivity(intent);
//            }
//        });
//
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_dashboard);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listkegiatan = new ArrayList<>();
        listkegiatan.add(new Kegiatan("Sporti", "11-11-2018","Spo rt TI koskdokdoksorjosdf ojdfosfoksf sofkoskfoksd osekfoskeofksd fspfkpskfpse spfskdfpksepk sfkpskfsekoskf fsojfosfoejfmso koadkoaksdo oakdoaksodka oakdoakodak oakdoakosd oakdoaksdoka oakdoaksod aodkoakdo aokdoaksodk aodkaoskdo",R.drawable.header_navigation));
        listkegiatan.add(new Kegiatan("ITCC", "11-11-2018","ITCC adalah",R.drawable.itcc));
        listkegiatan.add(new Kegiatan("IT-Esega", "11-11-2018","E-sport Game",R.drawable.itesega));
        listkegiatan.add(new Kegiatan("Semnas TI", "11-11-2018","Seminar Nasional",R.drawable.semnas_ti));
        listkegiatan.add(new Kegiatan("Musang", "11-11-2018","Musyawarah Mahasiswa",R.drawable.header_navigation));
        listkegiatan.add(new Kegiatan("Sporti", "11-11-2018","Sport TI",R.drawable.header_navigation));
        listkegiatan.add(new Kegiatan("Sporti", "11-11-2018","Sport TI",R.drawable.header_navigation));


        RecyclerView myrey = (RecyclerView) findViewById(R.id.recyclerview_kegiatan);
        KegiatanAdapter myadapter = new KegiatanAdapter(this,listkegiatan);
        myrey.setLayoutManager(new GridLayoutManager(this, 2));
        myrey.setAdapter(myadapter);

        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_dashboard);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_navigation,R.string.close_navigation);
        drawerLayout.setDrawerListener(toggle);

        toggle.syncState();
        init();
    }

    public void init() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View header = navigationView.getHeaderView(0);
        ImageView imageView = (ImageView) header.findViewById(R.id.pic_nav);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, ProfileActivity.class);

                startActivity(intent);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                int id = menuItem.getItemId();
                switch (id){

                    case R.id.kegiatan:
                        Intent kegiatan = new Intent(NavigationActivity.this, NavigationActivity.class);
                        startActivity(kegiatan);
                        break;
                    case R.id.pengumuman:
                        Intent pengumuman = new Intent(NavigationActivity.this, ProfileActivity.class);
                        startActivity(pengumuman);
                        break;
                    case R.id.riwayat_kepanitiaan:
                        Intent riwayat = new Intent(NavigationActivity.this, RiwayatActivity.class);
                        startActivity(riwayat);
                        break;
                    case R.id.logout:
                        Intent logout = new Intent(NavigationActivity.this, LoginActivity.class);
                        startActivity(logout);
                        finish();
                        break;
                }

                return true;
            }
        });
    }
}
