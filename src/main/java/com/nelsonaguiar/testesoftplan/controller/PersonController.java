package com.nelsonaguiar.testesoftplan.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelsonaguiar.testesoftplan.dto.PersonDTOV1;
import com.nelsonaguiar.testesoftplan.resources.PersonResource;
import com.nelsonaguiar.testesoftplan.service.PersonCustomServiceV1;
import com.nelsonaguiar.testesoftplan.service.PersonCustomServiceV2;
import com.nelsonaguiar.testesoftplan.service.PersonService;

@RestController
@RequestMapping(value = "/person", produces = "application/hal+json", headers = {"Accept=application/vnd.nelsonaguiar.testesoftplan-v1+json",
		"Accept=application/vnd.nelsonaguiar.testesoftplan-v2+json"})
@Validated
public class PersonController {

	@Autowired
    private BeanFactory beanFactory;
	
	private PersonService service;
	
	@GetMapping(value= "/{id}", consumes = "application/vnd.nelsonaguiar.testesoftplan-v1+json", produces = "application/vnd.nelsonaguiar.testesoftplan-v1+json")
	public ResponseEntity<PersonResource> getV1(@PathVariable final long id) {
		this.service = beanFactory.getBean(PersonCustomServiceV1.class);
		return ResponseEntity.ok(service.getById(id));
	}
	
	@GetMapping(value= "/{id}", consumes = "application/vnd.nelsonaguiar.testesoftplan-v2+json", produces = "application/vnd.nelsonaguiar.testesoftplan-v2+json")	
	public ResponseEntity<PersonResource> getV2(@PathVariable final long id) {
		this.service = beanFactory.getBean(PersonCustomServiceV2.class);
		return ResponseEntity.ok(service.getById(id));
	}
	
	@PostMapping(consumes = "application/vnd.nelsonaguiar.testesoftplan-v1+json", produces = "application/vnd.nelsonaguiar.testesoftplan-v1+json")
	public ResponseEntity<PersonResource> post( @RequestBody @Valid PersonDTOV1 person) {
		this.service = beanFactory.getBean(PersonCustomServiceV1.class);
		System.err.println(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(person));
	}
	
	@GetMapping(consumes = "application/vnd.nelsonaguiar.testesoftplan-v1+json", produces = "application/vnd.nelsonaguiar.testesoftplan-v1+json")
	public ResponseEntity<List<PersonDTOV1>> getPerson() {
		this.service = beanFactory.getBean(PersonCustomServiceV1.class);
		return ResponseEntity.ok(this.service.getPersonsByName(""));
	}
	
	@GetMapping(value= "/search", consumes = "application/vnd.nelsonaguiar.testesoftplan-v1+json", produces = "application/vnd.nelsonaguiar.testesoftplan-v1+json")
	public ResponseEntity<List<PersonDTOV1>> getPersonByName(@RequestParam String name) {
		this.service = beanFactory.getBean(PersonCustomServiceV1.class);
		return ResponseEntity.ok(this.service.getPersonsByName(name));
	}

}
