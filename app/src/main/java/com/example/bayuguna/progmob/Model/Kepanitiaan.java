package com.example.bayuguna.progmob.Model;

import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class Kepanitiaan{

	@SerializedName("nim")
	private String nim;

	@SerializedName("id_det_kegiatan")
	private int idDetKegiatan;

	@SerializedName("name")
	private String name;

	@SerializedName("id_users")
	private int idUsers;

	@SerializedName("id")
	private int id;

	@SerializedName("id_kegiatan")
	private int idKegiatan;

	@SerializedName("sie")
	private String sie;

	@SerializedName("status")
	private String status;

	public void setNim(String nim){
		this.nim = nim;
	}

	public String getNim(){
		return nim;
	}

	public void setIdDetKegiatan(int idDetKegiatan){
		this.idDetKegiatan = idDetKegiatan;
	}

	public int getIdDetKegiatan(){
		return idDetKegiatan;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Kepanitiaan{" + 
			"nim = '" + nim + '\'' + 
			",id_det_kegiatan = '" + idDetKegiatan + '\'' + 
			",name = '" + name + '\'' + 
			",id_users = '" + idUsers + '\'' + 
			",id = '" + id + '\'' + 
			",id_kegiatan = '" + idKegiatan + '\'' + 
			",sie = '" + sie + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}