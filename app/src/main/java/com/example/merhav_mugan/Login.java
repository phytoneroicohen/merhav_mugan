package com.example.merhav_mugan;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    private EditText pass, email;
    private Button btn, move_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pass = findViewById(R.id.lgn_password);
        email = findViewById(R.id.lgn_email);
        btn = findViewById(R.id.login_button);
        move_button = findViewById(R.id.move_button);

        move_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

                String SavedEmail = sharedPreferences.getString("email", "");
                String SavedPassword = sharedPreferences.getString("Pass", "");

                String UserEmail = email.getText().toString();
                String UserPassword = pass.getText().toString();


                if (!SavedEmail.equals(UserEmail) || !SavedPassword.equals(UserPassword)) {
                    Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Login successfully - Welcome", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}