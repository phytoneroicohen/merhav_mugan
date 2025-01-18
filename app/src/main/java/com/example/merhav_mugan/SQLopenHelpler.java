package com.example.merhav_mugan;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class SQLopenHelpler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="students.db";
    private  static final int DATABASE_VERSION=1;
    private static final  String TABLE_STUDENT="student";
    private static final  String COLUMN_ID="id";
    private static final  String COLUMN_NAME="name";
    private static final  String COLUMN_ADDRESS="address";
    private static final  String COLUMN_AGE="age";
    private static final String COLUMN_GRADES="grades";
    public SQLopenHelpler(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_STUDENT +
                " (" + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_NAME + " TEXT ," + COLUMN_ADDRESS +" TEXT ,"+
                COLUMN_AGE +" INTEGER," + COLUMN_GRADES +" TEXT )";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }
    /// method that add student
    public long addStudent (Student student ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME , student.getName());
        values.put(COLUMN_ADDRESS , student.getAddress());
        values.put(COLUMN_AGE , student.getAge());
        //    values.put(COLUMN_GRADES,convertGradesToString(student.getGrades()));
        values.put(COLUMN_GRADES,student.getGrades());
        long id = db.insert(TABLE_STUDENT , null , values);
        db.close();
        return  id;
    }

    public void deleteStudent(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT,COLUMN_ID+" =?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateStudentGrades(int id, String newGrades)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_GRADES, newGrades);
        db.update(TABLE_STUDENT,values,COLUMN_ID+" =?",new String[]{String.valueOf(id)});
        db.close();

    }

    public void updateStudentGrades2(int id,ArrayList<Integer>newGrades)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_GRADES, convertGradesToString(newGrades));
        db.update(TABLE_STUDENT,values,COLUMN_ID+" =?",new String[]{String.valueOf(id)});
        db.close();

    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> ls=new ArrayList<>();
        String queryStr = "SELECT * FROM " + TABLE_STUDENT;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryStr,null);
        if (cursor.moveToFirst()) {
            do{
                int studentID = cursor.getInt(0);
                String studentName=cursor.getString(1);
                String studentAddrrss=cursor.getString(2);
                int studentAge=cursor.getInt(3);
                String studentgrades=cursor.getString(4);

                Student student=new Student(studentID,studentName,studentAddrrss,studentgrades,studentAge);
                ls.add(student);
            } while (cursor.moveToNext());
        }
        else{}
        cursor.close();
        db.close();
        return ls;

    }

    public ArrayList<String> dbShow2 () {
        ArrayList<String> ls=new ArrayList<>();
        String queryStr = "SELECT * FROM " + TABLE_STUDENT;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryStr,null);
        if (cursor.moveToFirst()) {
            do{
                int studentID = cursor.getInt(0);
                String studentName=cursor.getString(1);
                String studentAddrrss=cursor.getString(2);
                int studentAge=cursor.getInt(3);
                String studentgrades=cursor.getString(4);

                Student student=new Student(studentID,studentName,studentAddrrss,studentgrades,studentAge);
                ls.add(student.getGrades());
            } while (cursor.moveToNext());
        }
        else{}
        cursor.close();
        db.close();
        return ls;

    }



    public Student getTopStudent()
    {
        List<Student> students=getAllStudents();
        double higher_avg=students.get(0).getAverageGrade();
        Student top= students.get(0);

        for (Student student:students) {
            if (student.getAverageGrade() > higher_avg) {
                higher_avg = student.getAverageGrade();
                top=student;

            }
        }
        return top;
    }
    /*    @SuppressLint("Range")
        public ArrayList<Student> getAllStudents2()
        {
            ArrayList<Student> students=new ArrayList<>();
            String selectQuery="SELECT * FROM"+TABLE_STUDENT;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.moveToFirst())
            {
                do {
                  String name= cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                    String adress= cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS))
                    int age= cursor.getInt(cursor.getColumnIndex(COLUMN_AGE))
                            ArrayList<Integer>g=convertStringGrades(cursor.getColumnIndex(COLUMN_GRADES))
                                    int id=cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                                    Student student=new Student(name,adress,age,g);
                                    students.add(student);
                }while(cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return students;
        }
    */
    private String convertGradesToString(ArrayList<Integer>grades)
    {
        return TextUtils.join(",",grades);
    }

    private ArrayList<Integer> convertStringGrades(String gradesStr)
    {
        ArrayList<Integer> grades=new ArrayList<>();
        if(!gradesStr.isEmpty())
        {
            String [] gradeArray=gradesStr.split(",");
            for(String grade:gradeArray)
            {
                grades.add(Integer.parseInt(grade));
            }
        }
        return grades;
    }


}