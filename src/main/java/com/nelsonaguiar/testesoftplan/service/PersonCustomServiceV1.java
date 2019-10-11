package com.nelsonaguiar.testesoftplan.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.nelsonaguiar.testesoftplan.beans.Person;
import com.nelsonaguiar.testesoftplan.dto.AbstractPerson;
import com.nelsonaguiar.testesoftplan.dto.PersonDTOV1;
import com.nelsonaguiar.testesoftplan.exception.PersonNotFoundException;
import com.nelsonaguiar.testesoftplan.repository.PersonRepository;
import com.nelsonaguiar.testesoftplan.resources.PersonResource;

@Service
@Scope("prototype")
public class PersonCustomServiceV1 implements PersonService {
	
	@Autowired
	private PersonRepository repository;	
	@Autowired 
	private ModelMapper mapper;
	

	@Override
	public PersonResource getById(Long id) {
		return repository.findById(id).map(p -> 
			new PersonResource(this.toDTO(p))).orElseThrow(() -> new PersonNotFoundException(id));
	}

	@Override
	public PersonResource save(AbstractPerson person) {
		Person personV1 = this.mapper.map(person, Person.class);
		personV1.setRegistredAt(new Date(Instant.now().toEpochMilli()));
		this.repository.save(personV1);
		person.setId(personV1.getId());		
		return new PersonResource(toDTO(person));
	}

	@Override
	public List<PersonResource> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePerson(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PersonResource updatePerson(AbstractPerson person) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<PersonDTOV1> getPersonsByName(String name){
		return repository.findAllByNameContaining(name).orElseThrow(() -> new PersonNotFoundException(name)).stream().map(p->{
			return toDTO(p);
		}).collect(Collectors.toList());
	}
	
	private PersonDTOV1 toDTO(Object obj) {
		return this.mapper.map(obj, PersonDTOV1.class);
	}

}
