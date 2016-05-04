package com.lmp.api.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="domain_entity")
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_domain_entity")
	private long id;
	
	private String identifier;
	
	private String name;
	
	private String email;
	
	private String description;
	
	@ManyToOne
	private Person userCreator;
	
	@OneToMany
	private List<Person> users;
	
	
	// Relations
	@ManyToMany
	private List<Consumer> consumers;
	
	@ManyToMany
	private List<Provider> providers;
	
	@OneToMany
	private List<Sphere> spheres;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Person getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(Person userCreator) {
		this.userCreator = userCreator;
	}

	public List<Person> getUsers() {
		return users;
	}

	public void setUsers(List<Person> users) {
		this.users = users;
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
}