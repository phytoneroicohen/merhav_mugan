package com.example.merhav_mugan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

     //   SQLopenHelpler  db=new SQLopenHelpler(this) ;
       // List<merhav_mugan> mugans=db.getAllRecords();
      //   List<merhav_mugan> mugans=DB_Main.;

        //  DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("shelters");
    //    List<merhav_mugan> mugans = new ArrayList<>();


  //      recyclerView.setLayoutManager(new LinearLayoutManager(this));
  //      recyclerView.setAdapter(new myAdapter(this,mugans));

    //    tv.setText("List of records:");

     /*   DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("shelters");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder sb = new StringBuilder();

                for (DataSnapshot snap : snapshot.getChildren()) {
                    merhav_mugan shelter = snap.getValue(merhav_mugan.class);
                    if (shelter != null) {
                        sb.append("ID: ").append(shelter.id)
                                .append(", Lat: ").append(shelter.latitude)
                                .append(", Lng: ").append(shelter.longitude)
                                .append(", Cap: ").append(shelter.quantity)
                                .append(", Acc: ").append(shelter.is_accessible)
                                .append("\n");
                    }
                }

                Toast.makeText(DB_View.this, sb.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(DB_View.this, "Error loading data", Toast.LENGTH_SHORT).show();
            }
        });
*/
    }


}