package com.lmp.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="subcategory")
public class AttributeSubcategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	
	private String name;
	
	@ManyToOne
	private AttributeCategory category;
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AttributeCategory getCategory() {
		return category;
	}

	public void setCategory(AttributeCategory category) {
		this.category = category;
	}	
}
