package com.nelsonaguiar.testesoftplan.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
		return repository.findById(id).map(p -> new PersonResource(this.toDTO(p)))
				.orElseThrow(() -> new PersonNotFoundException(id));
	}

	@Override
	public PersonResource save(AbstractPerson person) {
		Person personV1 = this.mapper.map(person, Person.class);
		personV1.setRegistredAt(new Date(Instant.now().toEpochMilli()));;
		personV1 = this.repository.save(personV1);
		return new PersonResource(toDTO(personV1));
	}

	@Override
	public List<PersonResource> getAll() {
		Optional<List<Person>> personsV1 = Optional.of(this.repository.findAll()).filter(l -> !l.isEmpty());
		return personsV1.orElseThrow(() -> new PersonNotFoundException()).stream().map(p -> {
			return new PersonResource(toDTO(p));
		}).collect(Collectors.toList());
	}

	@Override
	public boolean deletePerson(Long id) {
		Person personV1 = this.mapper
				.map(this.repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id)), Person.class);
		this.repository.delete(personV1);
		return false;
	}

	@Override
	public PersonResource updatePerson(AbstractPerson person) {
		this.repository.findById(person.getId()).orElseThrow(() -> new PersonNotFoundException(person.getId()));
		Person personV1 = this.mapper.map(person, Person.class);
		personV1.setUpdatedAt(new Date(Instant.now().toEpochMilli()));
		personV1.setRegistredAt(personV1.getRegistredAt());
		personV1 = this.repository.save(personV1);
		return new PersonResource(toDTO(personV1));
	}

	@Override
	public List<PersonResource> getPersonsByName(String name) {
		return this.repository.findAllByNameContainingIgnoringCase(name).orElseThrow(() -> new PersonNotFoundException(name))
				.stream().map(p -> {
					return new PersonResource(toDTO(p));
				}).collect(Collectors.toList());
	}

	private PersonDTOV1 toDTO(Object obj) {
		return this.mapper.map(obj, PersonDTOV1.class);
	}

}
