package com.lmp.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmp.api.model.PasswordResetToken;
import com.lmp.api.repositories.PasswordResetTokenRepository;
import com.lmp.api.service.interfaces.PasswordResetTokenService;

@Service
@Transactional
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public PasswordResetToken findFirstByToken(String token) {
		return passwordResetTokenRepository.findFirstByToken(token);
	}

	@Override
	public void deleteByToken(String token) {
		passwordResetTokenRepository.deleteByToken(token);
	}
	
}
