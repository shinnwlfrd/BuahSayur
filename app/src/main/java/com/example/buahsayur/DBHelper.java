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

        values.put(SAYUR_NAMA, "Cabbage");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.cabbage);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.cabbage);
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

        values.put(SAYUR_NAMA, "Garlic");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.garlic);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.garlic);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Lettuce");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.lettuce);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.lettuce);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Onion");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.onion);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.onions);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Leeks");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.leeks);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.leeks);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Peas");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.peas);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.peas);
        db.insert(TABLE_SAYUR, null, values); values.clear();

        values.put(SAYUR_NAMA, "Potato");
        values.put(SAYUR_GAMBAR_RES_ID, R.drawable.potato);
        values.put(SAYUR_AUDIO_RES_ID, R.raw.potato);
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

        values.put(BUAH_NAMA, "Cherry");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.cherry);
        values.put(BUAH_AUDIO_RES_ID, R.raw.cherry);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Corn");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.corn);
        values.put(BUAH_AUDIO_RES_ID, R.raw.corn);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Cucumber");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.cucumber);
        values.put(BUAH_AUDIO_RES_ID, R.raw.cucumber);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Dragon Fruit");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.dragon_fruit);
        values.put(BUAH_AUDIO_RES_ID, R.raw.dragon_fruit);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Eggplant");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.eggplant);
        values.put(BUAH_AUDIO_RES_ID, R.raw.eggplant);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Grapes");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.grapes);
        values.put(BUAH_AUDIO_RES_ID, R.raw.grapes);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Kiwi");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.kiwi);
        values.put(BUAH_AUDIO_RES_ID, R.raw.kiwi);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Lemon");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.lemon);
        values.put(BUAH_AUDIO_RES_ID, R.raw.lemon);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Mango");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.mango);
        values.put(BUAH_AUDIO_RES_ID, R.raw.mango);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Orange");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.orange);
        values.put(BUAH_AUDIO_RES_ID, R.raw.orange);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Papaya");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.papaya);
        values.put(BUAH_AUDIO_RES_ID, R.raw.papaya);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Pineapple");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.pineapple);
        values.put(BUAH_AUDIO_RES_ID, R.raw.pineapple);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Rambutan");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.rambutan);
        values.put(BUAH_AUDIO_RES_ID, R.raw.rambutan);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Starfruit");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.starfruit);
        values.put(BUAH_AUDIO_RES_ID, R.raw.starfruit);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Strawberry");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.strawberry);
        values.put(BUAH_AUDIO_RES_ID, R.raw.strawberry);
        db.insert(TABLE_BUAH, null, values); values.clear();

        values.put(BUAH_NAMA, "Tomato");
        values.put(BUAH_GAMBAR_RES_ID, R.drawable.tomato);
        values.put(BUAH_AUDIO_RES_ID, R.raw.tomato);
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

    public void resetDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
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
                int audioResId = cursor.getInt(cursor.getColumnIndexOrThrow(SAYUR_AUDIO_RES_ID));
                list.add(new Gabungan(id, nama, gambarResId, audioResId));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
}

