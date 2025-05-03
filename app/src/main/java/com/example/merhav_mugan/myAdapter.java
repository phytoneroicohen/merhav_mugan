package com.example.merhav_mugan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myHolder> {

    Context context;
    List<merhav_mugan> mugans;

    public myAdapter(Context context, List<merhav_mugan> mugans) {
        this.context = context;
        this.mugans = mugans;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        holder.idView.setText(String.valueOf(mugans.get(position).getid()));
        holder.latitudeView.setText(String.valueOf((mugans.get(position).getlatitude())));
        holder.longitudeView.setText(String.valueOf((mugans.get(position).getLongitude())));
        holder.accessibleView.setText(String.valueOf(mugans.get(position).isAccessible()));
        holder.quantityView.setText(String.valueOf(mugans.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return mugans.size();
    }
}
