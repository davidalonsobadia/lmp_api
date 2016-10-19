package com.lmp.api.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PasswordResetToken {
	
	// 1 day
	private static final int EXPIRATION = 60 * 24;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String token;
	
	@OneToOne
	private Person person;
	
	private Date exiprationDate;
	
	public PasswordResetToken(){
	}
	
	public PasswordResetToken(Person person, String token){
		this.person = person;
		this.token = token;
		this.exiprationDate = calculateExpirationDate(EXPIRATION);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getExiprationDate() {
		return exiprationDate;
	}

	public void setExiprationDate(Date exiprationDate) {
		this.exiprationDate = exiprationDate;
	}
	
	// METHODS
	private Date calculateExpirationDate(final int expirationTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expirationTimeInMinutes);
        return new Date(cal.getTime().getTime());
	}
	
}
