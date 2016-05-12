package com.lmp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.lmp.api.domain.Organization;
import com.lmp.api.domain.Person;
import com.lmp.api.domain.PersonOrganizationRelationship;

public interface PersonOrganizationRepository extends PagingAndSortingRepository<PersonOrganizationRelationship, Long>{
	
//	@Query("Select u from PersonOrganizationRelationship u"
//			+ " JOIN u.person p"
//			+ " WHERE p.email LIKE :email")
//	List<PersonOrganizationRelationship> findRelationshipsByEmail(@Param("email") String email);
//	
//	@Query("Select u from PersonOrganizationRelationship u"
//			+ " JOIN u.organization o"
//			+ " WHERE o.email LIKE :email")
//	List<Person> findPeopleByEntityEmail(@Param("email") String email);
//	
//	@Query("Select u.person from PersonOrganizationRelationship u"
//			+ " JOIN u.organization o"
//			+ " WHERE o.email LIKE :email"
//			+ " AND u.state LIKE :state")
//	List<Person> findPeopleByEntityEmailAndState(@Param("email") String email, @Param("state") String state);
//	
//	List<PersonOrganizationRelationship> findByState(@Param("state") String state);
//	
//	
	
	@Query("Select u.organization from PersonOrganizationRelationship u"
			+ " JOIN u.person p"
			+ " WHERE p.email LIKE :email"
			+ " AND u.state LIKE :state")
	List<Organization> findOrganizationsByPersonMailAndState(@Param("email") String email, @Param("state") String state);
	
	@Query("Select u.person from PersonOrganizationRelationship u"
			+ " JOIN u.organization o"
			+ " WHERE o.email LIKE :email"
			+ " AND u.state LIKE :state")	
	List<Person> findPeopleByEntityMailAndState(@Param("email") String email, @Param("state") String state);
	
	@Query("Select u from PersonOrganizationRelationship u"
			+ " JOIN u.organization o"
			+ " JOIN u.person p"
			+ " WHERE o.email LIKE :entityEmail"
			+ " AND p.email LIKE :personEmail")
	PersonOrganizationRelationship findPersonOrganizationRelationshipByEntityEmailAndPersonEmail(@Param("personEmail") String email, @Param("entityEmail") String state);
	
	@Query("Select u from PersonOrganizationRelationship u"
			+ " JOIN u.organization o"
			+ " WHERE o.email LIKE :email")
	List<PersonOrganizationRelationship> findPersonOrganizationRelationshipsByEntityEmail(@Param("email") String email);
	
	@Query("Select o from PersonOrganizationRelationship u"
			+ " JOIN u.organization o"
			+ " JOIN u.person p"
			+ " WHERE p.email LIKE :email")
	List<Organization> findOrganizationsByPersonEmail(@Param("email") String email);
	
	@Query("Select p from PersonOrganizationRelationship u"
			+ " JOIN u.organization o"
			+ " JOIN u.person p"
			+ " WHERE o.email LIKE :email")
	List<Person> findPeopleByEntityEmail(@Param("email") String email);
}
