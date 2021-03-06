package com.lmp.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sphere")
public class Sphere {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private String identifier;
	
	private String name;
	
	private String description;
	
	private String type;
	
	@Column(name = "is_enabled")
	private boolean isEnabled;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "is_extracted")
	private boolean isDataExtracted;
	
	@ManyToMany/*(fetch=FetchType.EAGER)*/
	@JoinTable(name="sphere_has_consumers", 
			joinColumns={@JoinColumn(name="sphere_id")},
			inverseJoinColumns={@JoinColumn(name="consumer_id")}
	)
	private List<Consumer> consumers;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="sphere_has_attributes", 
		joinColumns={@JoinColumn(name="sphere_id")},
		inverseJoinColumns={@JoinColumn(name="attribute_id")}
	)
	private List<Attribute> attributes;
	
	@ManyToOne
	private Person person;
	
	
	public Sphere(){
	}
	
	public Sphere(String identifier, String name, String description, 
			String type, boolean isEnabled, boolean isDeleted, 
			boolean isDataExtracted){
		this.identifier = identifier;
		this.name = name;
		this.description = description;
		this.type = type;
		this.isEnabled = isEnabled;
		this.isDeleted = isDeleted;
		this.isDataExtracted = isDataExtracted;
	}
	

	public Long getId() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Consumer> getConsumers() {
		return consumers;
	}

	public void setConsumers(List<Consumer> consumers) {
		this.consumers = consumers;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public boolean isDataextracted() {
		return isDataExtracted;
	}

	public void setDataextracted(boolean isDataextracted) {
		this.isDataExtracted = isDataextracted;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
