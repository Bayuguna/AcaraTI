package com.example.bayuguna.progmob.Model;

import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class DetKegiatan{

	@SerializedName("koor")
	private String koor;

	@SerializedName("line")
	private String line;

	@SerializedName("idKegiatan")
	private int idKegiatan;

	@SerializedName("kuota")
	private String kuota;

	@SerializedName("id")
	private int id;

	@SerializedName("job")
	private String job;

	@SerializedName("sie")
	private String sie;

	public void setKoor(String koor){
		this.koor = koor;
	}

	public String getKoor(){
		return koor;
	}

	public void setLine(String line){
		this.line = line;
	}

	public String getLine(){
		return line;
	}

	public void setIdKegiatan(int idKegiatan){
		this.idKegiatan = idKegiatan;
	}

	public int getIdKegiatan(){
		return idKegiatan;
	}

	public void setKuota(String kuota){
		this.kuota = kuota;
	}

	public String getKuota(){
		return kuota;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setJob(String job){
		this.job = job;
	}

	public String getJob(){
		return job;
	}

	public void setSie(String sie){
		this.sie = sie;
	}

	public String getSie(){
		return sie;
	}

	@Override
 	public String toString(){
		return 
			"DetKegiatan{" + 
			"koor = '" + koor + '\'' + 
			",line = '" + line + '\'' + 
			",idKegiatan = '" + idKegiatan + '\'' + 
			",kuota = '" + kuota + '\'' + 
			",id = '" + id + '\'' + 
			",job = '" + job + '\'' + 
			",sie = '" + sie + '\'' + 
			"}";
		}
}