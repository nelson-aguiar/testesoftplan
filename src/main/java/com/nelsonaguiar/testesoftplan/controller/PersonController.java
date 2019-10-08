package com.nelsonaguiar.testesoftplan.controller;

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
	public ResponseEntity<PersonResource> post( @RequestBody PersonDTOV1 person) {
		this.service = beanFactory.getBean(PersonCustomServiceV1.class);
		System.err.println(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(person));
	}
	
//	@GetMapping
//	public ResponseEntity<Resources<PersonResourceV1>> all() {
//		/*
//		 * final List<PersonResource> collection =
//		 * personRepository.findAll().stream().map( p -> new
//		 * PersonResource(this.mapper.map(p,
//		 * PersonDTOV1.class))).collect(Collectors.toList()); final
//		 * Resources<PersonResource> resources = new Resources<>(collection); final
//		 * String uriString =
//		 * ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
//		 * resources.add(new Link(uriString, "self"));
//		 */
//	  return ResponseEntity.ok(null);
//	}

}
