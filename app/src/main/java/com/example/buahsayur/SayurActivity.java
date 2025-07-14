package com.example.buahsayur;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;
import java.util.Locale;

import org.tensorflow.lite.Interpreter;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SayurActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    ImageView gambarSayur;
    TextView namaSayur;
    ImageButton nextButton, backButton;

    private Interpreter tflite;
    private List<String> labelList;
    private TextToSpeech tts;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sayur);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CAMERA}, 101);
        }


        gambarSayur = findViewById(R.id.gambarSayur);
        namaSayur = findViewById(R.id.namaSayur);
        nextButton = findViewById(R.id.Next);
        backButton = findViewById(R.id.back);

        nextButton.setOnClickListener(v -> openCamera());

        backButton.setOnClickListener(v -> finish());

        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = tts.setLanguage(new Locale("en", "EN"));
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "Bahasa tidak didukung", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Inisialisasi TTS gagal", Toast.LENGTH_SHORT).show();
            }
        });


        try {
            tflite = new Interpreter(loadModelFile("model_sayur.tflite"));
            labelList = loadLabelList("labels_sayur.txt");
        } catch (
                IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Model tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
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
            gambarSayur.setImageBitmap(imageBitmap);

            classifyImage(imageBitmap);
        }
    }

    private void classifyImage(Bitmap image) {
        //Resize gambar ke 224x224 (sesuai kebutuhan model)
        Bitmap resizedImage = Bitmap.createScaledBitmap(image, 224, 224, true);

        // Siapkan ByteBuffer untuk input model
        ByteBuffer inputBuffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3); // 4 byte = float32
        inputBuffer.order(ByteOrder.nativeOrder());

        int[] pixels = new int[224 * 224];
        resizedImage.getPixels(pixels, 0, 224, 0, 0, 224, 224);

        for (int pixel : pixels) {
            float r = ((pixel >> 16) & 0xFF) / 255.f;
            float g = ((pixel >> 8) & 0xFF) / 255.f;
            float b = (pixel & 0xFF) / 255.f;

            inputBuffer.putFloat(r);
            inputBuffer.putFloat(g);
            inputBuffer.putFloat(b);
        }

        // Output: jumlah label
        float[][] output = new float[1][labelList.size()];

        // Jalankan model
        tflite.run(inputBuffer, output);

        // Cari hasil dengan skor tertinggi
        int maxIndex = 0;
        float maxScore = output[0][0];

        for (int i = 1; i < labelList.size(); i++) {
            if (output[0][i] > maxScore) {
                maxScore = output[0][i];
                maxIndex = i;
            }
        }

        String hasil = labelList.get(maxIndex);
        namaSayur.setText(hasil);
        tts.speak(hasil, TextToSpeech.QUEUE_FLUSH, null, null);
    }



    private MappedByteBuffer loadModelFile(String modelName) throws IOException {
        AssetFileDescriptor fileDescriptor = getAssets().openFd(modelName);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    private List<String> loadLabelList(String fileName) throws IOException {
        List<String> labels = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(fileName)));
        String line;
        while ((line = reader.readLine()) != null) {
            labels.add(line.trim());
        }
        reader.close();
        return labels;
    }

    private ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap, int width, int height) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * width * height * 3);
        byteBuffer.order(ByteOrder.nativeOrder());

        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < pixels.length; i++) {
            int pixel = pixels[i];
            float r = ((pixel >> 16) & 0xFF) / 255.f;
            float g = ((pixel >> 8) & 0xFF) / 255.f;
            float b = (pixel & 0xFF) / 255.f;
            byteBuffer.putFloat(r);
            byteBuffer.putFloat(g);
            byteBuffer.putFloat(b);
        }
        return byteBuffer;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Izin kamera diberikan", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Izin kamera ditolak", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

}
