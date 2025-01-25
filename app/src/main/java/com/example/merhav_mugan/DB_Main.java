package com.example.merhav_mugan;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DB_Main extends AppCompatActivity {

    Button btn_add, btn_del, btn_updt,buttonshowall;
    EditText  et_latitude,et_longitude,et_accessible,et_Quantity, et_deleteID,etIDUpdt,etGradesUpdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_main);

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

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                merhav_mugan mugan=new merhav_mugan (-1,Double.parseDouble(et_latitude.getText().toString()),Double.parseDouble(et_longitude.getText().toString()),Integer.parseInt(et_accessible.getText().toString()),Integer.parseInt(et_Quantity.getText().toString()));
                SQLopenHelpler dbHelpler =new SQLopenHelpler(DB_Main.this);
                long success=dbHelpler.addRecord(mugan);
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

}