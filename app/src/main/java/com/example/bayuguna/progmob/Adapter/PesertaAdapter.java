package com.example.bayuguna.progmob.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bayuguna.progmob.Model.Kepanitiaan;
import com.example.bayuguna.progmob.Model.Peserta;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesertaAdapter extends RecyclerView.Adapter<PesertaAdapter.MyViewHolder> {
    private Context kContext;
    //    private List<Kegiatan> kData;
    private List<Kepanitiaan> kData;

    public PesertaAdapter(Context kContext, List<Kepanitiaan> kData) {
        this.kContext = kContext;
        this.kData = kData;

    }

    public void setPeserta(List<Kepanitiaan> kData) {
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
        myViewHolder.Nama.setText(kData.get(i).getName());
        myViewHolder.Nim.setText(kData.get(i).getNim());
        myViewHolder.Sie.setText(kData.get(i).getSie());
        Log.d("Peserta Adapter", "onBindViewHolder: "+ kData.get(i).getId());
        myViewHolder.Status.setText(kData.get(i).getStatus());

        myViewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                aktivasi(kData.get(i).getId());
                return false;
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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Nama,Nim,Sie,Status;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Nama = (TextView) itemView.findViewById(R.id.nama_peserta);
            Nim = (TextView) itemView.findViewById(R.id.nim_peserta);
            Sie = (TextView) itemView.findViewById(R.id.sie_peserta);
            Status = (TextView) itemView.findViewById(R.id.status_peserta);
            cardView = itemView.findViewById(R.id.card_recyclerview);
        }
    }

    public void aktivasi(final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(kContext);
        builder.setTitle("Are You Sure");
        builder.setMessage("This Kegiatan will be deleted");
        builder.setPositiveButton("Aktif", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<Peserta> call = RetrofitBuilder.creatService(ApiService.class).aktivasiPeserta(id);

                call.enqueue(new Callback<Peserta>() {
                    @Override
                    public void onResponse(Call<Peserta> call, Response<Peserta> response) {
                        Toast.makeText(kContext,"Berhasil di Aktifkan",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Peserta> call, Throwable t) {
                        Toast.makeText(kContext,"Lost Connection",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        builder.setNegativeButton("Non Aktif", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Call<Peserta> call = RetrofitBuilder.creatService(ApiService.class).NonAktivasiPeserta(id);

                call.enqueue(new Callback<Peserta>() {
                    @Override
                    public void onResponse(Call<Peserta> call, Response<Peserta> response) {
                        Toast.makeText(kContext,"Berhasil di Non Aktifkan",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Peserta> call, Throwable t) {
                        Toast.makeText(kContext,"Lost Connection",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        AlertDialog ad = builder.create();
        ad.show();
    }
}
