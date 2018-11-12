package com.example.bayuguna.progmob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class DasboardActivity extends AppCompatActivity{
    android.support.v7.widget.Toolbar toolbar;
    List<Kegiatan> listkegiatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_dashboard);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.settings){

            Toast.makeText(getApplicationContext(), "Settings is selected", Toast.LENGTH_SHORT).show();

        }else if(id == R.id.about){

            Toast.makeText(getApplicationContext(), "About is selected", Toast.LENGTH_SHORT).show();

        }else if (id == R.id.logout){

            Toast.makeText(getApplicationContext(), "Logout is selected", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}
