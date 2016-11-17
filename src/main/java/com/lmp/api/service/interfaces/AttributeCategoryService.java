package com.lmp.api.service.interfaces;

import com.lmp.api.model.Attribute;
import com.lmp.api.model.AttributeCategory;

public interface AttributeCategoryService {

	AttributeCategory findByAttribute(Attribute attribute);
}
