package com.lmp.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="entity")
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(unique=true, nullable=false)
	private String identifier;
	
	
	private String name;
	
	@Column(unique=true, nullable=false)
	private String email;
	
	private String description;
			
	// Relations
	@ManyToMany
	@JoinTable(name="entity_has_consumers", 
		joinColumns={@JoinColumn(name="entity_id")},
		inverseJoinColumns={@JoinColumn(name="consumer_id")}
	)
	private List<Consumer> consumers;
	
	@ManyToMany
	@JoinTable(name="entity_has_providers", 
		joinColumns={@JoinColumn(name="entity_id")},
		inverseJoinColumns={@JoinColumn(name="provider_id")}
	)
	private List<Provider> providers;
	
	@OneToMany
	@JoinTable(name="entity_has_spheres", 
		joinColumns={@JoinColumn(name="person_id")},
		inverseJoinColumns={@JoinColumn(name="sphere_id")}
	)
	private List<Sphere> spheres;
	
	@OneToMany
	@JoinTable(name="entity_has_person_associations", 
		joinColumns={@JoinColumn(name="entity_id")},
		inverseJoinColumns={@JoinColumn(name="associations_id")}
	)
	private List<PersonOrganizationRelationship> associations;
	
	
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

	public List<PersonOrganizationRelationship> getAssociations() {
		return associations;
	}

	public void setAssociations(List<PersonOrganizationRelationship> associations) {
		this.associations = associations;
	}
}