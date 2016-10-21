package com.lmp.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lmp.api.utils.BCryptPasswordDeserializer;

public class SavePasswordBody {

	private String email;
	
	private String token;
	
	@JsonDeserialize(using = BCryptPasswordDeserializer.class )
	private String password;
	
	public SavePasswordBody(){	
	}
	
	public SavePasswordBody(String email, String token, String password) {
		this.email = email;
		this.token = token;
		this.password = password;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
