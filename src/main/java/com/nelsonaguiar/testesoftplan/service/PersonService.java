package com.nelsonaguiar.testesoftplan.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nelsonaguiar.testesoftplan.dto.AbstractPerson;
import com.nelsonaguiar.testesoftplan.dto.PersonDTOV1;
import com.nelsonaguiar.testesoftplan.resources.PersonResource;


@Component
public interface PersonService {

	public PersonResource getById(Long id);
	
	public PersonResource save(AbstractPerson person);
	
	public List<PersonResource> getAll();
	
	public boolean deletePerson(Long id);
	
	public PersonResource updatePerson(AbstractPerson person);
	
	public List<PersonResource> getPersonsByName(String name);
	
}
