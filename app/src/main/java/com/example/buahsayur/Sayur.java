package com.example.buahsayur;

public class Sayur {
    private int id;
    private String nama;
    private int gambar;

    public Sayur(int id, String nama, int gambar){
        this.id = id;
        this.nama = nama;
        this.gambar = gambar;
    }
    public int id(){
        return id;
    }
    public String getNama(){
        return nama;
    }
    public int getGambar(){
        return gambar;
    }
}
