package com.lmp.api.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmp.api.model.Consumer;
import com.lmp.api.model.Person;
import com.lmp.api.model.Provider;
import com.lmp.api.model.Sphere;
import com.lmp.api.repositories.PersonRepository;
import com.lmp.api.repositories.ProviderRepository;
import com.lmp.api.service.interfaces.PersonService;

@Service
@Transactional
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ProviderRepository providerRepository;
		
	@Override
	public Person findPersonByName(String username) {
		return personRepository.findPersonByName(username);
	}
	
	@Override
	public Person findPersonByEmail(String email) {
		return personRepository.findFirstByEmail(email);
	}
			
	@Override
	public void updatePersonProviderAssociation(long providerId, long personId) {
		Person person = this.personRepository.findOne(personId);
		Provider provider = this.providerRepository.findOne(providerId);
		
		Set<Provider> personProviders = person.getProviders();
		Iterator<Provider> i = personProviders.iterator();
		while(i.hasNext()){
			if(i.next().getId() == provider.getId()){
				i.remove();
				break;
			}
		}
		
		this.personRepository.save(person); 
	}

	@Override
	public boolean isConsumerInList(String userName, String consumerName) {
		Person person = this.personRepository.findFirstByName(userName);
		List<Consumer> consumers = person.getConsumers();
		for(Consumer consumer : consumers){
			if(consumer.getName().equals(consumerName)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Set<Sphere> getSpheres(Person person) {
		return person.getSpheres();
	}

	@Override
	public void addProvider(Person person, Provider provider) {
		person.getProviders().add(provider);
		this.personRepository.save(person);
	}

	@Override
	public void save(Person person) {
		this.personRepository.save(person);
		
	}
	
	@Override
	public void delete(Person person){
		this.personRepository.delete(person);
	}

	@Override
	public Person findOne(long id) {
		return this.personRepository.findOne(id);
	}
}
