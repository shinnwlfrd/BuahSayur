package com.example.buahsayur;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Random;

public class QuizEssayActivity extends AppCompatActivity {

    ImageButton BackButton, NextButton;
    ImageView imageView, checkIcon;
    EditText EditAnswer;

    DBHelper dbHelper;
    List<Gabungan> daftarsemua;
    Random random = new Random();
    private int currentIndex;
    private boolean jawabanSudahDiverifikasi = false;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_essay);

        imageView = findViewById(R.id.gambarGabungan);
        EditAnswer = findViewById(R.id.editTextAnswer);
        NextButton = findViewById(R.id.Next);
        checkIcon = findViewById(R.id.checkIcon);
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
            if (!jawabanSudahDiverifikasi) {
                String userAnswer = EditAnswer.getText().toString().trim();

                if (TextUtils.isEmpty(userAnswer)) {
                    Toast.makeText(this, "Masukkan jawaban!", Toast.LENGTH_SHORT).show();
                    return;
                }

                verifikasiJawaban();
            } else {
                tampilkanSoal();
                jawabanSudahDiverifikasi = false;
                checkIcon.setBackgroundResource(R.drawable.check);
                EditAnswer.setEnabled(true);
            }
        });

    }

    private void tampilkanSoal() {
        if (daftarsemua.isEmpty()) return;

        currentIndex = random.nextInt(daftarsemua.size());
        Gabungan item = daftarsemua.get(currentIndex);

        imageView.setImageResource(item.getGambar());
        EditAnswer.setText("");
    }

    private void showCustomToast(String message, boolean isCorrect) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, findViewById(android.R.id.content), false);

        TextView text = layout.findViewById(R.id.text);
        ImageView icon = layout.findViewById(R.id.icon);

        text.setText(message);
        if (isCorrect) {
            icon.setImageResource(R.drawable.ic_check);
        } else {
            icon.setImageResource(R.drawable.ic_cross);
        }

        android.widget.Toast toast = new android.widget.Toast(getApplicationContext());
        toast.setDuration(android.widget.Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    private void verifikasiJawaban() {
        jawabanSudahDiverifikasi = true;
        checkIcon.setBackgroundResource(R.drawable.arrow_right);

        String userAnswer = EditAnswer.getText().toString().trim();
        String correctAnswer = daftarsemua.get(currentIndex).getNama();

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            EditAnswer.setBackgroundColor(Color.parseColor("#A5D6A7"));
            showCustomToast("Jawaban Benar!", true);
        } else {
            showCustomToast("Salah! Jawaban: " + correctAnswer, false);
        }

        EditAnswer.setEnabled(false);
    }

}