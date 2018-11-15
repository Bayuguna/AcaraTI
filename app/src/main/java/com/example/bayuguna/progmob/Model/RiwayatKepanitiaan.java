package com.example.bayuguna.progmob.Model;

import com.google.gson.annotations.SerializedName;

public class RiwayatKepanitiaan{

	@SerializedName("id_det_kegiatan")
	private int idDetKegiatan;

	@SerializedName("id_users")
	private int idUsers;

	@SerializedName("id")
	private int id;

	@SerializedName("tanggal_daftar")
	private String tanggalDaftar;

	@SerializedName("status_kepanitiaan")
	private String statusKepanitiaan;

	@SerializedName("users")
	private Users users;

	@SerializedName("det_kegiatan")
	private Object detKegiatan;

	public void setIdDetKegiatan(int idDetKegiatan){
		this.idDetKegiatan = idDetKegiatan;
	}

	public int getIdDetKegiatan(){
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

	public void setTanggalDaftar(String tanggalDaftar){
		this.tanggalDaftar = tanggalDaftar;
	}

	public String getTanggalDaftar(){
		return tanggalDaftar;
	}

	public void setStatusKepanitiaan(String statusKepanitiaan){
		this.statusKepanitiaan = statusKepanitiaan;
	}

	public String getStatusKepanitiaan(){
		return statusKepanitiaan;
	}

	public void setUsers(Users users){
		this.users = users;
	}

	public Users getUsers(){
		return users;
	}

	public void setDetKegiatan(Object detKegiatan){
		this.detKegiatan = detKegiatan;
	}

	public Object getDetKegiatan(){
		return detKegiatan;
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
			",users = '" + users + '\'' + 
			",det_kegiatan = '" + detKegiatan + '\'' + 
			"}";
		}
}