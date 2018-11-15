package com.example.bayuguna.progmob.Model;

public class Sie{
	private String nama;
	private int id;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Sie{" + 
			"nama = '" + nama + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
