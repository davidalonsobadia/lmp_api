package com.lmp.api.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.lmp.api.model.Token;

public interface TokenRepository extends PagingAndSortingRepository<Token, Long> {

	@Query("SELECT token "
			  +" FROM Token token "
			  +" JOIN token.person per"
			  +" JOIN token.provider pro "
			  +" WHERE pro.name LIKE :providerName AND per.email LIKE :email "
			)
	Token findTokenByProviderNameAndUserEmail(
			@Param("provider") String providerName,
			@Param("email") String userEmail);
}
