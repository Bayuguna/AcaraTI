package com.example.bayuguna.progmob.Model;

public class ListKegiatan{
	private int idSie;
	private String updatedAt;
	private String jobDesc;
	private Kegiatans kegiatan;
	private String kuota;
	private String createdAt;
	private int id;
	private String namaKoor;
	private String lineId;
	private int idKegiatan;
	private Sie sie;

	public void setIdSie(int idSie){
		this.idSie = idSie;
	}

	public int getIdSie(){
		return idSie;
	}

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

	public void setKegiatan(Kegiatans kegiatan){
		this.kegiatan = kegiatan;
	}

	public Kegiatans getKegiatan(){
		return kegiatan;
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

	public void setSie(Sie sie){
		this.sie = sie;
	}

	public Sie getSie(){
		return sie;
	}

	@Override
 	public String toString(){
		return 
			"ListKegiatan{" + 
			"id_sie = '" + idSie + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",job_desc = '" + jobDesc + '\'' + 
			",kegiatan = '" + kegiatan + '\'' + 
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
