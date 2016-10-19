package com.lmp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lmp.api.model.PersonEntityRelationship;

@RepositoryRestResource(collectionResourceRel = "personEntityRelationships", path = "personEntityRelationships")
public interface PersonOrganizationRepository extends PagingAndSortingRepository<PersonEntityRelationship, Long>{
	
	@Query("Select u from PersonEntityRelationship u"
			+ " JOIN u.organization o"
			+ " JOIN u.person p"
			+ " WHERE o.email LIKE :entityEmail"
			+ " AND p.email LIKE :personEmail")
	PersonEntityRelationship findPersonEntityRelationshipByEntityEmailAndPersonEmail(@Param("personEmail") String email, @Param("entityEmail") String state);
	
	@Query("Select u from PersonEntityRelationship u"
			+ " JOIN u.organization o"
			+ " WHERE o.email LIKE :email")
	List<PersonEntityRelationship> findPersonEntityRelationshipsByEntityEmail(@Param("email") String email);
	
}
