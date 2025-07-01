package com.example.buahsayur;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BuahSayur.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BUAH = "Buah";
    private static final String BUAH_ID = "id";
    private static final String BUAH_NAMA = "nama";
    private static final String BUAH_GAMBAR_RES_ID = "gambar";
    private static final String BUAH_AUDIO_RES_ID = "audio";


    private static final String TABLE_SAYUR = "Sayur";
    private static final String SAYUR_ID = "id";
    private static final String SAYUR_NAMA = "nama";
    private static final String SAYUR_GAMBAR_RES_ID = "gambar";
    private static final String SAYUR_AUDIO_RES_ID = "audio";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BUAH = "CREATE TABLE " + TABLE_BUAH + "("
                + BUAH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BUAH_NAMA + " TEXT,"
                + BUAH_GAMBAR_RES_ID + " INTEGER,"
                + BUAH_AUDIO_RES_ID + " INTEGER" + ")";
        String CREATE_TABLE_SAYUR = "CREATE TABLE " + TABLE_SAYUR + "("
                + SAYUR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SAYUR_NAMA + " TEXT,"
                + SAYUR_GAMBAR_RES_ID + " INTEGER,"
                + SAYUR_AUDIO_RES_ID + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_BUAH);
        db.execSQL(CREATE_TABLE_SAYUR);

        isiDataAwal(db);
    }

    private void isiDataAwal(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(SAYUR_NAMA, "Asparagus");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.asparagus);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.asparagus);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Bean Sprouts");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.bean_sprouts);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.bean_sprouts);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Beet");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.beet);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.beet);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Broccoli");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.broccoli);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.broccoli);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Cauliflower");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.cauliflower);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.cauliflower);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Celery");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.celery);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.celery);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Carrot");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.carrot);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.carrots);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Turnip");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.turnip);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.turnip);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Water Spinach");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.water_spinach);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.water_spinach);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Water Chestnut");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.waterchestnut);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.water_chestnut);
        db.insert(TABLE_SAYUR, null, values); values.clear();


        values.put(SAYUR_NAMA, "Zucchini");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.zucchini);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.zucchini);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        // Fruits
        values.put(BUAH_NAMA, "Apple");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.apple);
        values.put(BUAH_AUDIO_RES_ID, R.raw.apple);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Apricot");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.apricot);
        values.put(BUAH_AUDIO_RES_ID, R.raw.apricot);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Avocado");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.avocado);
        values.put(BUAH_AUDIO_RES_ID, R.raw.avocado);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Banana");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.banana);
        values.put(BUAH_AUDIO_RES_ID, R.raw.bananas);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Blackberry");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.blacberry);
        values.put(BUAH_AUDIO_RES_ID, R.raw.blackberry);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Blueberry");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.blueberry);
        values.put(BUAH_AUDIO_RES_ID, R.raw.blueberry);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Cantaloupe");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.cantaloupe);
        values.put(BUAH_AUDIO_RES_ID, R.raw.cantaloupe);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Watermelon");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.watermelon);
        values.put(BUAH_AUDIO_RES_ID, R.raw.watermelon);
        db.insert(TABLE_BUAH, null, values); values.clear();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUAH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAYUR);
        onCreate(db);
    }

    public List<Buah> getAllBuah() {
        List<Buah> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BUAH, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(BUAH_ID));
                String nama = cursor.getString(cursor.getColumnIndexOrThrow(BUAH_NAMA));
                int gambarResId = cursor.getInt(cursor.getColumnIndexOrThrow(BUAH_GAMBAR_RES_ID));
                int audioResId = cursor.getInt(cursor.getColumnIndexOrThrow(BUAH_AUDIO_RES_ID));
                list.add(new Buah(id, nama, gambarResId, audioResId));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    public List<Sayur> getAllSayur() {
        List<Sayur> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SAYUR, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(SAYUR_ID));
                String nama = cursor.getString(cursor.getColumnIndexOrThrow(SAYUR_NAMA));
                int gambarResId = cursor.getInt(cursor.getColumnIndexOrThrow(SAYUR_GAMBAR_RES_ID));
                int audioResId = cursor.getInt(cursor.getColumnIndexOrThrow(SAYUR_AUDIO_RES_ID));

                list.add(new Sayur(id, nama, gambarResId, audioResId));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    @SuppressLint("Recycle")
    public List<Gabungan> getAllBuahSayur(){
        List<Gabungan> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BUAH, null);

        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(BUAH_ID));
                String nama = cursor.getString(cursor.getColumnIndexOrThrow(BUAH_NAMA));
                int gambarResId = cursor.getInt(cursor.getColumnIndexOrThrow(BUAH_GAMBAR_RES_ID));
                int audioResId = cursor.getInt(cursor.getColumnIndexOrThrow(BUAH_AUDIO_RES_ID));
                list.add(new Gabungan(id, nama, gambarResId, audioResId));
            } while (cursor.moveToNext());

        }
        cursor.close();

        cursor = db.rawQuery("SELECT * FROM " + TABLE_SAYUR, null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(SAYUR_ID));
                String nama = cursor.getString(cursor.getColumnIndexOrThrow(SAYUR_NAMA));
                int gambarResId = cursor.getInt(cursor.getColumnIndexOrThrow(SAYUR_GAMBAR_RES_ID));
                int audioResId = cursor.getInt(cursor.getColumnIndexOrThrow(BUAH_AUDIO_RES_ID));
                list.add(new Gabungan(id, nama, gambarResId, audioResId));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public String getAudioPath(String namaItem) {
        SQLiteDatabase db = this.getReadableDatabase();
        String audioPath = null;

        Cursor cursor = db.rawQuery("SELECT audio FROM tabel_buah_sayur WHERE nama = ?", new String[]{namaItem});
        if (cursor.moveToFirst()) {
            audioPath = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return audioPath;
    }


}

