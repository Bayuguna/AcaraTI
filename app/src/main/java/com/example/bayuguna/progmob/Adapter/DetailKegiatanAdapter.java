package com.example.bayuguna.progmob.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bayuguna.progmob.Model.DetKegiatan;
import com.example.bayuguna.progmob.R;

import java.util.List;

public class DetailKegiatanAdapter extends RecyclerView.Adapter<DetailKegiatanAdapter.MyVieHolder> {
    private Context kContext;
    private List<DetKegiatan> kData;

    public DetailKegiatanAdapter(Context kContext, List<DetKegiatan> kData) {
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
        return new DetailKegiatanAdapter.MyVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailKegiatanAdapter.MyVieHolder myVieHolder, final int i) {

        myVieHolder.Sie.setText(kData.get(i).getSie());
        myVieHolder.Koor.setText(kData.get(i).getKoor());
        myVieHolder.Line.setText(kData.get(i).getLine());

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

}
