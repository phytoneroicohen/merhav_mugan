package com.example.merhav_mugan;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class DB_Main extends AppCompatActivity {

    Button btn_add, btn_del, btn_updt,buttonshowall;
    EditText  et_latitude,et_longitude,et_accessible,et_Quantity, et_deleteID,etIDUpdt,etGradesUpdt;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_main);
        db = FirebaseDatabase.getInstance().getReference();
        et_latitude=findViewById(R.id.etlatitude);
        et_longitude=findViewById(R.id.etlongitude);
        et_accessible=findViewById(R.id.etaccessible);
        et_Quantity=findViewById(R.id.etQuantity);
        et_deleteID=findViewById(R.id.edDeleteID);
        etIDUpdt=findViewById(R.id.edStdntUpdate);
        etGradesUpdt=findViewById(R.id.etGradesUpdate);
        btn_add=findViewById(R.id.btnAdd);
        btn_del=findViewById(R.id.btnDeleteID);
        btn_updt=findViewById(R.id.btnUpdate);
        buttonshowall=findViewById(R.id.buttonshowingall);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = System.currentTimeMillis();


                merhav_mugan mugan=new merhav_mugan (id,Double.parseDouble(et_latitude.getText().toString()),Double.parseDouble(et_longitude.getText().toString()),Integer.parseInt(et_accessible.getText().toString()),Integer.parseInt(et_Quantity.getText().toString()));
                db.child("shelters").child(String.valueOf(mugan.id)).setValue(mugan);

                // SQLopenHelpler dbHelpler =new SQLopenHelpler(DB_Main.this);
               // long success=dbHelpler.addRecord(mugan);
                Toast.makeText(DB_Main.this,"inserted to the database",Toast.LENGTH_SHORT).show();
            }
        });
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLopenHelpler db=new SQLopenHelpler(DB_Main.this);
                db.deleteRecord(Integer.parseInt(et_deleteID.getText().toString()));
                Toast.makeText(DB_Main.this,"deleted from the database",Toast.LENGTH_SHORT).show();

            }
        });
        btn_updt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLopenHelpler db=new SQLopenHelpler(DB_Main.this);
                db.updateRecord(Integer.parseInt(etIDUpdt.getText().toString()),etGradesUpdt.getText().toString());
            }
        });
        buttonshowall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(DB_Main.this, DB_View.class);
                startActivity(intent);

            }
        });
    }

    public ArrayList<merhav_mugan> get_records(){
        ArrayList<merhav_mugan> ls=new ArrayList<>();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("shelters");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder sb = new StringBuilder();

                for (DataSnapshot snap : snapshot.getChildren()) {
                    merhav_mugan shelter = snap.getValue(merhav_mugan.class);
                    if (shelter != null) {
                        ls.add(shelter);
                        sb.append("ID: ").append(shelter.id)
                                .append(", Lat: ").append(shelter.latitude)
                                .append(", Lng: ").append(shelter.longitude)
                                .append(", Cap: ").append(shelter.quantity)
                                .append(", Acc: ").append(shelter.is_accessible)
                                .append("\n");

                    }
                }

                Toast.makeText(DB_Main.this, sb.toString(), Toast.LENGTH_LONG).show();}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(DB_Main.this, "Error loading data", Toast.LENGTH_SHORT).show();
            }
        });



        return ls ;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.common_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_home) {
            Intent intent=new Intent(this,MainActivity.class);
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_DB) {
            Toast.makeText(this, "DB clicked", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,DB_Main.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_MAP) {
            Toast.makeText(this, "MAP clicked", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,Map.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_Login) {
            Toast.makeText(this, "Login clicked", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,Login.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}