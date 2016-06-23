package com.lmp.api.dao;

import com.lmp.api.model.Consumer;
import com.lmp.api.model.Sphere;

public interface SphereDao {

	boolean isConsumer(Sphere sphere, Consumer consumer);

}
