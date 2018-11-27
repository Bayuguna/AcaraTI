package com.example.bayuguna.progmob.Model;

import com.google.gson.annotations.SerializedName;

public class RiwayatKepanitiaan{

	@SerializedName("id_det_kegiatan")
	private String idDetKegiatan;

	@SerializedName("id_users")
	private int idUsers;

	@SerializedName("id")
	private int id;

	@SerializedName("tanggal_daftar")
	private TanggalDaftar tanggalDaftar;

	@SerializedName("status_kepanitiaan")
	private String statusKepanitiaan;

	@SerializedName("alasan")
	private String alasan;

	public void setIdDetKegiatan(String idDetKegiatan){
		this.idDetKegiatan = idDetKegiatan;
	}

	public String getIdDetKegiatan(){
		return idDetKegiatan;
	}

	public void setIdUsers(int idUsers){
		this.idUsers = idUsers;
	}

	public int getIdUsers(){
		return idUsers;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTanggalDaftar(TanggalDaftar tanggalDaftar){
		this.tanggalDaftar = tanggalDaftar;
	}

	public TanggalDaftar getTanggalDaftar(){
		return tanggalDaftar;
	}

	public void setStatusKepanitiaan(String statusKepanitiaan){
		this.statusKepanitiaan = statusKepanitiaan;
	}

	public String getStatusKepanitiaan(){
		return statusKepanitiaan;
	}

	public void setAlasan(String alasan){
		this.alasan = alasan;
	}

	public String getAlasan(){
		return alasan;
	}

	@Override
 	public String toString(){
		return 
			"RiwayatKepanitiaan{" + 
			"id_det_kegiatan = '" + idDetKegiatan + '\'' + 
			",id_users = '" + idUsers + '\'' + 
			",id = '" + id + '\'' + 
			",tanggal_daftar = '" + tanggalDaftar + '\'' + 
			",status_kepanitiaan = '" + statusKepanitiaan + '\'' + 
			",alasan = '" + alasan + '\'' + 
			"}";
		}
}