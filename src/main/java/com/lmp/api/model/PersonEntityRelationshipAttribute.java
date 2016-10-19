package com.lmp.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="person_and_entity_attributes")
public class PersonEntityRelationshipAttribute {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private PersonEntityRelationship personEntityRelationship;
	
	private String attributeName;
	
	private String attributeValue;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PersonEntityRelationship getPersonEntityRelationship() {
		return personEntityRelationship;
	}

	public void setPersonEntityRelationship(PersonEntityRelationship personEntityRelationship) {
		this.personEntityRelationship = personEntityRelationship;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}	
}
