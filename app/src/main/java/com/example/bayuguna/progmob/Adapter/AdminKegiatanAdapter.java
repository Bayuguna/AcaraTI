package com.example.bayuguna.progmob.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bayuguna.progmob.Activity.AdminKegiatanActivity;
import com.example.bayuguna.progmob.Model.ListKegiatan;
import com.example.bayuguna.progmob.R;

import java.util.List;

public class AdminKegiatanAdapter extends RecyclerView.Adapter<AdminKegiatanAdapter.MyVieHolder> {

    private Context kContext;
    private List<ListKegiatan> kData;

    public AdminKegiatanAdapter(Context kContext, List<ListKegiatan> kData) {
        this.kContext = kContext;
        this.kData = kData;

    }

    public void setKegiatan(List<ListKegiatan> kData) {
        this.kData = kData;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyVieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater kInflater = LayoutInflater.from(kContext);
        view = kInflater.inflate(R.layout.kegiatan_card,viewGroup,false);
        return new AdminKegiatanAdapter.MyVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVieHolder myVieHolder, final int i) {

        String url = "http://192.168.43.200:8000/"+kData.get(i).getPic();
        Glide.with(myVieHolder.itemView).load(url).into(myVieHolder.Kegiatan_pamflet);

        myVieHolder.Kegiatan_title.setText(kData.get(i).getNama());
        myVieHolder.Open_rec.setText(kData.get(i).getTanggal());
//        myVieHolder.Kegiatan_pamflet.setImageResource(kData.get(i).getPic());
//        myVieHolder.Kegiatan_pamflet.setImageResource(kData.get(i).getPamflet());

        myVieHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(kContext, AdminKegiatanActivity.class);

                intent.putExtra("Id", kData.get(i).getId());
                Log.d("ID", kData.get(i).getId() + "");
                intent.putExtra("Picture", kData.get(i).getPic());
                intent.putExtra("Title", kData.get(i).getNama());
                intent.putExtra("Tanggal", kData.get(i).getTanggal());
                intent.putExtra("Description" ,kData.get(i).getDeskripsi());
                intent.putExtra("Status" ,kData.get(i).getStatus());
//                intent.putExtra("Sie",kData.get(i).getDetKegiatan().getNama());
//                intent.putExtra("Pamflet",kData.get(i).getPamflet());

                kContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(kData != null){
            return kData.size();
        }
        return 0;
    }

    public class MyVieHolder extends RecyclerView.ViewHolder {
        TextView Kegiatan_title;
        TextView Open_rec;
        ImageView Kegiatan_pamflet;
        CardView cardView;
        public MyVieHolder(@NonNull View itemView) {
            super(itemView);
            Kegiatan_title = (TextView) itemView.findViewById(R.id.kegiatan_title);
            Open_rec = (TextView) itemView.findViewById(R.id.open_rec);
            Kegiatan_pamflet = (ImageView) itemView.findViewById(R.id.kegiatan_pic);
            cardView = (CardView) itemView.findViewById(R.id.card_recyclerview);
        }
    }
}
