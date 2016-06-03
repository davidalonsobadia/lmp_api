package com.lmp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.lmp.api.model.Attribute;

public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Long> {
	
	@Query(value = "SELECT a.* FROM person dp JOIN person_spheres dps ON dp.id = dps.person JOIN sphere_person sp ON sp.id_sphere = dps.spheres"
			+ " JOIN sphere_person_consumers spc ON sp.id_sphere = spc.sphere_person JOIN data_consumer dc ON dc.id_data_consumer = spc.consumers JOIN sphere_person_attributes spa ON spa.sphere_person = sp.id_sphere JOIN attribute a ON a.id = spa.attributes"
			+ " WHERE dp.name LIKE :username AND dc.name LIKE :consumerName",
			nativeQuery = true)
	public List<Attribute> findAttributesByUserAndConsumer(
			@Param("username") String username, 
			@Param("consumerName") String consumerName);
}
