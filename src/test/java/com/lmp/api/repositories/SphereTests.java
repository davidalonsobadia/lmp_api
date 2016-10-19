package com.lmp.api.repositories;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lmp.api.model.Sphere;
import com.lmp.api.service.interfaces.SphereService;

//@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = LmpApiBackendApplication.class)
@SpringBootTest
@WebAppConfiguration
public class SphereTests {

	@Autowired(required = true)
	private SphereService sphereService;

	@Test
	public void createSphere() {
		
		
		String identifier = "sphereIdentifier";
		String name = "sphere Test";
		String description = "Test Description";
		String type = "A";
		boolean isEnabled = true;
		boolean isDeleted = false;
		boolean isDataExtracted = true;

		Sphere sphere = new Sphere(identifier, name, description,
				type, isEnabled, isDeleted, isDataExtracted);
			
		sphereService.save(sphere);
		
		Sphere sphereSaved = sphereService.findByIdentifier(identifier);
		
		assertNotNull(sphereSaved);
		
		sphereService.delete(sphereSaved);
				
	}

}
