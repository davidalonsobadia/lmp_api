package com.lmp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lmp.api.model.AttributeCategory;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface AttributeCategoryRepository extends PagingAndSortingRepository<AttributeCategory, Long> {
	@Query("SELECT ac"
			+ " FROM AttributeMap am"
			+ " JOIN am.attribute a"
			+ " JOIN am.provider p"
			+ " JOIN a.subcategory sc"
			+ " JOIN sc.category ac"
			+ " WHERE p.name IN :names")
	List<AttributeCategory> findCategoriesByProviderNamesList(@Param("names") List<String> providerNames);

}
