package com.lmp.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lmp.api.model.Provider;

public interface ProviderRepository extends PagingAndSortingRepository<Provider, Long> {
		
}
