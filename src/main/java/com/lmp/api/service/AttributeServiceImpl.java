package com.lmp.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmp.api.model.Attribute;
import com.lmp.api.repositories.AttributeRepository;
import com.lmp.api.service.interfaces.AttributeService;

@Service
@Transactional
public class AttributeServiceImpl implements AttributeService {
	
	@Autowired
	private AttributeRepository attributeRepository;

	@Override
	public Attribute findAttributeByName(String attributeName) {
		return attributeRepository.findFirstByName(attributeName);
	}

	@Override
	public String getProviderAttributeName(String lmpAttributeName, long id) {
		// TODO Auto-generated method stub
		return null;
	}	
}
