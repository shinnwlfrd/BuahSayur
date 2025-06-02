package com.example.buahsayur;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Quiz2Activity extends AppCompatActivity {
    ImageButton BackButton;
    Button ButtonPronen;
    Button ButtonEssay;
    Button ButtonOption;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EdgeToEdge.enable(this);

        setContentView(R.layout.quiz2);
        BackButton = findViewById(R.id.back);
        BackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();

                }
            });

        ButtonPronen = findViewById(R.id.buttonPronen);
        ButtonPronen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Quiz2Activity.this, QuizPronenActivity.class);
                startActivity(intent);
            }
        });

        ButtonEssay = findViewById(R.id.button_essay);
        ButtonEssay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Quiz2Activity.this, QuizEssayActivity.class);
                startActivity(intent);
            }
        });

        ButtonOption = findViewById(R.id.Button_option);
        ButtonOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Quiz2Activity.this, QuizOptionActivity.class);
                startActivity(intent);
            }
        });
    }
}
