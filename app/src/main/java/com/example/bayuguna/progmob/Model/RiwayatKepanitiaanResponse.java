package com.example.bayuguna.progmob.Model;

import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class RiwayatKepanitiaanResponse{

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
	private User users;

//	@SerializedName("det_kegiatan")
//	private DetKegiatan detKegiatan;

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

	public void setUsers(User users){
		this.users = users;
	}

	public User getUsers(){
		return users;
	}

//	public void setDetKegiatan(DetKegiatan detKegiatan){
//		this.detKegiatan = detKegiatan;
//	}
//
//	public DetKegiatan getDetKegiatan(){
//		return detKegiatan;
//	}

	@Override
 	public String toString(){
		return 
			"RiwayatKepanitiaanResponse{" + 
			"id_det_kegiatan = '" + idDetKegiatan + '\'' + 
			",id_users = '" + idUsers + '\'' + 
			",id = '" + id + '\'' + 
			",tanggal_daftar = '" + tanggalDaftar + '\'' + 
			",status_kepanitiaan = '" + statusKepanitiaan + '\'' + 
			",users = '" + users + '\'' + 
//			",det_kegiatan = '" + detKegiatan + '\'' +
			"}";
		}
}