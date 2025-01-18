package com.example.merhav_mugan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(() -> {
            try {
                Thread.sleep(8000);
                startActivity(new Intent(this, MainActivity.class));
                finish(); // סיים את הפעילות של מסך הספלש
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    }


