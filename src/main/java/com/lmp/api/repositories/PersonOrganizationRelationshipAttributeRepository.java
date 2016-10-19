package com.lmp.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lmp.api.model.PersonEntityRelationshipAttribute;

@RepositoryRestResource(collectionResourceRel = "personEntityAttributes", path = "personEntityAttributes")
public interface PersonOrganizationRelationshipAttributeRepository extends PagingAndSortingRepository<PersonEntityRelationshipAttribute, Long> {

}
