package com.lmp.api.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class Person {
	
	public Person() {}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	private String identifier;
	
	private String name;
	
	private String surname;
	
	private String phone;
	
	@Column(unique=true, nullable=false)
	private String email;
	
	private String password;
	
	@Column(unique=true, nullable=false)
	private String personal_id;
	
	@ManyToMany
	@JoinTable(name="person_has_consumers", 
		joinColumns={@JoinColumn(name="person_id")},
		inverseJoinColumns={@JoinColumn(name="consumer_id")}
	)
	private List<Consumer> consumers;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="person_has_providers", 
		joinColumns={@JoinColumn(name="person_id")},
		inverseJoinColumns={@JoinColumn(name="provider_id")}
	)
	private Set<Provider> providers;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="person_has_spheres", 
		joinColumns={@JoinColumn(name="person_id")},
		inverseJoinColumns={@JoinColumn(name="sphere_id")}
	)
	private Set<Sphere> spheres;
	
	//uniones
	@OneToMany
	@JoinTable(name="person_has_entity_associations", 
		joinColumns={@JoinColumn(name="person_id")},
		inverseJoinColumns={@JoinColumn(name="association_id")}
	)
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

	public Set<Provider> getProviders() {
		return providers;
	}

	public void setProviders(Set<Provider> providers) {
		this.providers = providers;
	}

	public Set<Sphere> getSpheres() {
		return spheres;
	}

	public void setSpheres(Set<Sphere> spheres) {
		this.spheres = spheres;
	}

	public List<PersonOrganizationRelationship> getAssociations() {
		return associations;
	}

	public void setAssociations(List<PersonOrganizationRelationship> associations) {
		this.associations = associations;
	}
}
