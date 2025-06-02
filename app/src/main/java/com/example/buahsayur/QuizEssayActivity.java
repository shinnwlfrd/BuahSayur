package com.example.buahsayur;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Random;

public class QuizEssayActivity extends AppCompatActivity {

    ImageButton BackButton, NextButton;
    ImageView imageView;
    EditText EditAnswer;

    DBHelper dbHelper;
    List<Gabungan> daftarsemua;
    Random random = new Random();
    private int currentIndex;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_essay);

        imageView = findViewById(R.id.gambarGabungan);
        EditAnswer = findViewById(R.id.editTextAnswer);
        NextButton = findViewById(R.id.Next);

        BackButton = findViewById(R.id.back);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        dbHelper = new DBHelper(this);
        daftarsemua = dbHelper.getAllBuahSayur();

        if (!daftarsemua.isEmpty()) {
            tampilkanSoal();
        } else {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }

        NextButton.setOnClickListener(v -> {
            String userAnswer = EditAnswer.getText().toString().trim();

            if (TextUtils.isEmpty(userAnswer)) {
                Toast.makeText(this, "Masukkan jawaban!", Toast.LENGTH_SHORT).show();
                return;
            }

            Gabungan item = daftarsemua.get(currentIndex);
            String correctAnswer = item.getNama();

            if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                Toast.makeText(this, "✅ Benar!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "❌ Salah. Jawaban yang benar adalah: " + correctAnswer, Toast.LENGTH_LONG).show();
            }

            tampilkanSoal();
        });
    }

    private void tampilkanSoal() {
        if (daftarsemua.isEmpty()) return;

        currentIndex = random.nextInt(daftarsemua.size());
        Gabungan item = daftarsemua.get(currentIndex);

        imageView.setImageResource(item.getGambar());
        EditAnswer.setText("");
    }

}
