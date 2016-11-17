package com.lmp.api.service.interfaces;

import java.util.List;

import com.lmp.api.model.Attribute;
import com.lmp.api.model.Sphere;

public interface AttributeService {

	String getProviderAttributeName(String lmpAttributeName, long id);

	Attribute findAttributeByName(String lmpAttributeName);

	List<Attribute> findAttributesBySphere(Sphere sphere);

}
