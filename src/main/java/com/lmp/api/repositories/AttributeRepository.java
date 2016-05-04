package com.lmp.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lmp.api.domain.Attribute;

public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Long> {

}
