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
        List<Student> students=db.getAllRecords();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new myAdapter(this,students));

        Student topstudent=db.getTopStudent();
        // Toast.makeText(MainActivity.this,"The student with the highest average is:"+topstudent.toString(),Toast.LENGTH_LONG);
        tv.setText("The student with the highest average is: ID="+topstudent.getId()+"  Name="+topstudent.getName()+"  Average="+topstudent.getAverageGrade());



    }
}