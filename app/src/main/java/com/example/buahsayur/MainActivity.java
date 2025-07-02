package com.example.buahsayur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button ButtonScanBuah;
    Button ButtonScanSayur;
    Button ButtonPronuciatin;
    Button ButtonQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

//    Button
        ButtonScanBuah = findViewById(R.id.button_scan_buah);
        ButtonScanBuah.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v){
            Intent intent = new Intent(MainActivity.this, BuahActivity.class);
            startActivity(intent);
        }
        });

        ButtonScanSayur = findViewById(R.id.button_scan_sayur);
        ButtonScanSayur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SayurActivity.class);
                startActivity(intent);
            }
        });

        ButtonPronuciatin = findViewById(R.id.button_pronunciation);
        ButtonPronuciatin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, PronunciationActivity.class);
                startActivity(intent);
            }
        });

        ButtonQuiz = findViewById(R.id.button_quiz);
        ButtonQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Quiz2Activity.class);
                startActivity(intent);
            }
        });
    }
}
