package com.example.bayuguna.progmob.Model;

public class SpinerKegiatanTest {
    String nama_kegiatan;

    public SpinerKegiatanTest(String nama_kegiatan) {
        this.nama_kegiatan = nama_kegiatan;

    }

    public String getNama_kegiatan() {
        return nama_kegiatan;
    }

    public void setNama_kegiatan(String nama_kegiatan) {
        this.nama_kegiatan = nama_kegiatan;
    }

    public String toString(){
        return nama_kegiatan;
    }
}

