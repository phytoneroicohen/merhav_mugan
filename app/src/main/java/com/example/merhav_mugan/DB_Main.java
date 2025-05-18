package com.example.merhav_mugan;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DB_Main extends AppCompatActivity {

    Button btn_add,buttonshowall;
    EditText  et_latitude,et_longitude,et_Quantity;
    DatabaseReference db;
    Switch s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_main);
        db = FirebaseDatabase.getInstance().getReference();
        et_latitude=findViewById(R.id.etlatitude);
        et_longitude=findViewById(R.id.etlongitude);
        et_Quantity=findViewById(R.id.etQuantity);
        btn_add=findViewById(R.id.btnAdd);
        buttonshowall=findViewById(R.id.buttonshowingall);
        s1=findViewById(R.id.switch1);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        long userId = sharedPreferences.getLong("id", -1);
        if (userId != -1) {
            DatabaseReference userRef = db.child("users").child(String.valueOf(userId));
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        boolean  permited= dataSnapshot.child("permission").getValue(boolean.class);
                        if (permited){
                            btn_add.setEnabled(true);

                        } else {
                            Toast.makeText(DB_Main.this, "Access limited — view only mode", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        Toast.makeText(DB_Main.this, "not", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("Firebase", "Error", databaseError.toException());
                }
            });
        } else {
            Log.d("SharedPreferences", "userId not found in SharedPreferences");
        }
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid=true;
                String longitude=et_longitude.getText().toString();
                if (Double.parseDouble(longitude)>180||Double.parseDouble(longitude)<-180||longitude.isEmpty()) {
                    valid = false;
                    Toast.makeText(getApplicationContext(), "Longitude must be between -180 and 180", Toast.LENGTH_LONG).show();
                }
                String latitude=et_latitude.getText().toString();
                if (Double.parseDouble(latitude)>180||Double.parseDouble(latitude)<-180||latitude.isEmpty()) {
                    valid = false;
                    Toast.makeText(getApplicationContext(), "Latitude must be between -90 and 90", Toast.LENGTH_LONG).show();
                }
                String quantity=et_Quantity.getText().toString();
                if (quantity.isEmpty()) {
                    valid = false;
                    Toast.makeText(getApplicationContext(), "Fill quantity", Toast.LENGTH_LONG).show();
                }
                if (valid) {
                    long id = System.currentTimeMillis();
                    merhav_mugan mugan = new merhav_mugan(id, Double.parseDouble(et_latitude.getText().toString()), Double.parseDouble(et_longitude.getText().toString()), s1.isChecked(), Integer.parseInt(et_Quantity.getText().toString()));
                    db.child("shelters").child(String.valueOf(mugan.id)).setValue(mugan);
                    Toast.makeText(DB_Main.this, "inserted to the database", Toast.LENGTH_SHORT).show();
                }
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
//Menu
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
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_DB) {
            Intent intent=new Intent(this,DB_Main.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_MAP) {
            Intent intent=new Intent(this,Map.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_Login) {
            Intent intent=new Intent(this,Register.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}