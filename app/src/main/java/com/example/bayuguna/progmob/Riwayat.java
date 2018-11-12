package com.example.bayuguna.progmob;

public class Riwayat {

    private String Title;
    private String Sie;
    private String Status;
    private String Tanggal;

    public Riwayat(String title, String sie, String status, String tanggal) {
        Title = title;
        Sie = sie;
        Status = status;
        Tanggal = tanggal;
    }

    public String getTitle() {
        return Title;
    }

    public String getSie() {
        return Sie;
    }

    public String getStatus() {
        return Status;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setSie(String sie) {
        Sie = sie;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }
}
