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

    private static final String TABLE_SAYUR = "Sayur";
    private static final String SAYUR_ID = "id";
    private static final String SAYUR_NAMA = "nama";
    private static final String SAYUR_GAMBAR_RES_ID = "gambar";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BUAH = "CREATE TABLE " + TABLE_BUAH + "("
                + BUAH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BUAH_NAMA + " TEXT,"
                + BUAH_GAMBAR_RES_ID + " INTEGER" + ")";

        String CREATE_TABLE_SAYUR = "CREATE TABLE " + TABLE_SAYUR + "("
                + SAYUR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SAYUR_NAMA + " TEXT, "
                + SAYUR_GAMBAR_RES_ID + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_BUAH);
        db.execSQL(CREATE_TABLE_SAYUR);

        isiDataAwal(db);
    }

    private void isiDataAwal(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Vegetables
        values.put(SAYUR_NAMA, "Asparagus");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.asparagus);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Bean Sprouts");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.bean_sprouts);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Beet");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.beet);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Broccoli");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.broccoli);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Casava Leaves");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.casava_leaves);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Cauliflower");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.cauliflower);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Celery");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.celery);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Carrot");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.carrot);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Turnip");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.turnip);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Water Spinach");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.water_spinach);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Water Chestnut");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.waterchestnut);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Yardlong Bean");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.yardlng_bean);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Zucchini");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.zucchini);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        // Fruits
        values.put(BUAH_NAMA, "Apple");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.apple);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Apricot");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.apricot);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Avocado");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.avocado);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Banana");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.banana);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Blackberry");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.blacberry); // Pastikan nama file sesuai, kemungkinan typo
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Blueberry");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.blueberry);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Cantaloupe");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.cantaloupe);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Watermelon");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.watermelon);
        db.insert(TABLE_BUAH, null, values); values.clear();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUAH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAYUR);
        onCreate(db);
    }

    public void tambahBuah(String nama, int gambarResId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BUAH_NAMA, nama);
        values.put(BUAH_GAMBAR_RES_ID, gambarResId);
        db.insert(TABLE_BUAH, null, values);
        db.close();
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

                list.add(new Buah(id, nama, gambarResId));
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

                list.add(new Sayur(id, nama, gambarResId));
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

                list.add(new Gabungan(id, nama, gambarResId));
            } while (cursor.moveToNext());

        }
        cursor.close();

        cursor = db.rawQuery("SELECT * FROM " + TABLE_SAYUR, null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(SAYUR_ID));
                String nama = cursor.getString(cursor.getColumnIndexOrThrow(SAYUR_NAMA));
                int gambarResId = cursor.getInt(cursor.getColumnIndexOrThrow(SAYUR_GAMBAR_RES_ID));

                list.add(new Gabungan(id, nama, gambarResId));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

}