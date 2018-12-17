package com.example.bayuguna.progmob.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bayuguna.progmob.Model.RiwayatKepanitiaanResponse;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.MyVieHolder> {


    private Context rContext;
//    private List<Riwayat> rData;
    private List<RiwayatKepanitiaanResponse> rData;

    public RiwayatAdapter(Context rContext, List<RiwayatKepanitiaanResponse> rData) {
        this.rContext = rContext;
        this.rData = rData;
    }

    public void setRiwayat(List<RiwayatKepanitiaanResponse> rData) {
        this.rData = rData;
        notifyDataSetChanged();
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

        myVieHolder.Title.setText(rData.get(i).getNama());
        myVieHolder.Sie.setText(rData.get(i).getSie());
        myVieHolder.Tanggal.setText(rData.get(i).getTanggal());

        myVieHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(rData.get(i).getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        if(rData != null){
            return rData.size();
        }
        return 0;
    }

    public class MyVieHolder extends RecyclerView.ViewHolder {

        TextView Title;
        TextView Sie;
        TextView Tanggal;
        CardView cardView;


        public MyVieHolder(View itemView) {
            super(itemView);

            Title = (TextView) itemView.findViewById(R.id.riwayat_title);
            Sie = (TextView) itemView.findViewById(R.id.riwayat_sie);
            Tanggal = (TextView) itemView.findViewById(R.id.riwayat_tanggal);
            cardView = (CardView) itemView.findViewById(R.id.card_recyclerview);

        }
    }

    public void delete(final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(rContext);
        builder.setTitle("Are You Sure");
        builder.setMessage("This Kepanitiaan will be deleted");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<RiwayatKepanitiaanResponse> call = RetrofitBuilder.creatService(ApiService.class).deleteKepanitiaan(id);

                call.enqueue(new Callback<RiwayatKepanitiaanResponse>() {
                    @Override
                    public void onResponse(Call<RiwayatKepanitiaanResponse> call, Response<RiwayatKepanitiaanResponse> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(rContext, "Berhasil",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(rContext, "Data Tidak Bisa di Hapus",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<RiwayatKepanitiaanResponse> call, Throwable t) {
                        Toast.makeText(rContext, "Gagal",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog ad = builder.create();
        ad.show();
    }
}
