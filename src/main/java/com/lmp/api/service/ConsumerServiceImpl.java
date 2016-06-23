package com.lmp.api.service;

import org.springframework.transaction.annotation.Transactional;

import com.lmp.api.dao.ConsumerDao;
import com.lmp.api.model.Consumer;

public class ConsumerServiceImpl implements ConsumerService {
	
	private ConsumerDao consumerDao;
	
	@Override
	public void setConsumerdao(ConsumerDao consumerDao) {	
		this.consumerDao = consumerDao;
	}

	@Override
	@Transactional
	public Consumer getConsumerByName(String name) {
		return this.consumerDao.getConsumerByName(name);
	}
}
