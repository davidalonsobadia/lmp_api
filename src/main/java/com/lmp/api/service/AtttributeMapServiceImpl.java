package com.lmp.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmp.api.model.AttributeMap;
import com.lmp.api.repositories.AttributeMapRepository;
import com.lmp.api.service.interfaces.AttributeMapService;

@Service
@Transactional
public class AtttributeMapServiceImpl implements AttributeMapService {

	@Autowired
	private AttributeMapRepository attributeMapRepository;
	
	@Override
	public AttributeMap findFirstByLmpAttributeNameAndProvider_id(String name, long providerId) {
		return attributeMapRepository.findFirstByLmpAttributeNameAndProvider_id(name, providerId);
	}

}
