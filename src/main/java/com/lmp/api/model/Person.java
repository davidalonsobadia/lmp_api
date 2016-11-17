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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lmp.api.utils.BCryptPasswordDeserializer;


@Entity
@Table(name="person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	private String name;
	
	private String surname;
	
	private String phone;
	
	@Column(unique=true, nullable=false)
	private String email;
	
	@JsonDeserialize(using = BCryptPasswordDeserializer.class )
	private String password;
	
	@Column(unique=true, nullable=false)
	private String identifier;
	
	private String address;
	
	private String postalCode;
	
	private String city;
	
	private String country;
	
	private String description;
	
	private String company;
	
	private String jobPosition;
	
	@Column(name="is_legal", columnDefinition="boolean default false", nullable=false)
	private boolean legal = false;
	
	@Column(name="is_client_role", columnDefinition="boolean default true", nullable=true)
	private boolean clientRole = true;
	
	@Column(name="is_provider_role", columnDefinition="boolean default false", nullable=false)
	private boolean providerRole = false;
	
	@Column(name="is_activated", columnDefinition="boolean default false", nullable=false)
	private boolean activated = false;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="person_has_consumers", 
		joinColumns={@JoinColumn(name="person_id")},
		inverseJoinColumns={@JoinColumn(name="consumer_id")}
	)
	private Set<Consumer> consumers;
	
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
	@OneToMany(mappedBy="person", cascade=CascadeType.REMOVE)
//	@JoinTable(name="person_has_entity_associations", 
//		joinColumns={@JoinColumn(name="person_id")},
//		inverseJoinColumns={@JoinColumn(name="association_id")}
//	)
	private List<PersonEntityRelationship> associations;
			
	public Person(){}
	
	public Person (String name, String surname, String phone,
			 String email, String password, String identifier){
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.email = email;
		this.setPassword(password);
		this.identifier = identifier;
	}
	
		
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

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public Set<Consumer> getConsumers() {
		return consumers;
	}

	public void setConsumers(Set<Consumer> consumers) {
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

	public List<PersonEntityRelationship> getAssociations() {
		return associations;
	}

	public void setAssociations(List<PersonEntityRelationship> associations) {
		this.associations = associations;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public boolean isLegal() {
		return legal;
	}

	public void setLegal(boolean legal) {
		this.legal = legal;
	}

	public boolean isClientRole() {
		return clientRole;
	}

	public void setClientRole(boolean clientRole) {
		this.clientRole = clientRole;
	}

	public boolean isProviderRole() {
		return providerRole;
	}

	public void setProviderRole(boolean providerRole) {
		this.providerRole = providerRole;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
}
