package com.example.merhav_mugan;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
//  import android.util.Patterns;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText UserName, Password, Email, adrres;
    private Button btn;
    private Switch sn;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = FirebaseDatabase.getInstance().getReference();


        UserName = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        Email = (EditText) findViewById(R.id.email);
        sn = findViewById(R.id.switch2);
        adrres = findViewById(R.id.address);
        btn = (Button) findViewById(R.id.register_button);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //    במידה ורוצים למחוק את התוכן של sharedPreferences
        //editor.clear();
        //editor.apply();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                long current_id = sharedPreferences.getLong("id", -1);

                String userName = UserName.getText().toString();
                String rawPass = Password.getText().toString();
                String Pass = Hash.generateHash(rawPass, "SHA-256");
                String email = Email.getText().toString();
                long id = System.currentTimeMillis();
                boolean permission = false;
                Boolean valid = true;
                //ולידציות:

                if (userName.isEmpty()) {
                    valid = false;
                    Toast.makeText(getApplicationContext(), "Fill UserName", Toast.LENGTH_LONG).show();
                }
                if (rawPass.length() < 8) {
                    valid = false;
                    Toast.makeText(getApplicationContext(), "8 chars for password ", Toast.LENGTH_LONG).show();
                }
                if (!(isValidEmail(email))) {
                    valid = false;
                    Toast.makeText(getApplicationContext(), "Fill Email or your email is illegal", Toast.LENGTH_LONG).show();
                }

                if (valid) {
                    if (current_id != -1) {
                        db.child("users").child(String.valueOf(current_id))
                                .removeValue();
                    }
                    editor.putLong("id", id);
                    editor.apply();

                    User user = new User(id, userName, Pass, email, adrres.getText().toString(), sn.isChecked(), permission);
                    db.child("users").child(String.valueOf(user.id)).setValue(user);

                    Toast.makeText(Register.this, "inserted to the database", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);

                }
            }

        });
    }


    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

}
