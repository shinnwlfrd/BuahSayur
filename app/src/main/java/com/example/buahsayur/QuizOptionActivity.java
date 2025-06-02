package com.example.buahsayur;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizOptionActivity extends AppCompatActivity {

    ImageButton BackButton, NextButton;
    ImageView imageView, checkIcon;
    Button btnA, btnB, btnC, btnD;

    DBHelper dbHelper;
    List<Gabungan> daftarSemua;
    Random random = new Random();
    private int currentIndex;
    private String correctAnswer;
    private Button selectedButton = null;
    private boolean jawabanSudahDiverifikasi = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quis_option);

        BackButton = findViewById(R.id.back);
        NextButton = findViewById(R.id.Next);
        checkIcon = findViewById(R.id.check);
        imageView = findViewById(R.id.gambarGabungan);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);

        dbHelper = new DBHelper(this);
        daftarSemua = dbHelper.getAllBuahSayur();

        if (!daftarSemua.isEmpty()) {
            tampilkanSoal();
        } else {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }

        View.OnClickListener pilihanListener = v -> {
            if (jawabanSudahDiverifikasi) return;

            resetShadow();
            selectedButton = (Button) v;
            applyShadow(selectedButton, "#AAAAAA");
        };

        btnA.setOnClickListener(pilihanListener);
        btnB.setOnClickListener(pilihanListener);
        btnC.setOnClickListener(pilihanListener);
        btnD.setOnClickListener(pilihanListener);

        NextButton.setOnClickListener(v -> {
            if (!jawabanSudahDiverifikasi) {
                if (selectedButton != null) {
                    verifikasiJawaban();
                } else {
                    Toast.makeText(this, "Pilih dulu jawabannya", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Reset state
                jawabanSudahDiverifikasi = false;
                checkIcon.setBackgroundResource(R.drawable.check);
                tampilkanSoal();
            }
        });

        BackButton.setOnClickListener(v -> finish());
    }

    private void tampilkanSoal() {
        resetShadow();

        currentIndex = random.nextInt(daftarSemua.size());
        Gabungan item = daftarSemua.get(currentIndex);
        imageView.setImageResource(item.getGambar());
        correctAnswer = item.getNama();

        List<String> wrongAnswers = new ArrayList<>();
        while (wrongAnswers.size() < 3) {
            Gabungan wrongItem = daftarSemua.get(random.nextInt(daftarSemua.size()));
            if (!wrongItem.getNama().equals(correctAnswer) && !wrongAnswers.contains(wrongItem.getNama())) {
                wrongAnswers.add(wrongItem.getNama());
            }
        }

        List<String> options = new ArrayList<>(wrongAnswers);
        options.add(correctAnswer);
        Collections.shuffle(options);

        btnA.setText(options.get(0));
        btnB.setText(options.get(1));
        btnC.setText(options.get(2));
        btnD.setText(options.get(3));

        selectedButton = null;
    }

    private void verifikasiJawaban() {
        jawabanSudahDiverifikasi = true;
        checkIcon.setBackgroundResource(R.drawable.arrow_right);

        if (selectedButton.getText().toString().equals(correctAnswer)) {
            applyShadow(selectedButton, "#00FF00");
        } else {
            applyShadow(selectedButton, "#FF0000");
            // Highlight jawaban yang benar
            for (Button btn : new Button[]{btnA, btnB, btnC, btnD}) {
                if (btn.getText().toString().equals(correctAnswer)) {
                    applyShadow(btn, "#00FF00");
                }
            }
        }
    }

    private void resetShadow() {
        for (Button btn : new Button[]{btnA, btnB, btnC, btnD}) {
            btn.setBackgroundTintList(getResources().getColorStateList(R.color.green_button));
            btn.setBackground(null);
        }
    }

    private void applyShadow(Button button, String shadowColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setCornerRadius(20);
        drawable.setStroke(5, Color.parseColor(shadowColor));
        button.setBackground(drawable);
    }
}
