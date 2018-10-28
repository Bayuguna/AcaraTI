package com.example.bayuguna.progmob;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity{
    android.support.v7.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    List<Kegiatan> listkegiatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_dashboard);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listkegiatan = new ArrayList<>();
        listkegiatan.add(new Kegiatan("Sporti", "11-11-2018","Sport TI koskdokdoksorjosdf ojdfosfoksf sofkoskfoksd osekfoskeofksd fspfkpskfpse spfskdfpksepk sfkpskfsekoskf fsojfosfoejfmso",R.drawable.header_navigation));
        listkegiatan.add(new Kegiatan("IT-Esega", "11-11-2018","E-sport Game",R.drawable.header_navigation));
        listkegiatan.add(new Kegiatan("Semnas TI", "11-11-2018","Seminar Nasional",R.drawable.header_navigation));
        listkegiatan.add(new Kegiatan("Musang", "11-11-2018","Musyawarah Mahasiswa",R.drawable.header_navigation));
        listkegiatan.add(new Kegiatan("Sporti", "11-11-2018","Sport TI",R.drawable.header_navigation));
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
    }
}
