package com.example.buahsayur;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.Random;

public class BuahActivity extends AppCompatActivity {

    ImageButton BackButton;
    ImageButton NextButton;
    ImageView GambarBuah;
    TextView NamaBuah;

    private DBHelper dbHelper;
    private List<Buah> daftarBuah;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buah);

        GambarBuah = findViewById(R.id.gambarBuah);
        NamaBuah = findViewById(R.id.namaBuah);
        BackButton = findViewById(R.id.back);
        NextButton = findViewById(R.id.Next);
        dbHelper = new DBHelper(this);
        daftarBuah = dbHelper.getAllBuah();
        random = new Random();

        if (!daftarBuah.isEmpty()) {
            tampilkanData();
        } else {
            NamaBuah.setText("Tidak ada data");
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
            int randomIndex = random.nextInt(daftarBuah.size());
            Buah buah = daftarBuah.get(randomIndex);
            NamaBuah.setText(buah.getNama());
            GambarBuah.setImageResource(buah.getGambar());


    }
}
