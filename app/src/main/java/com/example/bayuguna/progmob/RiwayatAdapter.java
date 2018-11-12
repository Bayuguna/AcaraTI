package com.example.bayuguna.progmob;

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

import java.util.List;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.MyVieHolder> {


    private Context rContext;
    private List<Riwayat> rData;

    public RiwayatAdapter(Context rContext, List<Riwayat> rData) {
        this.rContext = rContext;
        this.rData = rData;
    }

    @NonNull
    @Override
    public MyVieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater kInflater = LayoutInflater.from(rContext);
        view = kInflater.inflate(R.layout.riwayat_card,viewGroup,false);
        return new MyVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVieHolder myVieHolder, final int i) {

        myVieHolder.Title.setText(rData.get(i).getTitle());
        myVieHolder.Sie.setText(rData.get(i).getSie());
        myVieHolder.Status.setText(rData.get(i).getStatus());
        myVieHolder.Tanggal.setText(rData.get(i).getTanggal());


    }

    @Override
    public int getItemCount() {
        return rData.size();
    }

    public static class MyVieHolder extends RecyclerView.ViewHolder {

        TextView Title;
        TextView Sie;
        TextView Status;
        TextView Tanggal;


        public MyVieHolder(View itemView) {
            super(itemView);

            Title = (TextView) itemView.findViewById(R.id.riwayat_title);
            Sie = (TextView) itemView.findViewById(R.id.riwayat_sie);
            Status = (TextView) itemView.findViewById(R.id.riwayat_status);
            Tanggal = (TextView) itemView.findViewById(R.id.riwayat_tanggal);

        }
    }
}
