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
import com.nelsonaguiar.testesoftplan.dto.PersonDTOV2;
import com.nelsonaguiar.testesoftplan.exception.PersonAlreadyExists;
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

	@Override
	public PersonResource getById(Long id) {
		return repository.findById(id).map(p -> new PersonResource(this.toDTO(p)))
				.orElseThrow(() -> new PersonNotFoundException(id));
	}

	@Override
	public PersonResource save(AbstractPerson person) {
		Person personV2 = this.mapper.map(person, Person.class);
		this.repository.findByDocument(personV2.getDocument()).ifPresent(p -> {
			throw new PersonAlreadyExists("pessoa já cadastrada para o cpf: "+p.getDocument());
		});
		personV2.setRegistredAt(new Date(Instant.now().toEpochMilli()));
		this.repository.save(personV2);
		person.setId(personV2.getId());
		return new PersonResource(toDTO(person));
	}

	@Override
	public List<PersonResource> getAll() {
		Optional<List<Person>> personsV2 = Optional.of(this.repository.findAll()).filter(l -> !l.isEmpty());
		return personsV2.orElseThrow(() -> new PersonNotFoundException()).stream().map(p -> {
			return new PersonResource(toDTO(p));
		}).collect(Collectors.toList());
	}

	@Override
	public boolean deletePerson(Long id) {
		Person personV2 = this.mapper
				.map(this.repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id)), Person.class);
		this.repository.delete(personV2);
		return false;
	}

	@Override
	public PersonResource updatePerson(AbstractPerson person) {
		this.repository.findById(person.getId()).orElseThrow(() -> new PersonNotFoundException(person.getId()));
		Person personV1 = this.mapper.map(person, Person.class);
		personV1.setUpdatedAt(new Date(Instant.now().toEpochMilli()));
		personV1.setRegistredAt(personV1.getRegistredAt());
		this.repository.save(personV1);
		return new PersonResource(toDTO(person));
	}

	@Override
	public List<PersonResource> getPersonsByName(String name) {
		return this.repository.findAllByNameContainingIgnoringCase(name).orElseThrow(() -> new PersonNotFoundException(name))
				.stream().map(p -> {
					return new PersonResource(toDTO(p));
				}).collect(Collectors.toList());
	}

	private PersonDTOV2 toDTO(Object obj) {
		return this.mapper.map(obj, PersonDTOV2.class);
	}

}
