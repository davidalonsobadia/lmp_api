package com.lmp.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lmp.api.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends PagingAndSortingRepository<PasswordResetToken, Long> {

	PasswordResetToken findFirstByToken(String token);
	
	void deleteByToken(String token);
}
