package com.lmp.api.service;

import com.lmp.api.dao.SphereDao;
import com.lmp.api.model.Consumer;
import com.lmp.api.model.Sphere;

public interface SphereService {

	void setSphereDao(SphereDao sphereDao);

	boolean isConsumer(Sphere sphere, Consumer consumer);

}
