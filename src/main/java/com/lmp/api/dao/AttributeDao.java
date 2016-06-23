package com.lmp.api.dao;

import com.lmp.api.model.Attribute;

public interface AttributeDao {
	
	public String getApiAttributeName(String ApiAttributeName);
	
	public String getAttributeName(String AttributeName);

	public Attribute getAttributeByName(String name);

}
