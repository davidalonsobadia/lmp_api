package com.lmp.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lmp.api.domain.Person;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
	
	public Person findByIdentifier(@Param("identifier") String identifier);
	
	public Person findFirstByEmail(@Param("email") String email);
	
}
