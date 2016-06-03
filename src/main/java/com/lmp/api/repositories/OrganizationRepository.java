package com.lmp.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lmp.api.model.Organization;

@RepositoryRestResource(collectionResourceRel = "entities", path = "entities")
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long>{

}
