package com.lmp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.lmp.api.model.Consumer;
import com.lmp.api.model.Person;

public interface ConsumerRepository extends PagingAndSortingRepository<Consumer, Long>{

	@Query("SELECT c FROM Person p"
			+ " JOIN p.consumers c"
			+ " WHERE p.name LIKE :username")
	public List<Consumer> findConsumersByUser(@Param("username") String username);
	
	public Consumer findConsumerByName(String name);

}
