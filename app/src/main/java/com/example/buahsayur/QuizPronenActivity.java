package com.example.buahsayur;


import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class QuizPronenActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 200;

    ImageView imageView;
    ImageButton btnMic,BackButton;
    TextView txtResult;

    DBHelper dbHelper;
    List<Gabungan> daftarsemua;
    Random random = new Random();
    private int currentIndex;

    private int totalScore = 0;
    private int questionCount = 0;
    private final int MAX_QUESTIONS = 10;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizpronen);

        imageView = findViewById(R.id.gambarGabungan);
        btnMic = findViewById(R.id.micButton);
        txtResult = findViewById(R.id.txtResult);

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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_PERMISSION);
        }
        ActivityResultLauncher<Intent> speechRecognitionLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        ArrayList<String> resultText = result.getData()
                                .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        if (resultText != null && !resultText.isEmpty()) {
                            String userAnswer = resultText.get(0).trim().toLowerCase();
                            cekJawaban(userAnswer);
                        }
                    } else {
                        Toast.makeText(this, "Tidak ada suara terdeteksi.", Toast.LENGTH_SHORT).show();
                    }
                });

        btnMic.setOnClickListener(v -> mulaiDeteksiSuara(speechRecognitionLauncher));
    }

    private void tampilkanSoal() {
        if (questionCount >= MAX_QUESTIONS) {
            tampilkanSkorAkhir();
            return;
        }

        if (daftarsemua.isEmpty()) return;

        currentIndex = random.nextInt(daftarsemua.size());
        Gabungan item = daftarsemua.get(currentIndex);
        imageView.setImageResource(item.getGambar());

        questionCount++;
    }
    
    private void mulaiDeteksiSuara(ActivityResultLauncher<Intent> launcher) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-GB");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Ucapkan nama gambar...");

        try {
            launcher.launch(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    
    private void cekJawaban(String userAnswer) {
        Gabungan item = daftarsemua.get(currentIndex);
        String correctAnswer = item.getNama().toLowerCase();

        if (userAnswer.contains(correctAnswer)) {
            txtResult.setText("✅ Benar!");
            totalScore += 10;
            txtResult.setTextColor(getColor(android.R.color.holo_green_dark));
        } else {
            txtResult.setText("❌ Salah. Jawaban yang benar adalah: " + correctAnswer);
            txtResult.setTextColor(getColor(android.R.color.holo_red_dark));
        }
        imageView.postDelayed(() -> tampilkanSoal(), 1500);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Izin mikrofon diberikan", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Izin mikrofon ditolak", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void tampilkanSkorAkhir() {
        Intent intent = new Intent(QuizPronenActivity.this, ScoreActivity.class);
        intent.putExtra("SKOR_AKHIR", totalScore);
        intent.putExtra("MAKSIMAL", MAX_QUESTIONS * 10);
        startActivity(intent);
        finish();
    }

}
