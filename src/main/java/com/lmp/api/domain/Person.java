package com.lmp.api.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="domain_person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_domain_person")
	private long id;
	
	private String identifier;
	
	private String name;
	
	private String surname;
	
	private String phone;
	
	private String email;
	
	private String password;
	
	private String personal_id;
	
	@ManyToMany
	private List<Consumer> consumers;
	
	@ManyToMany
	private List<Provider> providers;
	
	@OneToMany
	private List<Sphere> spheres;
	
	// Relation with Organization (Entities)
	@ManyToMany
	private List<Organization> verifiedEntities;
	
	@ManyToMany 
	private List<Organization> entitiesToVerify;
	
	@ManyToMany
	private List<Organization> adminEntities;
	
	//uniones
	@OneToMany
	private List<PersonOrganizationRelationship> associations;
	

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersonal_id() {
		return personal_id;
	}

	public void setPersonal_id(String personal_id) {
		this.personal_id = personal_id;
	}
	
	public List<Consumer> getConsumers() {
		return consumers;
	}

	public void setConsumers(List<Consumer> consumers) {
		this.consumers = consumers;
	}

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public List<Sphere> getSpheres() {
		return spheres;
	}

	public void setSpheres(List<Sphere> spheres) {
		this.spheres = spheres;
	}

	public List<Organization> getVerifiedEntities() {
		return verifiedEntities;
	}

	public void setVerifiedEntities(List<Organization> verifiedEntities) {
		this.verifiedEntities = verifiedEntities;
	}

	public List<Organization> getEntitiesToVerify() {
		return entitiesToVerify;
	}

	public void setEntitiesToVerify(List<Organization> entitiesToVerify) {
		this.entitiesToVerify = entitiesToVerify;
	}

	public List<Organization> getAdminEntities() {
		return adminEntities;
	}

	public void setAdminEntities(List<Organization> adminEntities) {
		this.adminEntities = adminEntities;
	}

	public List<PersonOrganizationRelationship> getUniones_person() {
		return associations;
	}

	public void setUniones_person(List<PersonOrganizationRelationship> uniones_person) {
		this.associations = uniones_person;
	}
}
