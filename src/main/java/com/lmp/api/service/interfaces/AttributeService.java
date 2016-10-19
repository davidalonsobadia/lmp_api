package com.lmp.api.service.interfaces;

import com.lmp.api.model.Attribute;

public interface AttributeService {

	String getProviderAttributeName(String lmpAttributeName, long id);

	Attribute findAttributeByName(String lmpAttributeName);

}
