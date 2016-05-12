-- INSERT INTO data ...

-- CONSUMERS
INSERT INTO data_consumer (identifier, name, description, is_enabled, is_deleted) 
	VALUES ('AMAZONWEB', 'Amazon', 'Amazon. Buy Online all kind of products.', true, false);
INSERT INTO data_consumer (identifier, name, description, is_enabled, is_deleted) 
	VALUES ('TROVIT.ES', 'Trovit', 'Trovit. El buscador', true, false);
INSERT INTO data_consumer (identifier, name, description, is_enabled, is_deleted) 
	VALUES ('RASTREATOR.COM', 'Rastreator', 'El mejor comparador de seguros de España', true, false);
INSERT INTO data_consumer (identifier, name, description, is_enabled, is_deleted) 
	VALUES ('BUSCADORMEDICOS.COM', 'Buscador Medico', 'El mejor Buscador de medicos online', true, false);
INSERT INTO data_consumer (identifier, name, description, is_enabled, is_deleted) 
	VALUES ('LANEVERAROJA.COM', 'La Nevera Roja', 'Comida a domicilio', true, false);
	
-- PERSON
INSERT INTO domain_person (identifier, name, surname, phone, email, password)
	VALUES ('xxx', 'Sergi', 'Alonso', '937890532', 'sergi@hotmail.com', '123456');
INSERT INTO domain_person (identifier, name, surname, phone, email, password)
	VALUES ('yyy', 'Juan', 'Caubet', '932322585', 'juan@hotmail.com', '123456');
	

-- CATEGORY
INSERT INTO category (name)
	VALUES ('Health');
INSERT INTO category (name)
	VALUES ('e-Governance');
INSERT INTO category (name)
	VALUES ('Finance');
INSERT INTO category (name)
	VALUES ('Academic');

-- SUBCATEGORY
INSERT INTO subcategory (name, category)
	SELECT 'Health conditions', c.id
	FROM category c
	WHERE c.name LIKE 'Health';	
INSERT INTO subcategory (name, category)
	SELECT 'Risk factors', c.id
	FROM category c
	WHERE c.name LIKE 'Health';
INSERT INTO subcategory (name, category)
	SELECT 'Administration info', c.id
	FROM category c
	WHERE c.name LIKE 'e-Governance';	
INSERT INTO subcategory (name, category)
	SELECT 'Employment details', c.id
	FROM category c
	WHERE c.name LIKE 'e-Governance';
INSERT INTO subcategory (name, category)
	SELECT 'Bank details', c.id
	FROM category c
	WHERE c.name LIKE 'Finance';
INSERT INTO subcategory (name, category)
	SELECT 'Properties', c.id
	FROM category c
	WHERE c.name LIKE 'Finance';
INSERT INTO subcategory (name, category)
	SELECT 'Education', c.id
	FROM category c
	WHERE c.name LIKE 'Academic';
INSERT INTO subcategory (name, category)
	SELECT 'Research', c.id
	FROM category c
	WHERE c.name LIKE 'Academic';
	
-- PROVIDER
INSERT INTO data_provider (identifier, name, description, type, url, is_enabled, is_deleted )
	VALUES ('LACAIXA', 'La Caixa', 'Catalan Bank', 'Financial', 'http://www.lacaixa.es', true, false);
INSERT INTO data_provider (identifier, name, description, type, url, is_enabled, is_deleted )
	VALUES ('HIPERSEGUROS', 'HIPERSEGUROS', 'Los mejores seguros', 'Health', 'https://www.hiperseguros.com', true, false);
INSERT INTO data_provider (identifier, name, description, type, url, is_enabled, is_deleted )
	VALUES ('FACEBOOK', 'Facebook Inc', 'La mayor red social del mundo', 'Social', 'http://www.facebook.com', true, false);
INSERT INTO data_provider (identifier, name, description, type, url, is_enabled, is_deleted )
	VALUES ('TWITTER', 'Twitter', 'Twitter', 'Social', 'http://www.twitter.com', true, false);
INSERT INTO data_provider (identifier, name, description, type, url, is_enabled, is_deleted )
	VALUES ('HOSPITALTERRASSA', 'Hospital de Terrassa', 'Hospital public', 'Health', 'http://www.hospitaldeterrassa.com', true, false);
INSERT INTO data_provider (identifier, name, description, type, url, is_enabled, is_deleted )
	VALUES ('HACIENDA', 'Hacienda de Espanna', 'Gobierno de Espanna', 'Public Administration', 'http://www.hacienda.com', true, false);
INSERT INTO data_provider (identifier, name, description, type, url, is_enabled, is_deleted )
	VALUES ('LINKEDIN', 'LinkedIn', 'Web para poner el curriculum', 'Education', 'http://www.linkedin.com', true, false);
	
	
-- ATTRIBUTE
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Allergic conditions', s.id, 'Allergic conditions', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Health conditions'
	WHERE dp.identifier LIKE 'HOSPITALTERRASSA';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Allergic conditions' 
	WHERE dp.identifier LIKE 'HOSPITALTERRASSA';
	

INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Chronic conditions', s.id, 'Chronic conditions', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Health conditions'
	WHERE dp.identifier LIKE 'HOSPITALTERRASSA';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Chronic conditions'
	WHERE dp.identifier LIKE 'HOSPITALTERRASSA';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Mental health', s.id, 'Mental health', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Risk factors'
	WHERE dp.identifier LIKE 'HOSPITALTERRASSA';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Mental health' 
	WHERE dp.identifier LIKE 'HOSPITALTERRASSA';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Cholesterol level', s.id, 'Cholesterol level', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Risk factors'
	WHERE dp.identifier LIKE 'HOSPITALTERRASSA';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Cholesterol level' 
	WHERE dp.identifier LIKE 'HOSPITALTERRASSA';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Tobacco smoking', s.id, 'Tobacco smoking', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Risk factors'
	WHERE dp.identifier LIKE 'HOSPITALTERRASSA';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Tobacco smoking' 
	WHERE dp.identifier LIKE 'HOSPITALTERRASSA';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Physical conditions', s.id, 'Physical conditions', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Risk factors'
	WHERE dp.identifier LIKE 'HOSPITALTERRASSA';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Physical conditions' 
	WHERE dp.identifier LIKE 'HOSPITALTERRASSA';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Social Security Number', s.id, 'Social Security Number', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Administration info'
	WHERE dp.identifier LIKE 'HACIENDA';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Social Security Number' 
	WHERE dp.identifier LIKE 'HACIENDA';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Unemployment beneift status', s.id, 'Unemployment beneift status', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Employment details'
	WHERE dp.identifier LIKE 'HACIENDA';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Unemployment beneift status' 
	WHERE dp.identifier LIKE 'HACIENDA';

	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Employment history', s.id, 'Employment history', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Employment details'
	WHERE dp.identifier LIKE 'HACIENDA';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Employment history' 
	WHERE dp.identifier LIKE 'HACIENDA';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Account Numbers', s.id, 'Account Numbers', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Bank details'
	WHERE dp.identifier LIKE 'LACAIXA';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Account Numbers' 
	WHERE dp.identifier LIKE 'LACAIXA';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Account balances', s.id, 'Account balances', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Bank details'
	WHERE dp.identifier LIKE 'LACAIXA';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Account balances' 
	WHERE dp.identifier LIKE 'LACAIXA';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Property Details', s.id, 'Property Details', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Properties'
	WHERE dp.identifier LIKE 'LACAIXA';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Property Details' 
	WHERE dp.identifier LIKE 'LACAIXA';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Property taxes', s.id, 'Property taxes', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Properties'
	WHERE dp.identifier LIKE 'LACAIXA';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Property taxes' 
	WHERE dp.identifier LIKE 'LACAIXA';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Educational institutions', s.id, 'Educational institutions', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Education'
	WHERE dp.identifier LIKE 'LINKEDIN';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Educational institutions' 
	WHERE dp.identifier LIKE 'LINKEDIN';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Education level', s.id, 'Education level', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Education'
	WHERE dp.identifier LIKE 'LINKEDIN';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Education level' 
	WHERE dp.identifier LIKE 'LINKEDIN';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Thesis', s.id, 'Master Thesis', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Education'
	WHERE dp.identifier LIKE 'LINKEDIN';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Thesis' 
	WHERE dp.identifier LIKE 'LINKEDIN';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Articles', s.id, 'Writted articles', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Research'
	WHERE dp.identifier LIKE 'LINKEDIN';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Articles' 
	WHERE dp.identifier LIKE 'LINKEDIN';

	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Books', s.id, 'Books written', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Research'
	WHERE dp.identifier LIKE 'LINKEDIN';	
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Books' 
	WHERE dp.identifier LIKE 'LINKEDIN';
	
	
INSERT INTO attribute(name, subcategory, description, reputation, is_enabled, is_deleted, is_updateable, provider)
	SELECT 'Conferences', s.id, 'Assisted Conferences', 8, true, false, true, dp.id
	FROM data_provider dp
	JOIN subcategory s 
		ON s.name LIKE 'Research'
	WHERE dp.identifier LIKE 'LINKEDIN';
INSERT INTO data_provider_attributes(data_provider, attributes)
	SELECT dp.id, attr.id
	FROM data_provider dp
	JOIN attribute attr
		ON attr.name LIKE 'Conferences' 
	WHERE dp.identifier LIKE 'LINKEDIN';


-- Insert consumers in a user
INSERT INTO domain_person_consumers(domain_person, consumers) 
	SELECT dp.id_domain_person, c.id_data_consumer 
	FROM domain_person dp 
	JOIN data_consumer c 
		ON c.name LIKE 'Amazon' 
	WHERE dp.email LIKE 'juan@hotmail.com';
INSERT INTO domain_person_consumers(domain_person, consumers) 
	SELECT dp.id_domain_person, c.id_data_consumer 
	FROM domain_person dp 
	JOIN data_consumer c 
		ON c.name LIKE 'Trovit' 
	WHERE dp.email LIKE 'juan@hotmail.com';
INSERT INTO domain_person_consumers(domain_person, consumers) 
	SELECT dp.id_domain_person, c.id_data_consumer 
	FROM domain_person dp 
	JOIN data_consumer c 
		ON c.name LIKE 'La Nevera Roja' 
	WHERE dp.email LIKE 'juan@hotmail.com';
INSERT INTO domain_person_consumers(domain_person, consumers) 
	SELECT dp.id_domain_person, c.id_data_consumer 
	FROM domain_person dp 
	JOIN data_consumer c 
		ON c.name LIKE 'Rastreator' 
	WHERE dp.email LIKE 'juan@hotmail.com';
	

-- Insert providers in a user
INSERT INTO domain_person_providers(domain_person, providers) 
	SELECT dp.id_domain_person, p.id
	FROM domain_person dp 
	JOIN data_provider p 
		ON p.name LIKE 'La Caixa' 
	WHERE dp.email LIKE 'juan@hotmail.com';
INSERT INTO domain_person_providers(domain_person, providers) 
	SELECT dp.id_domain_person, p.id
	FROM domain_person dp 
	JOIN data_provider p 
		ON p.name LIKE 'Hospital de Terrassa' 
	WHERE dp.email LIKE 'juan@hotmail.com';
INSERT INTO domain_person_providers(domain_person, providers) 
	SELECT dp.id_domain_person, p.id 
	FROM domain_person dp 
	JOIN data_provider p 
		ON p.name LIKE 'LinkedIn' 
	WHERE dp.email LIKE 'juan@hotmail.com';
	

-- Insert entity in a user
INSERT INTO domain_entity(description, email, identifier, name)
	VALUES ('Test entity', 'entity@hotmail.com', 'xxxx', 'Entity Test');
INSERT INTO domain_entity(description, email, identifier, name)
	VALUES ('Test entity 2', 'entity2@hotmail.com', 'yyyy', 'Entity Test 2');
INSERT INTO domain_entity(description, email, identifier, name)
	VALUES ('Test entity 3', 'entity3@hotmail.com', 'zzzz', 'Entity Test 3');
INSERT INTO domain_entity(description, email, identifier, name)
	VALUES ('Test entity 4', 'entity4@hotmail.com', 'ssss', 'Entity Test 4');
INSERT INTO domain_entity(description, email, identifier, name)
	VALUES ('Test entity 5', 'entity5@hotmail.com', 'aaaa', 'Entity Test 5');


--INSERT INTO domain_entity_admin_users(domain_entity, admin_users)
--	SELECT de.id_domain_entity, dp.id_domain_person
--	FROM domain_entity de
--	JOIN domain_person dp
--		ON dp.email LIKE 'juan@hotmail.com'
--	WHERE de.email LIKE 'entity@hotmail.com';
--INSERT INTO domain_entity_admin_users(domain_entity, admin_users)
--	SELECT de.id_domain_entity, dp.id_domain_person
--	FROM domain_entity de
--	JOIN domain_person dp
--		ON dp.email LIKE 'juan@hotmail.com'
--	WHERE de.email LIKE 'entity2@hotmail.com';
--	
--INSERT INTO domain_entity_verified_users(domain_entity, verified_users)
--	SELECT de.id_domain_entity, dp.id_domain_person
--	FROM domain_entity de
--	JOIN domain_person dp
--		ON dp.email LIKE 'juan@hotmail.com'
--	WHERE de.email LIKE 'entity3@hotmail.com';
--	
--	
--INSERT INTO domain_person_admin_entities(domain_person, admin_entities)
--	SELECT dp.id_domain_person, de.id_domain_entity
--	FROM domain_person dp
--	JOIN domain_entity de
--		ON de.email LIKE 'entity@hotmail.com'
--	WHERE dp.email LIKE 'juan@hotmail.com';
--INSERT INTO domain_person_admin_entities(domain_person, admin_entities)
--	SELECT dp.id_domain_person, de.id_domain_entity
--	FROM domain_person dp
--	JOIN domain_entity de
--		ON de.email LIKE 'entity2@hotmail.com'
--	WHERE dp.email LIKE 'juan@hotmail.com';
--	
--INSERT INTO domain_person_verified_entities(domain_person, verified_entities)
--	SELECT dp.id_domain_person, de.id_domain_entity
--	FROM domain_person dp
--	JOIN domain_entity de
--		ON de.email LIKE 'entity3@hotmail.com'
--	WHERE dp.email LIKE 'juan@hotmail.com';
--INSERT INTO domain_person_entities_to_verify(domain_person, entities_to_verify)
--	SELECT  dp.id_domain_person, de.id_domain_entity
--	FROM domain_person dp
--	JOIN domain_entity de
--		ON de.email LIKE 'entity4@hotmail.com'
--	WHERE dp.email LIKE 'juan@hotmail.com';
--INSERT INTO domain_entity_users_to_verify(users_to_verify, domain_entity)
--	SELECT  dp.id_domain_person, de.id_domain_entity
--	FROM domain_person dp
--	JOIN domain_entity de
--		ON de.email LIKE 'entity4@hotmail.com'
--	WHERE dp.email LIKE 'juan@hotmail.com';


INSERT INTO user_entity_association(state, person, organization)
	SELECT 'ASSOCIATED', dp.id_domain_person, de.id_domain_entity
	FROM domain_person dp
	JOIN domain_entity de
		ON de.email LIKE 'entity@hotmail.com'
	WHERE dp.email LIKE 'juan@hotmail.com';
	
INSERT INTO user_entity_association(state, person, organization)
	SELECT 'ASSOCIATED', dp.id_domain_person, de.id_domain_entity
	FROM domain_person dp
	JOIN domain_entity de
		ON de.email LIKE 'entity2@hotmail.com'
	WHERE dp.email LIKE 'juan@hotmail.com';
	
INSERT INTO user_entity_association(state, person, organization)
		SELECT 'REQUESTED_FROM_USER', dp.id_domain_person, de.id_domain_entity
	FROM domain_person dp
	JOIN domain_entity de
		ON de.email LIKE 'entity3@hotmail.com'
	WHERE dp.email LIKE 'juan@hotmail.com';
	
INSERT INTO user_entity_association(state, person, organization)
	SELECT 'REQUESTED_FROM_ENTITY', dp.id_domain_person, de.id_domain_entity
	FROM domain_person dp
	JOIN domain_entity de
		ON de.email LIKE 'entity4@hotmail.com'
	WHERE dp.email LIKE 'juan@hotmail.com';
	
INSERT INTO user_entity_association(state, person, organization)
	SELECT 'ADMINISTRATOR', dp.id_domain_person, de.id_domain_entity
	FROM domain_person dp
	JOIN domain_entity de
		ON de.email LIKE 'entity5@hotmail.com'
	WHERE dp.email LIKE 'juan@hotmail.com';
	
INSERT INTO user_entity_association(state, person, organization)
	SELECT 'REQUESTED_FROM_ENTITY', dp.id_domain_person, de.id_domain_entity
	FROM domain_person dp
	JOIN domain_entity de
		ON de.email LIKE 'entity5@hotmail.com'
	WHERE dp.email LIKE 'sergi@hotmail.com';
	