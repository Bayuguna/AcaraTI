package com.example.bayuguna.progmob.Model;

import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class SieSpinner{

	@SerializedName("id")
	private int id;

	@SerializedName("sie")
	private String sie;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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
			"SieSpinner{" + 
			"id = '" + id + '\'' + 
			",sie = '" + sie + '\'' + 
			"}";
		}
}