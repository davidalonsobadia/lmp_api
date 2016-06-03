package com.lmp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="attribute_map")
public class AttributeMap {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	
	@Column(name="api_attribute_name")
	private String apiAttributeName;
	
	@Column(name="attribute_name")
	private String attributeName;
		
	@ManyToOne  
	private Provider provider;
	
	@ManyToOne
	private Attribute attribute;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getApiAttributeName() {
		return apiAttributeName;
	}

	public void setApiAttributeName(String apiAttributeName) {
		this.apiAttributeName = apiAttributeName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	
	@Override
	public String toString(){
		return "Attribute name in the API: " + this.apiAttributeName + "\n"
				+ "Internal attribute name: " + this.attributeName;
	}
}
