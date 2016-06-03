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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="provider")
public class Provider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String identifier;
	
	@Column(unique=true)
	private String name;
	
	private String description;
	
	private String type;
	
	private String url;
	
	@Column(name="is_enabled")
	private boolean isEnabled;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	@ManyToMany(mappedBy = "providers")
	private List<Person> people;

	@ManyToMany(mappedBy = "providers")
	private List<Organization> organizations;
	
	@OneToMany
	@JoinTable(name="provider_has_attributes", 
		joinColumns={@JoinColumn(name="provider_id")},
		inverseJoinColumns={@JoinColumn(name="attribute_id")}
	)
	private List<Attribute> attributes;
		
	//tokens
	@ManyToMany
	@JoinTable(name="provider_has_tokens", 
		joinColumns={@JoinColumn(name="provider_id")},
		inverseJoinColumns={@JoinColumn(name="token_id")}
	)
	private List<ProviderToken> providerTokens;
	
	//attributeMapping
	@OneToMany
	@JoinTable(name="attribute_has_attribute_maps", 
		joinColumns={@JoinColumn(name="attribute_id")},
		inverseJoinColumns={@JoinColumn(name="attribute_map_id")}
	)
	private List<AttributeMap> attributeMaps;
	
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public List<ProviderToken> getPersonProviderTokens() {
		return providerTokens;
	}

	public void setPersonProviderTokens(List<ProviderToken> personProviderTokens) {
		this.providerTokens = personProviderTokens;
	}

	public List<AttributeMap> getAttributeMaps() {
		return attributeMaps;
	}

	public void setAttributeMaps(List<AttributeMap> attributeMaps) {
		this.attributeMaps = attributeMaps;
	}
}
