package com.lmp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name="id")
	private long id;
	
	@Column(name="provider_attribute_name")
	private String providerAttributeName;
	
	@Column(name="lmp_attribute_name")
	private String lmpAttributeName;
	
	private String attributeNameInResponse;
			
	@ManyToOne  
	private Provider provider;
	
	@ManyToOne
	private Attribute attribute;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLmpAttributeName() {
		return lmpAttributeName;
	}

	public void setLmpAttributeName(String attributeName) {
		this.lmpAttributeName = attributeName;
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
	
	public String getProviderAttributeName() {
		return providerAttributeName;
	}
	
	public String getAttributeNameInResponse() {
		return attributeNameInResponse;
	}

	public void setAttributeNameInResponse(String attributeNameInResponse) {
		this.attributeNameInResponse = attributeNameInResponse;
	}

	public void setProviderAttributeName(String providerAttributeName) {
		this.providerAttributeName = providerAttributeName;
	}

	@Override
	public String toString(){
		return "Attribute name in the API: " + this.providerAttributeName + "\n"
				+ "Internal attribute name: " + this.lmpAttributeName;
	}
}
