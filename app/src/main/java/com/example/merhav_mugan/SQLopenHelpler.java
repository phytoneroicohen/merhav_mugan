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

    private static final String DATABASE_NAME="safeplace.db";
    private  static final int DATABASE_VERSION=1;
    //table mugan
    private static final  String TABLE_MUGAN="mugan";
    private static final  String COLUMN_ID="id";
    private static final  String COLUMN_LATITUDE="latitude";
    private static final  String COLUMN_LONGITUDE="longitude";
    private static final  String COLUMN_ACCESSIBLE="accessible";
    private static final String COLUMN_QUANTITY="quantity";

    //Table users
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_ADDRESS = "address";
    private static final String ACCESSIBILITY = "";



    public SQLopenHelpler(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
// create safe place table
        String createMuganTable = "CREATE TABLE " + TABLE_MUGAN +
                " (" + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_LATITUDE + " REAL ," + COLUMN_LONGITUDE +" REAL ,"+
                COLUMN_ACCESSIBLE +" INTEGER," + COLUMN_QUANTITY +" INTEGER )";
        db.execSQL(createMuganTable);

        // Create Users Table
        String createUserTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_EMAIL + " TEXT," +COLUMN_ADDRESS+"TEXT,"+
                COLUMN_ACCESSIBLE +" INTEGER)";

        db.execSQL(createUserTable);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MUGAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    // add new safeplace record
    public long addRecord (merhav_mugan merhav_mugan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LATITUDE , merhav_mugan.getlatitude());
        values.put(COLUMN_LONGITUDE , merhav_mugan.getLongitude());
        values.put(COLUMN_ACCESSIBLE , merhav_mugan.is_accessible());
        values.put(COLUMN_QUANTITY,merhav_mugan.getQuantity());
        long id = db.insert(TABLE_MUGAN , null , values);
        db.close();
        return  id;
    }

    // Add a new user
    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUN());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put (COLUMN_ADDRESS,user.getAddress());
        values.put(ACCESSIBILITY,user.getNeedAccessibility());

        long id = db.insert(TABLE_USERS, null, values);
        db.close();
        return id;
    }
    public void deleteRecord(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MUGAN,COLUMN_ID+" =?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteUserRecord(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS,COLUMN_ID+" =?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateRecord(int id, String newGrades)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUANTITY, newGrades);
        db.update(TABLE_MUGAN,values,COLUMN_ID+" =?",new String[]{String.valueOf(id)});
        db.close();

    }


    public ArrayList<merhav_mugan> getAllRecords() {
        ArrayList<merhav_mugan> ls=new ArrayList<>();
        String queryStr = "SELECT * FROM " + TABLE_MUGAN;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryStr,null);
        if (cursor.moveToFirst()) {
            do{
                int ID = cursor.getInt(0);
                Double latitude=cursor.getDouble(1);
                Double longitute=cursor.getDouble(2);
                int accessible=cursor.getInt(3);
                int quantity=cursor.getInt(4);

                merhav_mugan mugan=new merhav_mugan(ID,latitude,longitute,accessible,quantity);
                ls.add(mugan);
            } while (cursor.moveToNext());
        }
        else{}
        cursor.close();
        db.close();
        return ls;
    }






}