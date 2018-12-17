package com.example.bayuguna.progmob.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.bayuguna.progmob.Adapter.AboutAdapter;
import com.example.bayuguna.progmob.Model.About;
import com.example.bayuguna.progmob.R;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar = null;

    RecyclerView myrey;
    AboutAdapter myadapter;

    List<About> lists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lists = new ArrayList<>();
        lists.add(new About("Isna Wanda Robiulla","1605551091",R.drawable.wanda));
        lists.add(new About("Ni Luh Putu Giri Gita Saraswati","1605551102",R.drawable.gita));
        lists.add(new About("Putu Denanta Bayuguna","1605552011",R.drawable.bayu));

        myrey = (RecyclerView) findViewById(R.id.recyclerview_about);
        myadapter = new AboutAdapter(this, lists);
        myrey.setLayoutManager(new GridLayoutManager(this, 3));
        myrey.setAdapter(myadapter);

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
