package com.example.merhav_mugan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {

    private EditText pass, email;
    private Button btn, move_button;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pass = findViewById(R.id.lgn_password);
        email = findViewById(R.id.lgn_email);
        btn = findViewById(R.id.login_button);
        move_button = findViewById(R.id.move_button);
        checkBox = findViewById(R.id.checkBox);
        // בדיקת זכור אותי loged in
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean loggedIn = sharedPreferences.getBoolean("LogedIn", false);
        if (loggedIn) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        // כפתור מעבר ל register
        move_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
// כפתור login
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailInput = email.getText().toString();
                String rawPassword = pass.getText().toString();
                String hashedPassword = Hash.generateHash(rawPassword, "SHA-256");

                DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
                //חיפוש במסד בעץ של ה users לפי המייל שהוזן emailInput
                db.orderByChild("email").equalTo(emailInput).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                User user = userSnapshot.getValue(User.class);

                                if (user != null && user.getPassword().equals(hashedPassword)) {
                                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_LONG).show();

                                    // שמור רק מזהה משתמש
                                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putLong("id", user.getId());
                                    if (checkBox.isChecked()) {
                                        editor.putBoolean("LogedIn", true);
                                    }
                                    editor.apply();

                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    return;
                                }
                            }
                            Toast.makeText(Login.this, "Incorrect password", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Login.this, "User not found", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Login.this, "Firebase error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}