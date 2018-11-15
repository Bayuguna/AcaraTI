package com.example.bayuguna.progmob.Model;

public class Kegiatans{
    private String nama;
    private String updatedAt;
    private String createdAt;
    private int id;
    private Object pic;
    private String tanggal;
    private String deskripsi;
    private String status;

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getNama(){
        return nama;
    }

    public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }

    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setPic(Object pic){
        this.pic = pic;
    }

    public Object getPic(){
        return pic;
    }

    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }

    public String getTanggal(){
        return tanggal;
    }

    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi(){
        return deskripsi;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return
                "Kegiatan{" +
                        "nama = '" + nama + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",id = '" + id + '\'' +
                        ",pic = '" + pic + '\'' +
                        ",tanggal = '" + tanggal + '\'' +
                        ",deskripsi = '" + deskripsi + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
