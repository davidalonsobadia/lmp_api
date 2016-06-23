package com.lmp.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Sphere;

@Configuration
public class WebMvcConfig extends RepositoryRestMvcConfiguration {

	@Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Person.class, Provider.class, Sphere.class);
    }
	
//  @Override 
//  public JpaHelper jpaHelper() { 
//	  JpaHelper helper = new JpaHelper();  
//	  helper.getInterceptors().add(new CustomInterceptor());
//	  return helper;
//  }
  
//  @Override
//  public void addInterceptors(InterceptorRegistry registry) {
//	  registry.addInterceptor(new CustomInterceptor()).addPathPatterns("/people/*");;
//
//  }
}