package com.example.bayuguna.progmob.Model;

import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class TanggalDaftar{

	@SerializedName("date")
	private String date;

	@SerializedName("timezone")
	private String timezone;

	@SerializedName("timezone_type")
	private int timezoneType;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setTimezone(String timezone){
		this.timezone = timezone;
	}

	public String getTimezone(){
		return timezone;
	}

	public void setTimezoneType(int timezoneType){
		this.timezoneType = timezoneType;
	}

	public int getTimezoneType(){
		return timezoneType;
	}

	@Override
 	public String toString(){
		return 
			"TanggalDaftar{" + 
			"date = '" + date + '\'' + 
			",timezone = '" + timezone + '\'' + 
			",timezone_type = '" + timezoneType + '\'' + 
			"}";
		}
}