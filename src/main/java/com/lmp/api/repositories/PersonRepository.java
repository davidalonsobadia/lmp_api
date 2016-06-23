package com.lmp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lmp.api.model.Consumer;
import com.lmp.api.model.Person;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
	
	public Person findByIdentifier(@Param("identifier") String identifier);
	
	public Person findFirstByEmail(@Param("email") String email);
	
	public Person findFirstByName(@Param("name") String name);
	
	@Query("SELECT c FROM Person p"
			+ " JOIN p.consumers c"
			+ " WHERE p.name LIKE :username")
	public List<Consumer> findConsumersByUser(@Param("username") String username);
		
	
}
