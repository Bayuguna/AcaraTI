package com.example.bayuguna.progmob.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bayuguna.progmob.Model.About;
import com.example.bayuguna.progmob.R;

import java.util.List;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.MyViewHolder> {

    private Context kContext;
    List<About> kData;

    public AboutAdapter(Context kContext, List<About> kData) {
        this.kContext = kContext;
        this.kData = kData;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater kInflater = LayoutInflater.from(kContext);
        view = kInflater.inflate(R.layout.about_card,viewGroup,false);
        return new AboutAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.nama.setText(kData.get(i).getNama());
        myViewHolder.nim.setText(String.valueOf(kData.get(i).getNim()));
        myViewHolder.foto.setImageResource(kData.get(i).getFoto());
    }

    @Override
    public int getItemCount() {
        if(kData != null){
            return kData.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama,nim;
        ImageView foto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            foto = itemView.findViewById(R.id.foto_about);
            nama = itemView.findViewById(R.id.nama_about);
            nim = itemView.findViewById(R.id.nim_about);
        }
    }
}
