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

import com.example.bayuguna.progmob.Model.DetKegiatan;
import com.example.bayuguna.progmob.Model.DetKegiatanItem;
import com.example.bayuguna.progmob.R;
import com.example.bayuguna.progmob.network.ApiService;
import com.example.bayuguna.progmob.network.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDetailKegiatanAdapter extends RecyclerView.Adapter<AdminDetailKegiatanAdapter.MyVieHolder> {
    private Context kContext;
    private List<DetKegiatan> kData;

    public AdminDetailKegiatanAdapter(Context kContext, List<DetKegiatan> kData) {
        this.kContext = kContext;
        this.kData = kData;
    }

    public void setSie(List<DetKegiatan> kData) {
        this.kData = kData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyVieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater kInflater = LayoutInflater.from(kContext);
        view = kInflater.inflate(R.layout.sie_card,viewGroup,false);
        return new AdminDetailKegiatanAdapter.MyVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminDetailKegiatanAdapter.MyVieHolder myVieHolder, final int i) {

        myVieHolder.Sie.setText(kData.get(i).getSie());
        myVieHolder.Koor.setText(kData.get(i).getKoor());
        myVieHolder.Line.setText(kData.get(i).getLine());

        myVieHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(kData.get(i).getId());
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
        TextView Sie,Line,Koor;
        CardView cardView;


        public MyVieHolder(@NonNull View itemView) {
            super(itemView);

            Sie = itemView.findViewById(R.id.sie_kegiatan);
            Koor = itemView.findViewById(R.id.koor_kegiatan);
            Line = itemView.findViewById(R.id.idLine_kegiatan);
            cardView = (CardView) itemView.findViewById(R.id.card_recyclerview);
        }
    }

    public void delete(final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(kContext);
        builder.setTitle("Are You Sure");
        builder.setMessage("This Kegiatan will be deleted");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<DetKegiatanItem> call = RetrofitBuilder.creatService(ApiService.class).deleteDetKegiatan(id);

                call.enqueue(new Callback<DetKegiatanItem>() {
                    @Override
                    public void onResponse(Call<DetKegiatanItem> call, Response<DetKegiatanItem> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(kContext, "Berhasil",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(kContext, "Data Tidak Bisa di Hapus",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<DetKegiatanItem> call, Throwable t) {
                        Toast.makeText(kContext, "Gagal",Toast.LENGTH_LONG).show();
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
