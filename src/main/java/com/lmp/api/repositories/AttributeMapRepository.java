package com.lmp.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lmp.api.model.AttributeMap;

public interface AttributeMapRepository extends PagingAndSortingRepository<AttributeMap, Long> {
	
	AttributeMap findFirstByLmpAttributeNameAndProvider_id(String name, long providerId);
}
