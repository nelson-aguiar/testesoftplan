package com.nelsonaguiar.testesoftplan.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.nelsonaguiar.testesoftplan.dto.AbstractPerson;
import com.nelsonaguiar.testesoftplan.dto.PersonDTOV2;
import com.nelsonaguiar.testesoftplan.exception.PersonNotFoundException;
import com.nelsonaguiar.testesoftplan.repository.PersonRepository;
import com.nelsonaguiar.testesoftplan.resources.PersonResource;

@Service
@Scope("prototype")
public class PersonCustomServiceV2 implements PersonService{
	
	@Autowired
	private PersonRepository repository;	
	@Autowired 
	private ModelMapper mapper;

	//@Override
	public PersonResource getById(Long id) {
		return repository.findById(id).map(p -> 
		new PersonResource(this.mapper.map(p, PersonDTOV2.class))).orElseThrow(() -> new PersonNotFoundException(id));
	}

	@Override
	public PersonResource save(AbstractPerson person) {
		return null;
	}

	@Override
	public List<PersonResource> getAll() {
		return null;
	}

	@Override
	public boolean deletePerson(Long id) {
		return false;
	}

	@Override
	public PersonResource updatePerson(AbstractPerson person) {
		return null;
	}

}
