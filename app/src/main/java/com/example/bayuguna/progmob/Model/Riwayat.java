package com.example.bayuguna.progmob.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//@Generated("com.robohorse.robopojogenerator")
public class Riwayat {
	private List<Riwayat> listKegiatan;

	@SerializedName("nama")
	private String nama;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("status")
	private String status;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public List<Riwayat> getListKegiatan() {
		return listKegiatan;
	}

	public void setListKegiatan(List<Riwayat> listKegiatan) {
		this.listKegiatan = listKegiatan;
	}

	@Override
 	public String toString(){
		return 
			"Riwayat{" +
			"nama = '" + nama + '\'' + 
			",tanggal = '" + tanggal + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}