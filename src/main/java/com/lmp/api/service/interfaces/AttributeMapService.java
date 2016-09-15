package com.lmp.api.service.interfaces;

import com.lmp.api.model.AttributeMap;

public interface AttributeMapService {

	AttributeMap findFirstByLmpAttributeNameAndProvider_id(String name, long providerId);
}
