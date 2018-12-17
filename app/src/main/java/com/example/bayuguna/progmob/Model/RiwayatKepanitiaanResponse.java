package com.example.bayuguna.progmob.Model;

import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class RiwayatKepanitiaanResponse{

	@SerializedName("nama")
	private String nama;

	@SerializedName("id")
	private int id;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("sie")
	private String sie;

	@SerializedName("status")
	private String status;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setSie(String sie){
		this.sie = sie;
	}

	public String getSie(){
		return sie;
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
			"RiwayatKepanitiaanResponse{" + 
			"nama = '" + nama + '\'' + 
			",id = '" + id + '\'' + 
			",tanggal = '" + tanggal + '\'' + 
			",sie = '" + sie + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}