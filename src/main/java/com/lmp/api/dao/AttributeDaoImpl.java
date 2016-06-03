package com.lmp.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmp.api.model.AttributeMap;

public class AttributeDaoImpl implements AttributeDao {
	private static final Logger logger = LoggerFactory.getLogger(AttributeDaoImpl.class);

	private SessionFactory sessionFactory;
	
    public void setSessionFactory (SessionFactory sessionFactory){
    	this.sessionFactory = sessionFactory;
    }
	

	@SuppressWarnings("unchecked")
	@Override
	public String getApiAttributeName(String attributeName) {
    	logger.info("in getApiAttributeName method");
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from AttributeMap where attribute_name LIKE '" + attributeName + "'";
		
		logger.info(query);
		
		List<AttributeMap> attributeMaps = session.createQuery(query).list();
		
		logger.info(attributeName);
		
		if(attributeMaps.size() > 0){
			logger.info(attributeMaps.get(0).toString());
			logger.info(attributeMaps.get(0).getAttributeName());
			return attributeMaps.get(0).getApiAttributeName();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAttributeName(String ApiAttributeName) {
    	logger.info("in getAttributeName method");
		Session session = this.sessionFactory.getCurrentSession();
		String query = "from AttributeMap where api_attribute_name LIKE '" + ApiAttributeName + "'";
		
		List<AttributeMap> attributeMaps = session.createQuery(query).list();
		
		if(attributeMaps.size() > 0)
			return attributeMaps.get(0).getAttributeName();
		return null;
	}

}
