package com.lmp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lmp.api.model.Organization;

@RepositoryRestResource(collectionResourceRel = "entities", path = "entities")
public interface OrganizationRepository extends JpaRepository<Organization, Long>{

	Organization findFirstByIdentifier(@Param("identifier") String identifier);
	
	Organization findFirstByEmail(@Param("email") String email);
	
	@Query("Select u.organization from PersonEntityRelationship u"
			+ " JOIN u.person p"
			+ " WHERE p.email LIKE :email"
			+ " AND u.state LIKE :state")
	List<Organization> findEntitiesByPersonEmailAndState(@Param("email") String email, @Param("state") String state);
	
	@Query("Select o from PersonEntityRelationship u"
			+ " JOIN u.organization o"
			+ " JOIN u.person p"
			+ " WHERE p.email LIKE :email")
	List<Organization> findEntitiesByPersonEmail(@Param("email") String email);
}

