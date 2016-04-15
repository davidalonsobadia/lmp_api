package com.lmp.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="attribute")
public class Attribute {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_attribute")
	private long id;

	private String name;
	
	@OneToOne
	private AttributeSubcategory subcategory;
	
	private String description;
	
	private int reputation;
	
	private boolean isEnabled;
	
	private boolean isDeleted;
	
	private boolean isUpdateable;
	
	@ManyToOne
	private Provider provider;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	public AttributeSubcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(AttributeSubcategory subcategory) {
		this.subcategory = subcategory;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
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

	public boolean isUpdateable() {
		return isUpdateable;
	}

	public void setUpdateable(boolean isUpdateable) {
		this.isUpdateable = isUpdateable;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
}
