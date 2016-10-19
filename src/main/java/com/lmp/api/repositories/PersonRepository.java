package com.lmp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lmp.api.model.Person;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
	
	public Person findFirstByIdentifier(@Param("identifier") String identifier);
	
	public Person findFirstByEmail(@Param("email") String email);
	
	public Person findFirstByName(@Param("name") String name);
			
	@Query("Select u.person from PersonEntityRelationship u"
			+ " JOIN u.organization o"
			+ " WHERE o.email LIKE :email"
			+ " AND u.state LIKE :state")	
	List<Person> findPeopleByEntityEmailAndState(@Param("email") String email, @Param("state") String state);
	
	@Query("Select p from PersonEntityRelationship u"
			+ " JOIN u.organization o"
			+ " JOIN u.person p"
			+ " WHERE o.email LIKE :email")
	List<Person> findPeopleByEntityEmail(@Param("email") String email);

	public Person findPersonByName(String username);

	public Person findPersonById(long id);
}
