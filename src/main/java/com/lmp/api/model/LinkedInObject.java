package com.lmp.api.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkedInObject {

	private String firstName;
	
	private String lastName;
	
	private String headline;
	
	private Map<String, String> objects;
	
	public LinkedInObject(){
		objects = new HashMap<String, String>();
	}
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		objects.put("firstName", firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		objects.put("lastName", lastName);
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
		objects.put("headline", headline);
	}
	
	public Map<String, String> getObjects(){
		return this.objects;
	}
}
