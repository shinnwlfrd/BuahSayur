package com.example.buahsayur;

public class Gabungan {
    private int id;
    private String nama;
    private int gambar;

    public Gabungan(int id, String nama, int gambar){
        this.id = id;
        this.nama = nama;
        this.gambar = gambar;
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
}
