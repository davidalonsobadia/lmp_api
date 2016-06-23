package com.lmp.api.service;

import com.lmp.api.dao.ConsumerDao;
import com.lmp.api.model.Consumer;

public interface ConsumerService {
	
	public void setConsumerdao(ConsumerDao consumerDao);
	
	public Consumer getConsumerByName(String name);

}
