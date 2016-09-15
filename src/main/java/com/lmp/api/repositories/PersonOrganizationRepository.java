package com.lmp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lmp.api.model.PersonOrganizationRelationship;

@RepositoryRestResource(collectionResourceRel = "personEntityRelationships", path = "personEntityRelationships")
public interface PersonOrganizationRepository extends PagingAndSortingRepository<PersonOrganizationRelationship, Long>{
	
	@Query("Select u from PersonOrganizationRelationship u"
			+ " JOIN u.organization o"
			+ " JOIN u.person p"
			+ " WHERE o.email LIKE :entityEmail"
			+ " AND p.email LIKE :personEmail")
	PersonOrganizationRelationship findPersonEntityRelationshipByEntityEmailAndPersonEmail(@Param("personEmail") String email, @Param("entityEmail") String state);
	
	@Query("Select u from PersonOrganizationRelationship u"
			+ " JOIN u.organization o"
			+ " WHERE o.email LIKE :email")
	List<PersonOrganizationRelationship> findPersonEntityRelationshipsByEntityEmail(@Param("email") String email);
	
}
