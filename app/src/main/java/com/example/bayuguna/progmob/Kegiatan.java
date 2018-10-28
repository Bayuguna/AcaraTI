package com.example.bayuguna.progmob;

public class Kegiatan {

    private String Nama;
    private String Open_recruitment;
    private String Description;
    public int Pamflet;

    public Kegiatan() {
    }

    public Kegiatan(String nama, String open_recruitment, String description, int pamflet) {
        Nama = nama;
        Open_recruitment = open_recruitment;
        Description = description;
        Pamflet = pamflet;
    }

    public String getNama() {
        return Nama;
    }

    public String getOpen_recruitment() {
        return Open_recruitment;
    }

    public String getDescription() {
        return Description;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public void setOpen_recruitment(String open_recruitment) {
        Open_recruitment = open_recruitment;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPamflet(int pamflet) {
        Pamflet = pamflet;
    }

    public int getPamflet() {
        return Pamflet;

    }

}
