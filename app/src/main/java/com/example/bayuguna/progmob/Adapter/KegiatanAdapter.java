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

import com.bumptech.glide.Glide;
import com.example.bayuguna.progmob.Activity.KegiatanActivity;
import com.example.bayuguna.progmob.Model.ListKegiatan;
import com.example.bayuguna.progmob.Model.User;
import com.example.bayuguna.progmob.R;

import java.util.List;

public class KegiatanAdapter extends RecyclerView.Adapter<KegiatanAdapter.MyVieHolder> {


    private Context kContext;
//    private List<Kegiatan> kData;
    private  List<ListKegiatan> kData;
    List<User> user;

//    private List<ListKegiatan> kData;
    public KegiatanAdapter(Context kContext, List<ListKegiatan> kData) {
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
        return new MyVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVieHolder myVieHolder, final int i) {

        String url = "http://172.17.100.2:8000/"+kData.get(i).getPic();
        Glide.with(myVieHolder.itemView).load(url).into(myVieHolder.Kegiatan_pamflet);

        myVieHolder.Kegiatan_title.setText(kData.get(i).getNama());
        myVieHolder.Open_rec.setText(kData.get(i).getTanggal());

        myVieHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(kContext, KegiatanActivity.class);

//                intent.putExtra("Id_user", kData.get(i).getUser().getId());
                intent.putExtra("Id", kData.get(i).getId());
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
