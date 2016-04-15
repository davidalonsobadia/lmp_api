package com.lmp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.lmp.api.domain.Attribute;
import com.lmp.api.domain.AttributeCategory;
import com.lmp.api.domain.Provider;

public interface ProviderRepository extends PagingAndSortingRepository<Provider, Long> {
	
	@Query("SELECT a FROM Attribute a"
			+ " JOIN a.provider p"
			+ " WHERE p.name LIKE :providerName")
	List<Attribute> findAttributesByProviderName(@Param("providerName") String providerName);
	
	@Query("Select a FROM Attribute a"
			+ " JOIN a.provider p"
			+ " JOIN a.subcategory sc"
			+ " JOIN sc.category c"
			+ " WHERE c.name LIKE :category"
			+ " AND p.name LIKE :providerName")
	List<Attribute> findAttributesByProviderNameAndCategory(@Param("providerName") String providerName,
			@Param("category") String category);
	
	@Query("SELECT a FROM Attribute a"
			+ " JOIN a.provider p"
			+ " WHERE p.name IN :providerNames")
	List<Attribute> findAttributesByProviderNamesList(@Param("providerNames") List<String> providerNames);
	
	
	@Query("SELECT ac FROM Attribute a"
			+ " JOIN a.provider p"
			+ " JOIN a.subcategory sc"
			+ " JOIN sc.category ac"
			+ " WHERE p.name IN :providerNames")
	List<AttributeCategory> findCategoriesByProviderNamesList(@Param("providerNames") List<String> providerNames);
	
}
