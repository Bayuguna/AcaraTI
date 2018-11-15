package com.example.bayuguna.progmob.Model;

public class UserResponse<T>{
	private T dataUser;
	private String token;
	private boolean status;

	public void setDataUser(T dataUser) {
		this.dataUser = dataUser;
	}

	public T getDataUser() {

		return dataUser;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"UserResponse{" + 
			"dataUser = '" + dataUser + '\'' + 
			",token = '" + token + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
