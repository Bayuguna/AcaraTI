package com.example.bayuguna.progmob.Model;

import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class DetKegiatanItem{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("job_desc")
	private String jobDesc;

	@SerializedName("kuota")
	private String kuota;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("nama_koor")
	private String namaKoor;

	@SerializedName("line_id")
	private String lineId;

	@SerializedName("id_kegiatan")
	private int idKegiatan;

	@SerializedName("sie")
	private String sie;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

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

	@Override
	public String toString(){
		return
				"DataItem{" +
						"updated_at = '" + updatedAt + '\'' +
						",job_desc = '" + jobDesc + '\'' +
						",kuota = '" + kuota + '\'' +
						",created_at = '" + createdAt + '\'' +
						",id = '" + id + '\'' +
						",nama_koor = '" + namaKoor + '\'' +
						",line_id = '" + lineId + '\'' +
						",id_kegiatan = '" + idKegiatan + '\'' +
						",sie = '" + sie + '\'' +
						"}";
	}
}