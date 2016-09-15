package com.lmp.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lmp.api.model.AttributeSubcategory;

@RepositoryRestResource(collectionResourceRel = "subcategories", path = "subcategories")
public interface AttributeSubcategoryRepository extends PagingAndSortingRepository<AttributeSubcategory, Long> {

}
