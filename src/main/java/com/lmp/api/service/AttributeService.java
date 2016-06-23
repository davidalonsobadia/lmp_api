package com.lmp.api.service;

import com.lmp.api.model.Attribute;

public interface AttributeService {

	public String getAttributeName(String apiAttributeName);
	
	public String getApiAttributeName(String attributeName);
	
	public Attribute getAttributeByName(String name);
}
