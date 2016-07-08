package com.lmp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.lmp.api.model.Attribute;

public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Long> {
	
	@Query(value = "SELECT a.* "
			  +" FROM person p "
			  +" JOIN person_has_spheres phs"
			  +" 	ON p.id = phs.person_id "
			  +" JOIN sphere_has_consumers shc "
			  +" 	ON phs.sphere_id = shc.sphere_id "
			  +" JOIN consumer c "
			  +" 	ON c.id = shc.consumer_id "
			  +" JOIN sphere_has_attributes sha "
			  +" 	ON sha.sphere_id = phs.sphere_id "
			  +" JOIN attribute a "
			  +" 	ON a.id = sha.attribute_id "
			  +" WHERE p.name LIKE :username AND c.name LIKE :consumerName ",
			nativeQuery = true)
	public List<Attribute> findAttributesByUserAndConsumer(
			@Param("username") String username, 
			@Param("consumerName") String consumerName);
	
		
	@Query("SELECT a "
			+ " FROM AttributeMap am"
			+ " JOIN am.provider p"
			+ " JOIN am.attribute a"
			+ " WHERE p.name LIKE :name")
	List<Attribute> findAttributesByProviderName(@Param("name") String providerName);
	
	@Query("Select a "
			+ " FROM AttributeMap am"
			+ " JOIN am.attribute a"
			+ " JOIN am.provider p"
			+ " JOIN a.subcategory sc"
			+ " JOIN sc.category c"
			+ " WHERE c.name LIKE :categoryName"
			+ " AND p.name LIKE :providerName")
	List<Attribute> findAttributesByProviderNameAndCategoryName(@Param("providerName") String providerName,
			@Param("categoryName") String category);
	
	@Query("SELECT a"
			+ " FROM AttributeMap am"
			+ " JOIN am.attribute a"
			+ " JOIN am.provider p"
			+ " WHERE p.name IN :names"
			+ " GROUP BY a.name")
	List<Attribute> findAttributesByProviderNamesList(@Param("names") List<String> providerNames);

}
