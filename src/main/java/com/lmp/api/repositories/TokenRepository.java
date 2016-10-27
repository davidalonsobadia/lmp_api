package com.lmp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Token;

public interface TokenRepository extends PagingAndSortingRepository<Token, Long> {

	@Query("SELECT tok "
			  +" FROM Token tok "
			  +" JOIN tok.person per"
			  +" JOIN tok.provider pro "
			  +" WHERE pro.name LIKE :provider AND per.email LIKE :email "
			)
	List<Token> findTokensByProviderNameAndUserEmail(
			@Param("provider") String providerName,
			@Param("email") String userEmail);

	
	List<Token> findTokensByPersonAndProvider(Person person, Provider provider);
}
