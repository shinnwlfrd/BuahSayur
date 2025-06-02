package com.example.buahsayur;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class PronunciationActivity extends AppCompatActivity {

    ImageView imageView;
    TextToSpeech textToSpeech;
    List<Gabungan> daftarSemua;
    Random random = new Random();
    private int currentIndex;
    ImageButton BackButton, NextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pronuncation);

        BackButton = findViewById(R.id.back);
        NextButton = findViewById(R.id.Next);
        imageView = findViewById(R.id.gambarSayur);
        View audioVisualization = findViewById(R.id.audioVisualization);
        DBHelper dbHelper = new DBHelper(this);
        daftarSemua = dbHelper.getAllBuahSayur();

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        NextButton.setOnClickListener(v -> tampilkanGambar());

        textToSpeech = new TextToSpeech(getApplicationContext(), status -> {
            if (status != TextToSpeech.SUCCESS) {
                Toast.makeText(getApplicationContext(), "TTS Error", Toast.LENGTH_SHORT).show();
            } else {
                textToSpeech.setLanguage(Locale.getDefault());
            }
        });
        findViewById(R.id.playAudioButton).setOnClickListener(v -> bacakanNama(audioVisualization));

    }

    private void tampilkanGambar() {
        if (daftarSemua.isEmpty()) return;

        currentIndex = random.nextInt(daftarSemua.size());
        Gabungan item = daftarSemua.get(currentIndex);
        imageView.setImageResource(item.getGambar());
    }

    private void bacakanNama(View audioVisualization) {
        if (daftarSemua.isEmpty()) return;

        Gabungan item = daftarSemua.get(currentIndex);
        String nama = item.getNama();

        AnimationDrawable animationDrawable = (AnimationDrawable) audioVisualization.getBackground();

        if (textToSpeech.isSpeaking()) {
            textToSpeech.stop();
            audioVisualization.setVisibility(View.GONE);
            if (animationDrawable != null && animationDrawable.isRunning()) {
                animationDrawable.stop();
            }
        } else {
            textToSpeech.speak(nama, TextToSpeech.QUEUE_FLUSH, null, "id1");
            audioVisualization.setVisibility(View.VISIBLE);
            if (animationDrawable != null && !animationDrawable.isRunning()) {
                animationDrawable.stop();
                animationDrawable.start();
            }
        }
    }
    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
