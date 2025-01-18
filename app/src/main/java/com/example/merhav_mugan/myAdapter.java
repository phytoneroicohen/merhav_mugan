package com.example.merhav_mugan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myHolder> {

    Context context;
    List<Student> students;

    public myAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        holder.idView.setText(String.valueOf(students.get(position).getId()));
        holder.nameView.setText(String.valueOf((students.get(position).getName())));
        holder.adsressView.setText(String.valueOf((students.get(position).getAddress())));
        holder.ageView.setText(String.valueOf(students.get(position).getAge()));
        holder.gradesView.setText(String.valueOf(students.get(position).getGrades()));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
