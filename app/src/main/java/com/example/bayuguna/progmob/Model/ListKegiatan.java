package com.example.bayuguna.progmob.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//@Generated("com.robohorse.robopojogenerator")
public class ListKegiatan{

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("pic")
	private Object pic;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("status")
	private String status;

	@SerializedName("det_kegiatan")
	private List<DetKegiatanItem> detKegiatan;

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

	public void setDetKegiatan(List<DetKegiatanItem> detKegiatan){
		this.detKegiatan = detKegiatan;
	}

	public List<DetKegiatanItem> getDetKegiatan(){
		return detKegiatan;
	}

	@Override
 	public String toString(){
		return 
			"ListKegiatan{" + 
			"nama = '" + nama + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",pic = '" + pic + '\'' + 
			",tanggal = '" + tanggal + '\'' + 
			",deskripsi = '" + deskripsi + '\'' + 
			",status = '" + status + '\'' + 
			",det_kegiatan = '" + detKegiatan + '\'' + 
			"}";
		}
}