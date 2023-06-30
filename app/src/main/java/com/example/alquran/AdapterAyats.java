package com.example.alquran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquran.model.AyatModel.VersesItem;
import com.example.alquran.model.Terjemahan.TranslationsItem;

import java.util.List;

public class AdapterAyats  extends RecyclerView.Adapter<AdapterAyats.ViewHolder> {
    private List<VersesItem> list1;

    private static List<TranslationsItem> list2;

    public AdapterAyats(List<VersesItem> list1, List<TranslationsItem> list2){
        this.list1 = list1;
        this.list2 = list2;
    }
    @NonNull
    @Override
    public AdapterAyats.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat, parent, false)
        );
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterAyats.ViewHolder holder, int position) {
        VersesItem list = list1.get(position);
        TranslationsItem list_ = list2.get(position);

        holder.textViewAyat.setText(list.getTextUthmani());
        holder.textViewTerjemahanAyat.setText(list_.getText());
        holder.textViewNomorAyat.setText(String.valueOf(position + 1));
    }
    @Override
    public int getItemCount() {
        return list2.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAyat, textViewNomorAyat, textViewTerjemahanAyat;

        public ViewHolder(View itemView){
            super(itemView);

            textViewAyat = itemView.findViewById(R.id.Ayat);
            textViewNomorAyat = itemView.findViewById(R.id.NomorAyat);
            textViewTerjemahanAyat = itemView.findViewById(R.id.TerjemahanAyat);
        }
    }
    public void setData(List<VersesItem> data, List<TranslationsItem> data1) {
        list1.clear();
        list1.addAll(data);
        list2.clear();
        list2.addAll(data1);
        notifyDataSetChanged();
    }


}

