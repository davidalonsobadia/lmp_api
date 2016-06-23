package com.lmp.api.service;

import javax.transaction.Transactional;

import com.lmp.api.dao.AttributeDao;
import com.lmp.api.model.Attribute;

public class AttributeServiceImpl implements AttributeService {
	
	private AttributeDao attributeDao;
	
	public void setAttributeDao(AttributeDao attributeDao) {
		this.attributeDao = attributeDao;
	}

	@Override
	@Transactional
	public String getAttributeName(String apiAttributeName) {
		return this.attributeDao.getAttributeName(apiAttributeName);
	}

	@Override
	@Transactional
	public String getApiAttributeName(String attributeName) {
		return this.attributeDao.getApiAttributeName(attributeName);
	}

	@Override
	@Transactional
	public Attribute getAttributeByName(String name) {
		return this.attributeDao.getAttributeByName(name);
	}

}
