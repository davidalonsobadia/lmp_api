package com.lmp.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lmp.api.model.AttributeCategory;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface AttributeCategoryRepository extends PagingAndSortingRepository<AttributeCategory, Long> {

}
