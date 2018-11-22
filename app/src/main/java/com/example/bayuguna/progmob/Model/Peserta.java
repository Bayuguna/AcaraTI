package com.example.bayuguna.progmob.Model;

import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class Peserta{

	@SerializedName("job_desc")
	private String jobDesc;

	@SerializedName("kuota")
	private String kuota;

	@SerializedName("id_users")
	private int idUsers;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("nama_koor")
	private String namaKoor;

	@SerializedName("line_id")
	private String lineId;

	@SerializedName("id_kegiatan")
	private int idKegiatan;

	@SerializedName("sie")
	private String sie;

	@SerializedName("users")
	private User users;

	@SerializedName("id_det_kegiatan")
	private int idDetKegiatan;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("tanggal_daftar")
	private String tanggalDaftar;

	@SerializedName("status_kepanitiaan")
	private String statusKepanitiaan;

	@SerializedName("det_kegiatan")
	private DetKegiatanItem detKegiatan;

	public void setJobDesc(String jobDesc){
		this.jobDesc = jobDesc;
	}

	public String getJobDesc(){
		return jobDesc;
	}

	public void setKuota(String kuota){
		this.kuota = kuota;
	}

	public String getKuota(){
		return kuota;
	}

	public void setIdUsers(int idUsers){
		this.idUsers = idUsers;
	}

	public int getIdUsers(){
		return idUsers;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setNamaKoor(String namaKoor){
		this.namaKoor = namaKoor;
	}

	public String getNamaKoor(){
		return namaKoor;
	}

	public void setLineId(String lineId){
		this.lineId = lineId;
	}

	public String getLineId(){
		return lineId;
	}

	public void setIdKegiatan(int idKegiatan){
		this.idKegiatan = idKegiatan;
	}

	public int getIdKegiatan(){
		return idKegiatan;
	}

	public void setSie(String sie){
		this.sie = sie;
	}

	public String getSie(){
		return sie;
	}

	public void setUsers(User users){
		this.users = users;
	}

	public User getUsers(){
		return users;
	}

	public void setIdDetKegiatan(int idDetKegiatan){
		this.idDetKegiatan = idDetKegiatan;
	}

	public int getIdDetKegiatan(){
		return idDetKegiatan;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
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

	public void setDetKegiatan(DetKegiatanItem detKegiatan){
		this.detKegiatan = detKegiatan;
	}

	public DetKegiatanItem getDetKegiatan(){
		return detKegiatan;
	}

	@Override
 	public String toString(){
		return 
			"Peserta{" + 
			"job_desc = '" + jobDesc + '\'' + 
			",kuota = '" + kuota + '\'' + 
			",id_users = '" + idUsers + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",nama_koor = '" + namaKoor + '\'' + 
			",line_id = '" + lineId + '\'' + 
			",id_kegiatan = '" + idKegiatan + '\'' + 
			",sie = '" + sie + '\'' + 
			",users = '" + users + '\'' + 
			",id_det_kegiatan = '" + idDetKegiatan + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",id = '" + id + '\'' + 
			",tanggal_daftar = '" + tanggalDaftar + '\'' + 
			",status_kepanitiaan = '" + statusKepanitiaan + '\'' + 
			",det_kegiatan = '" + detKegiatan + '\'' + 
			"}";
		}
}