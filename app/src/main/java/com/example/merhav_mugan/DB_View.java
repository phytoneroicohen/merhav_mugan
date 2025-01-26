package com.example.merhav_mugan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DB_View extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_view);
        tv=findViewById(R.id.textView);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);

        SQLopenHelpler  db=new SQLopenHelpler(this) ;
        List<merhav_mugan> mugans=db.getAllRecords();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new myAdapter(this,mugans));

        tv.setText("List of records:");



    }
}