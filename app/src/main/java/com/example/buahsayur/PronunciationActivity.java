package com.example.buahsayur;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class PronunciationActivity extends AppCompatActivity {

    ImageView imageView;
    TextToSpeech textToSpeech;
    WebView gifView;
    private DBHelper dbHelper;
    private List<Gabungan> daftarSemua;
    Random random = new Random();
    private int currentIndex;
    ImageButton BackButton, NextButton, playButton;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pronuncation);

        BackButton = findViewById(R.id.back);
        NextButton = findViewById(R.id.Next);
        imageView = findViewById(R.id.gambarSayur);
        playButton = findViewById(R.id.playButton);
        dbHelper = new DBHelper(this);
        daftarSemua = dbHelper.getAllBuahSayur();
        gifView = findViewById(R.id.audioGif);
        gifView.getSettings().setLoadWithOverviewMode(true);
        gifView.getSettings().setUseWideViewPort(true);
        gifView.setBackgroundColor(0x00000000);
        gifView.setBackgroundDrawable(null);
        gifView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);

        BackButton.setOnClickListener(v -> onBackPressed());
        NextButton.setOnClickListener(v -> tampilkanGambar());

        textToSpeech = new TextToSpeech(getApplicationContext(), status -> {
            if (status != TextToSpeech.SUCCESS) {
                Toast.makeText(getApplicationContext(), "TTS Error", Toast.LENGTH_SHORT).show();
            } else {
                textToSpeech.setLanguage(Locale.getDefault());
            }
        });
        playButton.setOnClickListener(v -> {
            if (daftarSemua == null || daftarSemua.isEmpty()) {
                Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            Gabungan item = daftarSemua.get(currentIndex);
            int audioResId = item.getAudio();

            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }

            gifView.loadUrl("file:///android_asset/sound.gif");
            gifView.setVisibility(View.VISIBLE);

            mediaPlayer = MediaPlayer.create(this, audioResId);
            if (mediaPlayer == null) {
                Toast.makeText(this, "Gagal memutar audio", Toast.LENGTH_SHORT).show();
                return;
            }

            mediaPlayer.setOnPreparedListener(mp -> {
                mediaPlayer.start();
                isPlaying = true;
                playButton.setImageResource(R.drawable.pause); // Ganti ikon ke pause
            });

            mediaPlayer.setOnCompletionListener(mp -> {
                gifView.setVisibility(View.GONE); // Sembunyikan animasi
                playButton.setImageResource(R.drawable.ic_play); // Kembali ke play
                isPlaying = false;

                mediaPlayer.release();
                mediaPlayer = null;
            });
        });
    }

    private void tampilkanGambar() {
        if (daftarSemua.isEmpty()) return;
        currentIndex = random.nextInt(daftarSemua.size());
        Gabungan item = daftarSemua.get(currentIndex);
        imageView.setImageResource(item.getGambar());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
