package com.example.bayuguna.progmob.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bayuguna.progmob.Activity.KegiatanActivity;
import com.example.bayuguna.progmob.Model.Kegiatan;
import com.example.bayuguna.progmob.R;

import java.util.List;

public class KegiatanAdapter extends RecyclerView.Adapter<KegiatanAdapter.MyVieHolder> {


    private Context kContext;
    private List<Kegiatan> kData;

    public KegiatanAdapter(Context kContext, List<Kegiatan> kData) {
        this.kContext = kContext;
        this.kData = kData;
    }

    public void setKegiatan(List<Kegiatan> kData) {
        this.kData = kData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyVieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater kInflater = LayoutInflater.from(kContext);
        view = kInflater.inflate(R.layout.kegiatan_card,viewGroup,false);
        return new MyVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVieHolder myVieHolder, final int i) {

        myVieHolder.Kegiatan_title.setText(kData.get(i).getNama());
        myVieHolder.Open_rec.setText(kData.get(i).getOpen_recruitment());
        myVieHolder.Kegiatan_pamflet.setImageResource(kData.get(i).getPamflet());

        myVieHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(kContext, KegiatanActivity.class);

                intent.putExtra("Title", kData.get(i).getNama());
                intent.putExtra("Tanggal", kData.get(i).getOpen_recruitment());
                intent.putExtra("Description" ,kData.get(i).getDescription());
                intent.putExtra("Pamflet",kData.get(i).getPamflet());

                kContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return kData.size();
    }

    public static class MyVieHolder extends RecyclerView.ViewHolder {

        TextView Kegiatan_title;
        TextView Open_rec;
        ImageView Kegiatan_pamflet;
        CardView cardView;


        public MyVieHolder(View itemView) {
            super(itemView);

            Kegiatan_title = (TextView) itemView.findViewById(R.id.kegiatan_title);
            Open_rec = (TextView) itemView.findViewById(R.id.open_rec);
            Kegiatan_pamflet = (ImageView) itemView.findViewById(R.id.kegiatan_pic);
            cardView = (CardView) itemView.findViewById(R.id.card_recyclerview);

        }
    }
}
