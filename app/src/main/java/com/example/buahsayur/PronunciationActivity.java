package com.example.buahsayur;

import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chibde.visualizer.BarVisualizer;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class PronunciationActivity extends AppCompatActivity {

    ImageView imageView;
    TextToSpeech textToSpeech;
    private DBHelper dbHelper;
    private List<Gabungan> daftarSemua;
    Random random = new Random();
    private int currentIndex;
    ImageButton BackButton, NextButton;
    private MediaPlayer mediaPlayer;
    private Visualizer visualizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pronuncation);

        BackButton = findViewById(R.id.back);
        NextButton = findViewById(R.id.Next);
        imageView = findViewById(R.id.gambarSayur);
        View audioVisualization = findViewById(R.id.audioVisualization);
        dbHelper = new DBHelper(this);
        daftarSemua = dbHelper.getAllBuahSayur();

        BackButton.setOnClickListener(v -> onBackPressed());
        NextButton.setOnClickListener(v -> tampilkanGambar());

        textToSpeech = new TextToSpeech(getApplicationContext(), status -> {
            if (status != TextToSpeech.SUCCESS) {
                Toast.makeText(getApplicationContext(), "TTS Error", Toast.LENGTH_SHORT).show();
            } else {
                textToSpeech.setLanguage(Locale.getDefault());
            }
        });
        findViewById(R.id.playAudioButton).setOnClickListener(v -> bacakanNama());

    }

    private void tampilkanGambar() {
        if (daftarSemua.isEmpty()) return;
        currentIndex = random.nextInt(daftarSemua.size());
        Gabungan item = daftarSemua.get(currentIndex);
        imageView.setImageResource(item.getGambar());
    }

    private void bacakanNama() {
        if (daftarSemua == null || daftarSemua.isEmpty()) {
            Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        Gabungan item = daftarSemua.get(currentIndex);
        int audioResId = item.getAudio();
        BarVisualizer visualizerView = findViewById(R.id.audioVisualization);

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

        mediaPlayer = MediaPlayer.create(this, audioResId);
        mediaPlayer.setOnPreparedListener(mp -> {
            setupVisualizer(mp.getAudioSessionId(), visualizerView);
            visualizerView.setVisibility(View.VISIBLE);
            mediaPlayer.start();
        });

        mediaPlayer.setOnCompletionListener(mp -> {
            visualizerView.setVisibility(View.GONE);
            if (visualizer != null) {
                visualizer.release();
            }
        });
    }


    private void setupVisualizer(int audioSessionId, BarVisualizer visualizerView) {
        if (visualizer != null) {
            visualizer.release();
        }

        visualizer = new Visualizer(audioSessionId);
        visualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        visualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener() {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform, int samplingRate) {
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] fft, int samplingRate) {}
        }, Visualizer.getMaxCaptureRate() / 2, true, false);
        visualizer.setEnabled(true);
    }


    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

        if (findViewById(R.id.audioVisualization) != null) {
            ((BarVisualizer) findViewById(R.id.audioVisualization)).release();
        }

        super.onDestroy();
    }
}
