package com.example.buahsayur;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Random;

public class SayurActivity extends AppCompatActivity {

    ImageButton BackButton;
    ImageButton NextButton;
    ImageView GambarSayur;
    TextView NamaSayur;

    private DBHelper dbHelper;
    private List<Sayur> daftarSayur;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sayur);

        GambarSayur = findViewById(R.id.gambarSayur);
        NamaSayur = findViewById(R.id.namaSayur);
        BackButton = findViewById(R.id.back);
        NextButton = findViewById(R.id.Next);
        dbHelper = new DBHelper(this);
        daftarSayur = dbHelper.getAllSayur();
        random = new Random();

        if (!daftarSayur.isEmpty()) {
            tampilkanData();
        } else {
            NamaSayur.setText("Tidak ada data");
        }

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilkanData();
            }
        });

    }
    private void tampilkanData() {
        int randomIndex = random.nextInt(daftarSayur.size());
        Sayur sayur = daftarSayur.get(randomIndex);
        NamaSayur.setText(sayur.getNama());
        GambarSayur.setImageResource(sayur.getGambar());


    }
}
