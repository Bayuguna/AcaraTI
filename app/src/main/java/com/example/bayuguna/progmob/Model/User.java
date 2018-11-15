package com.example.bayuguna.progmob.Model;

import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class User{

	@SerializedName("as")
	private String as;

	@SerializedName("nim")
	private String nim;

	@SerializedName("telp")
	private String telp;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("pic")
	private Object pic;

	@SerializedName("email")
	private String email;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("username")
	private String username;

	@SerializedName("token")
	private String token;

	public void setAs(String as){
		this.as = as;
	}

	public String getAs(){
		return as;
	}

	public void setNim(String nim){
		this.nim = nim;
	}

	public String getNim(){
		return nim;
	}

	public void setTelp(String telp){
		this.telp = telp;
	}

	public String getTelp(){
		return telp;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getToken(){
		return token;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"as = '" + as + '\'' + 
			",nim = '" + nim + '\'' + 
			",telp = '" + telp + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",pic = '" + pic + '\'' + 
			",email = '" + email + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",username = '" + username + '\'' +
			",token = '" + token + '\'' +
			"}";
		}
}