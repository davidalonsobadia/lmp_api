package com.lmp.api;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lmp.api.dao.AttributeDaoImpl;
import com.lmp.api.dao.PersonDaoImpl;
import com.lmp.api.dao.PersonProviderTokenDaoImpl;
import com.lmp.api.dao.ProviderDaoImpl;
import com.lmp.api.model.Person;
import com.lmp.api.repositories.PersonRepository;
import com.lmp.api.service.AttributeService;
import com.lmp.api.service.AttributeServiceImpl;
import com.lmp.api.service.PersonProviderTokenService;
import com.lmp.api.service.PersonProviderTokenServiceImpl;
import com.lmp.api.service.PersonService;
import com.lmp.api.service.PersonServiceImpl;
import com.lmp.api.service.ProviderService;
import com.lmp.api.service.ProviderServiceImpl;

@EnableTransactionManagement
@PropertySource({ "classpath:application.properties" })
@SpringBootApplication
public class LmpApiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LmpApiApplication.class);
    }
    
	public static void main(String[] args) {
		SpringApplication.run(LmpApiApplication.class, args);
	}
	
   @Autowired
   private Environment env;
 
   @Bean
   public LocalSessionFactoryBean sessionFactory() {
      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
      sessionFactory.setDataSource(restDataSource());
      sessionFactory.setPackagesToScan(new String[] { "com.lmp.api.model" });
      sessionFactory.setHibernateProperties(hibernateProperties());
      return sessionFactory;
   }
 
   @Bean
   public DataSource restDataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
      dataSource.setUrl(env.getProperty("spring.datasource.url"));
      dataSource.setUsername(env.getProperty("spring.datasource.username"));
      dataSource.setPassword(env.getProperty("spring.datasource.password"));
      return dataSource;
   }
 
   @Bean
   @Autowired
   public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
      HibernateTransactionManager txManager = new HibernateTransactionManager();
      txManager.setSessionFactory(sessionFactory);
      return txManager;
   }
 
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
      return new PersistenceExceptionTranslationPostProcessor();
   }
 
   Properties hibernateProperties() {
      return new Properties() {
		private static final long serialVersionUID = 1L;

		{
            setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
            setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
            setProperty("hibernate.globally_quoted_identifiers", "true");
         }
      };
   }
   
   @Bean
   public PersonService getPersonService(){
	   PersonServiceImpl personService = new PersonServiceImpl();
	   PersonDaoImpl personDao = new PersonDaoImpl();
	   personService.setPersonDao(personDao);
	   personDao.setSessionFactory(this.sessionFactory().getObject());
	   return personService;
   }
	
   @Bean
   public ProviderService getProviderService(){
	   ProviderServiceImpl providerService = new ProviderServiceImpl();
	   ProviderDaoImpl providerDao = new ProviderDaoImpl();
	   providerService.setProviderDao(providerDao);
	   providerDao.setSessionFactory(this.sessionFactory().getObject());
	   return providerService;
   }
   
   @Bean
   public AttributeService getAttributeService(){
	   AttributeServiceImpl attributeService = new AttributeServiceImpl();
	   AttributeDaoImpl attributeDao = new AttributeDaoImpl();
	   attributeService.setAttributeDao(attributeDao);
	   attributeDao.setSessionFactory(this.sessionFactory().getObject());
	   return attributeService;
   }
   
   @Bean
   public PersonProviderTokenService getPersonProviderTokenService(){
	   PersonProviderTokenServiceImpl personProviderTokenService = new PersonProviderTokenServiceImpl();
	   PersonProviderTokenDaoImpl personProviderTokenDao = new PersonProviderTokenDaoImpl();
	   personProviderTokenService.setPersonProviderTokenDao(personProviderTokenDao);
	   personProviderTokenDao.setSessionFactory(this.sessionFactory().getObject());
	   return personProviderTokenService;
   }
}
	
//@Configuration
//class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
//
//	@Autowired
//	PersonRepository personRepository;
//
//	@Override
//	public void init(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService());
//	}
//	
//	@Bean
//	UserDetailsService userDetailsService() {
//		return new UserDetailsService() {
//			@Override
//			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//				Person person = personRepository.findFirstByEmail(email);
//				if(person != null) {
//					return new User(person.getName(), person.getPassword(), true, true, true, true,
//							AuthorityUtils.createAuthorityList("USER"));
//				} else {
//					throw new UsernameNotFoundException("could not find the user '"
//							+ email + "'");
//				}	
//			}
//		};
//	}
//}
//
//@EnableWebSecurity
//@Configuration
//class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().fullyAuthenticated();
//		http.httpBasic();
//		http.csrf().disable();
//	}
//  
//}

