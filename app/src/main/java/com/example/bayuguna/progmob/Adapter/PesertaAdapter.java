package com.example.bayuguna.progmob.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bayuguna.progmob.Model.Peserta;
import com.example.bayuguna.progmob.R;

import java.util.List;

public class PesertaAdapter extends RecyclerView.Adapter<PesertaAdapter.MyViewHolder> {
    private Context kContext;
    //    private List<Kegiatan> kData;
    private List<Peserta> kData;

    public PesertaAdapter(Context kContext, List<Peserta> kData) {
        this.kContext = kContext;
        this.kData = kData;

    }

    public void setPeserta(List<Peserta> kData) {
        this.kData = kData;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater kInflater = LayoutInflater.from(kContext);
        view = kInflater.inflate(R.layout.peserta_card,viewGroup,false);
        return new PesertaAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.Nama.setText(kData.get(i).getUsers().getName());
        myViewHolder.Nim.setText(kData.get(i).getUsers().getNim());
        myViewHolder.Sie.setText(kData.get(i).getSie());
    }

    @Override
    public int getItemCount() {
        if(kData != null){
            return kData.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Nama,Nim,Sie;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Nama = (TextView) itemView.findViewById(R.id.nama_peserta);
            Nim = (TextView) itemView.findViewById(R.id.nim_peserta);
            Sie = (TextView) itemView.findViewById(R.id.sie_peserta);
        }
    }
}
