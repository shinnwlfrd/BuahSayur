package com.example.buahsayur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    TextView textScoreValue;
    Button btnKembali;
    ImageView iconScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        textScoreValue = findViewById(R.id.textScoreValue);
        btnKembali = findViewById(R.id.btnKembali);
        iconScore = findViewById(R.id.iconScore);

        int score = getIntent().getIntExtra("SKOR_AKHIR", 0);
        int maxScore = getIntent().getIntExtra("MAKSIMAL", 100);

        textScoreValue.setText(score + " / " + maxScore);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, Quiz2Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
