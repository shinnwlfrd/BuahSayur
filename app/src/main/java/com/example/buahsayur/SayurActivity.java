package com.example.buahsayur;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SayurActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    ImageView gambarSayur;
    TextView namaSayur;
    ImageButton nextButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sayur);

        gambarSayur = findViewById(R.id.gambarSayur);
        namaSayur = findViewById(R.id.namaSayur);
        nextButton = findViewById(R.id.Next);
        backButton = findViewById(R.id.back);

        nextButton.setOnClickListener(v -> openCamera());

        backButton.setOnClickListener(v -> finish());
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, "Kamera tidak tersedia", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            if (imageBitmap != null) {
                gambarSayur.setImageBitmap(imageBitmap);
                namaSayur.setText("Foto berhasil diambil");
            } else {
                namaSayur.setText("Gagal mengambil gambar");
            }
        }
    }
}
