package com.lmp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmp.api.model.Consumer;
import com.lmp.api.repositories.ConsumerRepository;
import com.lmp.api.service.interfaces.ConsumerService;

@Service
@Transactional
public class ConsumerServiceImpl implements ConsumerService {
	
	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Override
	public Consumer findConsumerByName(String name) {
		return this.consumerRepository.findConsumerByName(name);
	}
}
