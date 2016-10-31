package com.lmp.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="person_and_entity_association")
public class PersonEntityRelationship {
	
	// State: ADMINISTRATOR, ASSOCIATED, REQUESTED_FROM_USER, REQUESTED_FROM_ENTITY
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;

	@ManyToOne
	private Person person;
	
	@ManyToOne
	private Organization organization;
	
	@OneToMany(mappedBy = "personEntityRelationship", cascade=CascadeType.REMOVE)
	private List<PersonEntityRelationshipAttribute> personEntityAttribute; 
	
	private String state;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
