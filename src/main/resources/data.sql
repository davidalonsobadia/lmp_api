-- Categories
SET @Health = 'Health';
SET @eGovernance = 'e-Governance';
SET @Finance = 'Finance';
SET @WorkExperience = 'Work experience';
SET @Personal = 'Personal';
SET @Academic = 'Academic';

-- Subcategories
SET @HealthConditions = 'Health conditions';
SET @RiskFactors = 'Risk factors';
SET @AdministrationInfo = 'Administration info';
SET @EmploymentDetails = 'Employment details';
SET @BankDetails = 'Bank details';
SET @Properties = 'Properties';
SET @Education = 'Education';
SET @Research = 'Research';
SET @CurrentJobPosition = 'Current Job Position';
SET @PersonalData = 'Personal Data';


-- Provider names
SET @LaCaixa = 'La Caixa';	
SET @HIPERSEGUROS = 'HIPERSEGUROS';
SET @Facebook = 'Facebook';
SET @Twitter = 'Twitter';
SET @HospitalDeTerrassa = 'Hospital de Terrassa';
SET @HaciendaDeEspanna = 'Hacienda de Espanna';
SET @LinkedIn = 'LinkedIn';
SET @Strava = 'Strava';
SET @Fitbit = 'FitBit';

-- Consumer names
SET @Amazon = 'Amazon';
SET @Trovit = 'Trovit';
SET @Rastreator = 'Rastreator';
SET @Twitter = 'Twitter';
SET @BuscadorMedico = 'Buscador Medico';
SET @LaNeveraRoja = 'La Nevera Roja';
SET @HealthInsurance = 'Health Insurance';

-- Attribute names
SET @AllergicConditions = 'Allergic Conditions';
SET @ChronicConditions = 'Chronic conditions';
SET @MentalHealth = 'Mental health';
SET @CholesterolLevel = 'Cholesterol level';
SET @TobaccoSmoking = 'Tobacco smoking';
SET @PhysicalConditions = 'Physical conditions';
SET @SocialSecurityNumber = 'Social Security Number';
SET @UnemploymentBenefitStatus = 'Unemployment benefit status';
SET @EmploymentHistory = 'Employment history';
SET @AccountNumbers = 'Account Numbers';
SET @AccountBalances = 'Account balances';
SET @PropertyDetails = 'Property Details';
SET @PropertyTaxes = 'Property taxes';
SET @EducationalInstitutions = 'Educational institutions';
SET @EducationLevel = 'Education level';
SET @Thesis = 'Thesis';
SET @Articles = 'Articles';
SET @Books = 'Books';
SET @Conferences = 'Conferences';
SET @CurrentPosition = 'Current Position';
SET @Thesis = 'Thesis';
SET @Articles = 'Articles';
SET @Books = 'Books';
SET @FirstName = 'First Name';
SET @Birthday = 'Birthday';
SET @LastName = 'Last Name';
SET @Email = 'Email';
SET @Languages = 'Languages';
SET @Gender = 'Gender';
SET @City = 'City';
SET @Height = 'Height';
SET @Weight = 'Weight';
SET @RelationshipStatus = 'Relationship status';
SET @Picture = 'Picture';
SET @Locale = 'locale';
SET @Positions = 'Positions';
SET @Summary = 'Summary';
SET @LastName = 'Last Name';
SET @Activities = 'Activities';

-- user Mails
SET @user1Mail = 'david@hotmail.com';
SET @user2Mail = 'sergi@hotmail.com';
SET @user3Mail = 'juan@hotmail.com';
SET @user4Mail = 'xec@hotmail.com';
SET @user5Mail = 'gibet@hotmail.com';

-- Entity Mails
SET @entity1Mail = 'entity1@hotmail.com';
SET @entity2Mail = 'entity2@hotmail.com';
SET @entity3Mail = 'entity3@hotmail.com';
SET @entity4Mail = 'entity4@hotmail.com';
SET @entity5Mail = 'entity5@hotmail.com';


-----------------------------------------------------------------------------------------------
-- INSERT INTO data ...

-- CONSUMERS
INSERT INTO consumer (identifier, name, description, is_enabled, is_deleted) 
	VALUES ('AMAZONWEB', @Amazon, 'Amazon. Buy Online all kind of products.', true, false);
INSERT INTO consumer (identifier, name, description, is_enabled, is_deleted) 
	VALUES ('TROVIT.ES', @Trovit, 'Trovit. El buscador', true, false);
INSERT INTO consumer (identifier, name, description, is_enabled, is_deleted) 
	VALUES ('RASTREATOR.COM', @Rastreator, 'El mejor comparador de seguros de España', true, false);
INSERT INTO consumer (identifier, name, description, is_enabled, is_deleted) 
	VALUES ('BUSCADORMEDICOS.COM', @BuscadorMedico, 'El mejor Buscador de medicos online', true, false);
INSERT INTO consumer (identifier, name, description, is_enabled, is_deleted) 
	VALUES ('LANEVERAROJA.COM', @LaNeveraRoja, 'Comida a domicilio', true, false);
INSERT INTO consumer (identifier, name, description, is_enabled, is_deleted) 
	VALUES ('HEALTHINSURANCE.COM', @HealthInsurance, 'Health Insurance Consumer Demo', true, false);
	
-- PERSON
INSERT INTO person (identifier, name, surname, phone, email, password)
	VALUES ('admin', 'web', 'web', '00000000', 'web@hotmail.com', 'EurecatLMP2016!');
INSERT INTO person (identifier, name, surname, phone, email, password)
	VALUES ('yyy', 'David', 'Alonso', '932322585', @user1Mail, '123456');
INSERT INTO person (identifier, name, surname, phone, email, password)
	VALUES ('xxx', 'Sergi', 'Alonso', '937890532', @user2Mail, '123456');
INSERT INTO person (identifier, name, surname, phone, email, password)
	VALUES ('qqq', 'Juan', 'Caubet', '932322585', @user3Mail, '123456');
INSERT INTO person (identifier, name, surname, phone, email, password)
	VALUES ('zzz', 'Xec', 'Catala', '932322585', @user4Mail, '123456');
INSERT INTO person (identifier, name, surname, phone, email, password)
	VALUES ('www', 'Gibet', 'Benejam', '932322585', @user5Mail, '123456');

	

-- CATEGORY
INSERT INTO category (name)
	VALUES (@Health);
INSERT INTO category (name)
	VALUES (@eGovernance);
INSERT INTO category (name)
	VALUES (@Finance);
INSERT INTO category (name)
	VALUES (@Academic);
INSERT INTO category (name)
	VALUES (@WorkExperience);
INSERT INTO category (name)
	VALUES (@Personal);


-- SUBCATEGORY
INSERT INTO subcategory (name, category_id)
	SELECT @HealthConditions, c.id
	FROM category c
	WHERE c.name LIKE @Health;	
INSERT INTO subcategory (name, category_id)
	SELECT @RiskFactors, c.id
	FROM category c
	WHERE c.name LIKE @Health;
INSERT INTO subcategory (name, category_id)
	SELECT @AdministrationInfo, c.id
	FROM category c
	WHERE c.name LIKE @eGovernance;	
INSERT INTO subcategory (name, category_id)
	SELECT @EmploymentDetails, c.id
	FROM category c
	WHERE c.name LIKE @eGovernance;
INSERT INTO subcategory (name, category_id)
	SELECT @BankDetails, c.id
	FROM category c
	WHERE c.name LIKE @Finance;
INSERT INTO subcategory (name, category_id)
	SELECT @Properties, c.id
	FROM category c
	WHERE c.name LIKE @Finance;
INSERT INTO subcategory (name, category_id)
	SELECT @Education, c.id
	FROM category c
	WHERE c.name LIKE @Academic;
INSERT INTO subcategory (name, category_id)
	SELECT @Research, c.id
	FROM category c
	WHERE c.name LIKE @Academic;
INSERT INTO subcategory (name, category_id)
	SELECT @CurrentJobPosition, c.id
	FROM category c
	WHERE c.name LIKE @WorkExperience;
INSERT INTO subcategory (name, category_id)
	SELECT @PersonalData, c.id
	FROM category c
	WHERE c.name LIKE @Personal;
	
-- PROVIDER
INSERT INTO provider (identifier, name, description, type, url, is_enabled, is_deleted, is_oauth, oauth_url )
	VALUES ('LACAIXA', @LaCaixa, 'Catalan Bank', 'Financial', 'http://www.lacaixa.es', true, false, false, '' );
INSERT INTO provider (identifier, name, description, type, url, is_enabled, is_deleted, is_oauth, oauth_url )
	VALUES ('HIPERSEGUROS', @HIPERSEGUROS, 'Los mejores seguros', 'Health', 'https://www.hiperseguros.com', true, false, false, '');
INSERT INTO provider (identifier, name, description, type, url, is_enabled, is_deleted, is_oauth, oauth_url )
	VALUES ('FACEBOOK', @Facebook, 'La mayor red social del mundo', 'Social', 'http://www.facebook.com', true, false, true, '');
INSERT INTO provider (identifier, name, description, type, url, is_enabled, is_deleted, is_oauth, oauth_url )
	VALUES ('TWITTER', @Twitter, 'Twitter', 'Social', 'http://www.twitter.com', true, false, false, '');
INSERT INTO provider (identifier, name, description, type, url, is_enabled, is_deleted, is_oauth, oauth_url )
	VALUES ('HOSPITALTERRASSA', @HospitalDeTerrassa, 'Hospital public', 'Health', 'http://www.hospitaldeterrassa.com', true, false, false, '');
INSERT INTO provider (identifier, name, description, type, url, is_enabled, is_deleted, is_oauth, oauth_url )
	VALUES ('HACIENDA', @HaciendaDeEspanna, 'Gobierno de Espanna', 'Public Administration', 'http://www.hacienda.com', true, false, false, '');
INSERT INTO provider (identifier, name, description, type, url, is_enabled, is_deleted, is_oauth, oauth_url )
	VALUES ('LINKEDIN', @LinkedIn, 'Web para poner el curriculum', 'Education', 'http://www.linkedin.com', true, false, true, '');
INSERT INTO provider (identifier, name, description, type, url, is_enabled, is_deleted, is_oauth, oauth_url )
	VALUES ('STRAVA', @Strava, 'App de deporte', 'Health', 'http://www.strava.com', true, false, true, '');
INSERT INTO provider (identifier, name, description, type, url, is_enabled, is_deleted, is_oauth, oauth_url )
	VALUES ('FitBit', @Fitbit, 'App de deporte con dispositivos IoT', 'Health', 'http://www.fitbit.com', true, false, true, '');	
	
----ATTRIBUTE AllergicConditions -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @AllergicConditions, s.id, @AllergicConditions, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @HealthConditions;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @AllergicConditions, @AllergicConditions, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @HospitalDeTerrassa;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE ChronicConditions -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @ChronicConditions, s.id, @ChronicConditions, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @HealthConditions;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @ChronicConditions, @ChronicConditions, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @HospitalDeTerrassa;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE MentalHealth -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @MentalHealth, s.id, @MentalHealth, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @RiskFactors;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @MentalHealth, @MentalHealth, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @HospitalDeTerrassa;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE MentalHealth -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @CholesterolLevel, s.id, @CholesterolLevel, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @RiskFactors;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @CholesterolLevel, @CholesterolLevel, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @HospitalDeTerrassa;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE TobaccoSmoking -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @TobaccoSmoking, s.id, @TobaccoSmoking, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @RiskFactors;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @TobaccoSmoking, @TobaccoSmoking, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @HospitalDeTerrassa;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE PhysicalConditions -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @PhysicalConditions, s.id, @PhysicalConditions, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @RiskFactors;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @PhysicalConditions, @PhysicalConditions, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @HospitalDeTerrassa;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE SocialSecurityNumber -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @SocialSecurityNumber, s.id, @SocialSecurityNumber, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @AdministrationInfo;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @SocialSecurityNumber, @SocialSecurityNumber, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @HaciendaDeEspanna;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE UnemploymentBenefitStatus -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @UnemploymentBenefitStatus, s.id, @UnemploymentBenefitStatus, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @EmploymentDetails;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @UnemploymentBenefitStatus, @UnemploymentBenefitStatus, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @HaciendaDeEspanna;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------
		
----ATTRIBUTE EmploymentHistory -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @EmploymentHistory, s.id, @EmploymentHistory, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @EmploymentDetails;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @EmploymentHistory, @EmploymentHistory, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @HaciendaDeEspanna;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------	

----ATTRIBUTE AccountNumbers -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @AccountNumbers, s.id, @AccountNumbers, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @BankDetails;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @AccountNumbers, @AccountNumbers, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @LaCaixa;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------
	
----ATTRIBUTE AccountBalances -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @AccountBalances, s.id, @AccountBalances, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @BankDetails;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @AccountBalances, @AccountBalances, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @LaCaixa;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------
	
----ATTRIBUTE PropertyDetails -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @PropertyDetails, s.id, @PropertyDetails, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @Properties;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @PropertyDetails, @PropertyDetails, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @LaCaixa;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------
	
----ATTRIBUTE PropertyTaxes -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @PropertyTaxes, s.id, @PropertyTaxes, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @Properties;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @PropertyTaxes, @PropertyTaxes, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @LaCaixa;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------
	
----ATTRIBUTE EducationalInstitutions -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @EducationalInstitutions, s.id, @EducationalInstitutions, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @Education;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @EducationalInstitutions, @EducationalInstitutions, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @LinkedIn;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------	

----ATTRIBUTE EducationLevel -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @EducationLevel, s.id, @EducationLevel, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @Education;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @EducationLevel, @EducationLevel, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @LinkedIn;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------	

----ATTRIBUTE Thesis -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Thesis, s.id, @Thesis, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @Education;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @Thesis, @Thesis, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @LinkedIn;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------	

----ATTRIBUTE Articles -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Articles, s.id, @Articles, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @Research;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @Articles, @Articles, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @LinkedIn;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------
	
----ATTRIBUTE Books -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Books, s.id, @Books, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @Research;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @Books, @Books, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @LinkedIn;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------
		
----ATTRIBUTE Conferences -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Conferences, s.id, @Conferences, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @Research;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT @Conferences, @Conferences, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @LinkedIn;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------	

----ATTRIBUTE CurrentPosition -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @CurrentPosition, s.id, @CurrentPosition, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @CurrentJobPosition;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'headline', @CurrentPosition, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @LinkedIn;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------		

----ATTRIBUTE FirstName -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @FirstName, s.id, @FirstName, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @PersonalData;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'firstName', @FirstName, @provider_id:= p.id, @attribute_id:= LAST_INSERT_ID()
	FROM provider p
	WHERE p.name LIKE @LinkedIn;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE FirstName WITH OTHER PROVIDER -----
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'first_name', @FirstName, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @FirstName
	WHERE p.name LIKE @Facebook;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE Last Name -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @LastName, s.id, @LastName, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @PersonalData;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'last_name', @LastName, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @LastName
	WHERE p.name LIKE @Facebook;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE Birthday -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Birthday, s.id, @Birthday, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @PersonalData;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'birthday', @Birthday, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @Birthday
	WHERE p.name LIKE @Facebook;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE Email -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Email, s.id, @Email, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @PersonalData;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'email', @Email, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @Email
	WHERE p.name LIKE @Facebook;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE Gender -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Gender, s.id, @Gender, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @PersonalData;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'gender', @Gender, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @Gender
	WHERE p.name LIKE @Facebook;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE Languages -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Languages, s.id, @Languages, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @PersonalData;
INSERT INTO attribute_map(provider_attribute_name, attribute_name_in_response, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'languages', '$..name', @Languages, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @Languages
	WHERE p.name LIKE @Facebook;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE City -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @City, s.id, @City, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @PersonalData;
INSERT INTO attribute_map(provider_attribute_name, attribute_name_in_response, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'athlete', '$.city', @City, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @City
	WHERE p.name LIKE @Strava;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE Activities -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Activities, s.id, @Activities, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @PersonalData;
INSERT INTO attribute_map(provider_attribute_name, attribute_name_in_response, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'activities', '$.[''id'', ''name'', ''distance'', ''start_date_local'', ''type'', ''moving_time'', ''average_speed'']',  @Activities, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @Activities
	WHERE p.name LIKE @Strava;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE Locale -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Locale, s.id, @Locale, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @PersonalData;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'locale', @Locale, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @Locale
	WHERE p.name LIKE @Facebook;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE Height -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Height, s.id, @Height, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @PersonalData;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'height', @Height, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @Height
	WHERE p.name LIKE @Fitbit;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------
	
----ATTRIBUTE Weight -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Weight, s.id, @Weight, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @PersonalData;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'weight', @Weight, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @Weight
	WHERE p.name LIKE @Fitbit;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE Positions -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Positions, s.id, @Positions, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @CurrentJobPosition;
INSERT INTO attribute_map(provider_attribute_name, attribute_name_in_response, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'positions', '$.positions.values', @Positions, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @Positions
	WHERE p.name LIKE @Linkedin;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE Summary -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Summary, s.id, @Summary, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @CurrentJobPosition;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'summary', @Summary, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @Summary
	WHERE p.name LIKE @Linkedin;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

	
----ATTRIBUTE Relationship status -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @RelationshipStatus, s.id, @RelationshipStatus, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @PersonalData;
INSERT INTO attribute_map(provider_attribute_name, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'relationship_status', @RelationshipStatus, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @RelationshipStatus
	WHERE p.name LIKE @Facebook;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

----ATTRIBUTE Picture -----
INSERT INTO attribute(name, subcategory_id, description, reputation, is_enabled, is_deleted, is_updateable)
	SELECT @Picture, s.id, @Picture, 8, true, false, true
	FROM subcategory s 
	WHERE s.name LIKE @PersonalData;
INSERT INTO attribute_map(provider_attribute_name, attribute_name_in_response, lmp_attribute_name, provider_id, attribute_id)
	SELECT 'picture.width(720).height(720)', '$.picture.data.url', @Picture, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @Picture
	WHERE p.name LIKE @Facebook;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

-------- ATTRBIUTE Picture WITH OTHER PROVIDER
INSERT INTO attribute_map(provider_attribute_name, attribute_name_in_response,lmp_attribute_name, provider_id, attribute_id)
	SELECT 'picture-urls::(original)', '$.pictureUrls.values[0]', @Picture, @provider_id:= p.id, @attribute_id:= a.id
	FROM provider p
	JOIN attribute a
		ON a.name LIKE @Picture
	WHERE p.name LIKE @LinkedIn;
INSERT INTO provider_has_attribute_maps(provider_id, attribute_map_id)
	VALUES (@provider_id, @attribute_map_id:= LAST_INSERT_ID() );
INSERT INTO attribute_has_attribute_maps(attribute_id, attribute_map_id)
	VALUES (@attribute_id, @attribute_map_id);
----------------------------------------------------------------------------------------------------------------

	
-- Insert consumers in a user
INSERT INTO person_has_consumers(person_id, consumer_id) 
	SELECT p.id, c.id 
	FROM person p 
	JOIN consumer c 
		ON c.name LIKE @Amazon 
	WHERE p.email LIKE @user1Mail;
INSERT INTO person_has_consumers(person_id, consumer_id) 
	SELECT p.id, c.id 
	FROM person p 
	JOIN consumer c 
		ON c.name LIKE @Trovit 
	WHERE p.email LIKE @user1Mail;	
INSERT INTO person_has_consumers(person_id, consumer_id) 
	SELECT p.id, c.id 
	FROM person p 
	JOIN consumer c 
		ON c.name LIKE @LaNeveraRoja 
	WHERE p.email LIKE @user1Mail;	
INSERT INTO person_has_consumers(person_id, consumer_id) 
	SELECT p.id, c.id 
	FROM person p 
	JOIN consumer c 
		ON c.name LIKE @Rastreator 
	WHERE p.email LIKE @user1Mail;

-- Insert providers in a user
INSERT INTO person_has_providers(person_id, provider_id) 
	SELECT per.id, pro.id
	FROM person per 
	JOIN provider pro
		ON pro.name LIKE @LaCaixa
	WHERE per.email LIKE @user1Mail;
INSERT INTO person_has_providers(person_id, provider_id) 
	SELECT per.id, pro.id
	FROM person per 
	JOIN provider pro
		ON pro.name LIKE @HospitalDeTerrassa
	WHERE per.email LIKE @user1Mail;
INSERT INTO person_has_providers(person_id, provider_id) 
	SELECT per.id, pro.id
	FROM person per 
	JOIN provider pro
		ON pro.name LIKE @LinkedIn
	WHERE per.email LIKE @user1Mail;

		

-- Insert entity in a user
INSERT INTO entity(description, email, identifier, name)
	VALUES ('Test entity 1', 'entity1@hotmail.com', 'xxxx', 'Entity Test');
INSERT INTO entity(description, email, identifier, name)
	VALUES ('Test entity 2', 'entity2@hotmail.com', 'yyyy', 'Entity Test 2');
INSERT INTO entity(description, email, identifier, name)
	VALUES ('Test entity 3', 'entity3@hotmail.com', 'zzzz', 'Entity Test 3');
INSERT INTO entity(description, email, identifier, name)
	VALUES ('Test entity 4', 'entity4@hotmail.com', 'ssss', 'Entity Test 4');
INSERT INTO entity(description, email, identifier, name)
	VALUES ('Test entity 5', 'entity5@hotmail.com', 'aaaa', 'Entity Test 5');


-- Associations between entities and people for user1 (david@hotmail.com)
INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'ASSOCIATED', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity1Mail
	WHERE p.email LIKE @user1Mail;

INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'ASSOCIATED', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity2Mail
	WHERE p.email LIKE @user1Mail;

INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'REQUESTED_FROM_USER', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity3Mail
	WHERE p.email LIKE @user1Mail;
	
INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'ADMINISTRATOR', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity4Mail
	WHERE p.email LIKE @user1Mail;

INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'REQUESTED_FROM_ENTITY', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity5Mail
	WHERE p.email LIKE @user1Mail;

	
-- Associations between entities and people for user2 (sergi@hotmail.com)
INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'ADMINISTRATOR', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity1Mail
	WHERE p.email LIKE @user2Mail;
INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'ADMINISTRATOR', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity2Mail
	WHERE p.email LIKE @user2Mail;
INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'ADMINISTRATOR', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity3Mail
	WHERE p.email LIKE @user2Mail;
INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'ASSOCIATED', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity4Mail
	WHERE p.email LIKE @user2Mail;

INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'ASSOCIATED', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity5Mail
	WHERE p.email LIKE @user2Mail;

-- Associations between entities and people for user3 (juan@hotmail.com)
INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'REQUESTED_FROM_USER', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity1Mail
	WHERE p.email LIKE @user3Mail;
	
INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'REQUESTED_FROM_USER', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity2Mail
	WHERE p.email LIKE @user3Mail;

INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'REQUESTED_FROM_ENTITY', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity3Mail
	WHERE p.email LIKE @user3Mail;
	
INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'REQUESTED_FROM_ENTITY', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity4Mail
	WHERE p.email LIKE @user3Mail;

INSERT INTO person_and_entity_association(state, person_id, organization_id)
	SELECT 'ADMINISTRATOR', p.id, e.id
	FROM person p
	JOIN entity e
		ON e.email LIKE @entity5Mail
	WHERE p.email LIKE @user3Mail;


	
-- Sphere creation
INSERT INTO sphere( name, description, identifier, type, is_enabled, is_deleted, is_extracted )
	VALUES ('Bank Data Sphere', 'Contains all my financial data', 'xxxxx', 'Finance type', true, false, true);

INSERT INTO person_has_spheres(person_id, sphere_id)
	SELECT p.id, s.id
	FROM person p
	JOIN sphere s
		ON s.name LIKE 'Bank Data Sphere'
	WHERE p.email LIKE @user1Mail;

INSERT INTO sphere_has_consumers(sphere_id, consumer_id)
	SELECT s.id, c.id
	FROM sphere s
	JOIN consumer c
		ON s.name LIKE 'Bank Data Sphere'
	WHERE c.name LIKE @Amazon;
INSERT INTO sphere_has_consumers(sphere_id, consumer_id)
	SELECT s.id, c.id
	FROM sphere s
	JOIN consumer c
		ON s.name LIKE 'Bank Data Sphere'
	WHERE c.name LIKE @Trovit;
INSERT INTO sphere_has_consumers(sphere_id, consumer_id)
	SELECT s.id, c.id
	FROM sphere s
	JOIN consumer c
		ON s.name LIKE 'Bank Data Sphere'
	WHERE c.name LIKE @BuscadorMedico;	
	
INSERT INTO sphere_has_attributes(sphere_id, attribute_id)
	SELECT s.id, a.id
	FROM sphere s
	JOIN attribute a
		ON a.name LIKE @AccountNumbers
	WHERE s.name LIKE 'Bank Data Sphere';
INSERT INTO sphere_has_attributes(sphere_id, attribute_id)
	SELECT s.id, a.id
	FROM sphere s
	JOIN attribute a
		ON a.name LIKE @AccountBalances
	WHERE s.name LIKE 'Bank Data Sphere';
INSERT INTO sphere_has_attributes(sphere_id, attribute_id)
	SELECT s.id, a.id
	FROM sphere s
	JOIN attribute a
		ON a.name LIKE @PropertyDetails
	WHERE s.name LIKE 'Bank Data Sphere';
INSERT INTO sphere_has_attributes(sphere_id, attribute_id)
	SELECT s.id, a.id
	FROM sphere s
	JOIN attribute a
		ON a.name LIKE @PropertyTaxes
	WHERE s.name LIKE 'Bank Data Sphere';
	

	
INSERT INTO sphere( name, description, identifier, type, is_enabled, is_deleted, is_extracted )
	VALUES ('Education Sphere', 'Education Life information', 'yyyyy', 'Education', true, false, true);

INSERT INTO person_has_spheres(person_id, sphere_id)
	SELECT p.id, s.id
	FROM person p
	JOIN sphere s
		ON s.name LIKE 'Education Sphere'
	WHERE p.email LIKE @user1Mail;

INSERT INTO sphere_has_consumers(sphere_id, consumer_id)
	SELECT s.id, c.id
	FROM sphere s
	JOIN consumer c
		ON s.name LIKE 'Education Sphere'
	WHERE c.name LIKE @LaNeveraRoja;
INSERT INTO sphere_has_consumers(sphere_id, consumer_id)
	SELECT s.id, c.id
	FROM sphere s
	JOIN consumer c
		ON s.name LIKE 'Education Sphere'
	WHERE c.name LIKE @Rastreator;
	
INSERT INTO sphere_has_attributes(sphere_id, attribute_id)
	SELECT s.id, a.id
	FROM sphere s
	JOIN attribute a
		ON a.name LIKE @EducationLevel
	WHERE s.name LIKE 'Education Sphere';
INSERT INTO sphere_has_attributes(sphere_id, attribute_id)
	SELECT s.id, a.id
	FROM sphere s
	JOIN attribute a
		ON a.name LIKE @EducationalInstitutions
	WHERE s.name LIKE 'Education Sphere';
INSERT INTO sphere_has_attributes(sphere_id, attribute_id)
	SELECT s.id, a.id
	FROM sphere s
	JOIN attribute a
		ON a.name LIKE @Thesis
	WHERE s.name LIKE 'Education Sphere';
INSERT INTO sphere_has_attributes(sphere_id, attribute_id)
	SELECT s.id, a.id
	FROM sphere s
	JOIN attribute a
		ON a.name LIKE @Articles
	WHERE s.name LIKE 'Education Sphere';
	
	
INSERT INTO sphere( name, description, identifier, type, is_enabled, is_deleted, is_extracted )
	VALUES ('XEXI Sphere', 'Xexi first sphere', 'zzzzz', 'Testing Sphere', true, false, true);

INSERT INTO person_has_spheres(person_id, sphere_id)
	SELECT p.id, s.id
	FROM person p
	JOIN sphere s
		ON s.name LIKE 'Testing Sphere'
	WHERE p.email LIKE @user2Mail;
