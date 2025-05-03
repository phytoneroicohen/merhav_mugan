package com.example.merhav_mugan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DB_View extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_view);
        tv=findViewById(R.id.textView);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);

        firebaseHandler handler = new firebaseHandler();
        handler.getAllShelters(new firebaseHandler.ShelterListCallback() {
            @Override
            public void onResult(List<merhav_mugan> shelters) {
                // This runs only when data is loaded!
                recyclerView.setLayoutManager(new LinearLayoutManager(DB_View.this));
                recyclerView.setAdapter(new myAdapter(DB_View.this, shelters));
            }

            @Override
            public void onError(String error) {
                Toast.makeText(DB_View.this, "Failed: " + error, Toast.LENGTH_SHORT).show();
            }
        });

    }


}