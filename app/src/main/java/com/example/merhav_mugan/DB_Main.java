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
    private SQLopenHelpler dbHelper;
    private ArrayList<Student> studentList;
    EditText et_name, et_age, et_lentitude,et_grades, et_deleteID,etIDUpdt,etGradesUpdt;
    ListView lv;
    TextView tv;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_main);



        /*   btn_showexellent=findViewById(R.id.btnAdd);
        btn_edit=findViewById(R.id.btnedit);
       */
        et_name=findViewById(R.id.etName);
        et_age=findViewById(R.id.etAge);
        et_lentitude=findViewById(R.id.lentitude);
        et_grades=findViewById(R.id.etGrades);
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

                Student student=new Student(-1,et_name.getText().toString(),et_lentitude.getText().toString(),et_grades.getText().toString(),Integer.parseInt(et_age.getText().toString()));
                SQLopenHelpler dbHelpler =new SQLopenHelpler(DB_Main.this);
                long success=dbHelpler.addRecord(student);
                Toast.makeText(DB_Main.this,"inserted to the database",Toast.LENGTH_SHORT).show();
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLopenHelpler db=new SQLopenHelpler(DB_Main.this);
                db.deleteRecord(Integer.parseInt(et_deleteID.getText().toString()));
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

                //     ArrayAdapter studntadptr = new ArrayAdapter<Student>(DB_Main.this, android.R.layout.simple_list_item_1,students);
                //      lv.setAdapter(studntadptr);
                //   recyclerView.setLayoutManager(new LinearLayoutManager(DB_Main.this));
                //     recyclerView.setAdapter(new myAdapter(getApplicationContext(),students));
                //   Toast.makeText(DB_Main.this,students.toString(),Toast.LENGTH_SHORT).show();


            }
        });





    }



    /*   private void showAddStudentDialog()
    {
        AlertDialog.Builder build=new AlertDialog.Builder(this);
        build.setTitle("add new student");

        bulid.setPostiveButton("add",(dialog,which))->
        String name=et_name.getText().toString();
        String gradeStr=et_grades.getText().toString();
        String adress=et_address.getText.toString();
        string ageStr=et_age.getText.toString();
        if(!name.isEmpty() && !gradeStr.isEmpty() && !adress.isEmpty() && !ageStr.isEmpty())
        {
            ArrayList<Integer> grades=new ArrayList<>();

                String [] gradeArray=gradesStr.split(",");
                for(String grade:gradeArray)
                {
                    grades.add(Integer.parseInt(grade));
                }

            Student student=new Student(name,Integer.parseInt(ageStr),adress,grades);
                dbHelper.addStudent(student);
        }
        loadStudents()

    }

    private void showEditStudentDialog(Student student)
    {
        AlertDialog.Builder build=new AlertDialog.Builder(this);
        build.setTitle("edit student");

        bulid.setPostiveButton("edit",(dialog,which))->
        String name=et_name.getText().toString();
        String gradeStr=et_grades.getText().toString();
        String adress=et_address.getText.toString();
        string ageStr=et_age.getText.toString();
        if(!name.isEmpty() && !gradeStr.isEmpty() && !adress.isEmpty() && !ageStr.isEmpty())
        {
            ArrayList<Integer> grades=new ArrayList<>();

            String [] gradeArray=gradesStr.split(",");
            for(String grade:gradeArray)
            {
                grades.add(Integer.parseInt(grade));
            }

            student.setName(name);
            student.setGrades(grades);
            student.setAddress(adress);
            student.setAge(Integer.parseInt(ageStr));
            dbHelper.updateStudent(student);
            loadStudents()
        }



        }

    private void showTopStudent()
    {
        Student topStudent=SQLopenHelpler.getTopStudent();
        if(topStudent!=null)
        {
            String message=String.format(
                    "exexlent student\n"+
                            "name "+ topStudent.getName()+"\n"+
                            "average"+topStudent.getAverageGrade()+"\n"+
                            "age"+topStudent.getAge()+"\n"+
                            "adress"+topStudent.getAddress()+"\n"
            );
            new AlertDialog.Builder(this).setTitle("EXECLENT").setMessage(message).setPositiveButton("close",null).show();
        }
    }
*/

}