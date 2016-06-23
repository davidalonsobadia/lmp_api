package com.lmp.api.service;

import javax.transaction.Transactional;

import com.lmp.api.dao.SphereDao;
import com.lmp.api.model.Consumer;
import com.lmp.api.model.Sphere;

public class SphereServiceImpl implements SphereService {
	
	private SphereDao sphereDao;
	
	@Override
	public void setSphereDao(SphereDao sphereDao) {	
		this.sphereDao = sphereDao;
	}

	@Override
	@Transactional
	public boolean isConsumer(Sphere sphere, Consumer consumer) {
		return sphereDao.isConsumer(sphere, consumer);
	}
}
