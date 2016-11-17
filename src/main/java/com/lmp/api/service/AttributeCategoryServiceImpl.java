package com.lmp.api.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lmp.api.model.Attribute;
import com.lmp.api.model.AttributeCategory;
import com.lmp.api.model.AttributeSubcategory;
import com.lmp.api.service.interfaces.AttributeCategoryService;

@Service
@Transactional
public class AttributeCategoryServiceImpl implements AttributeCategoryService {

	@Override
	public AttributeCategory findByAttribute(Attribute attribute) {
		AttributeSubcategory subcategory = attribute.getSubcategory();
		return subcategory.getCategory();
	}

}
