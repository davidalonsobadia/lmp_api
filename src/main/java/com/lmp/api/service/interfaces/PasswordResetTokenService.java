package com.lmp.api.service.interfaces;

import com.lmp.api.model.PasswordResetToken;

public interface PasswordResetTokenService {

	PasswordResetToken findFirstByToken(String token);
	
	void deleteByToken(String token);
}
