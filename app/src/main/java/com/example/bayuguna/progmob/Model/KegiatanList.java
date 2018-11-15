package com.example.bayuguna.progmob.Model;

import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class KegiatanList{

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("pic")
	private int pic;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("remember_token")
	private Object rememberToken;

	@SerializedName("status")
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

	public void setPic(int pic){
		this.pic = pic;
	}

	public int getPic(){
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

	public void setRememberToken(Object rememberToken){
		this.rememberToken = rememberToken;
	}

	public Object getRememberToken(){
		return rememberToken;
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
			"KegiatanList{" + 
			"nama = '" + nama + '\'' +
			",updated_at = '" + updatedAt + '\'' +
			",created_at = '" + createdAt + '\'' +
			",id = '" + id + '\'' +
			",pic = '" + pic + '\'' + 
			",tanggal = '" + tanggal + '\'' + 
			",deskripsi = '" + deskripsi + '\'' +
			",remember_token = '" + rememberToken + '\'' +
			",status = '" + status + '\'' +
			"}";
		}
}