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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="attribute")
public class Attribute {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique=true, nullable=false)
	private String name;
	
	@OneToOne
	private AttributeSubcategory subcategory;
	
	@Transient
	private String subcategoryName;
	
	@Transient
	private String categoryName;
	
	@Transient
	private String providerName;
	
	private String description;
	
	private int reputation;
	
	@Column(name="is_enabled")
	private boolean isEnabled;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	@Column(name="is_updateable")
	private boolean isUpdateable;
		
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name="attribute_has_attribute_maps", 
		joinColumns={@JoinColumn(name="attribute_id")},
		inverseJoinColumns={@JoinColumn(name="attribute_map_id")}
	)
	private List<AttributeMap> attributeMaps;
	

	public List<AttributeMap> getAttributeMaps() {
		return attributeMaps;
	}

	public void setAttributeMaps(List<AttributeMap> attributeMaps) {
		this.attributeMaps = attributeMaps;
	}

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

	public String getSubcategoryName() {
		return this.subcategory.getName();
	}

	public String getCategoryName() {
		return this.subcategory.getCategory().getName();
	}	
}
