package com.lmp.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lmp.api.model.ProviderToken;

public interface PersonProviderTokenRepository extends PagingAndSortingRepository<ProviderToken, Long>{

}
