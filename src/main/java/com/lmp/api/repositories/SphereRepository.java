package com.lmp.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lmp.api.model.Sphere;

public interface SphereRepository extends PagingAndSortingRepository<Sphere, Long> {
	
	Sphere findByIdentifier(String identifier);

}
