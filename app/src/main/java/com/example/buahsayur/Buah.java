package com.example.buahsayur;

public class Buah {
    private int id;
    private String nama;
    private int gambar;
    private int audio;

    public Buah(int id, String nama, int gambar, int audio){
        this.id = id;
        this.nama = nama;
        this.gambar = gambar;
        this.audio = audio;
    }


    public int getId(){
        return id;
    }
    public String getNama(){
        return nama;
    }
    public int getGambar(){
        return gambar;
    }
    public int getAudio(){
        return audio;
    }
}
