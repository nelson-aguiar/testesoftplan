package com.nelsonaguiar.testesoftplan.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import com.nelsonaguiar.testesoftplan.controller.PersonController;
import com.nelsonaguiar.testesoftplan.dto.AbstractPerson;
import com.nelsonaguiar.testesoftplan.dto.PersonDTOV1;
import com.nelsonaguiar.testesoftplan.dto.PersonDTOV2;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class PersonResource extends ResourceSupport{

	private AbstractPerson person;
	
	public PersonResource(PersonDTOV1 person) {
		this.person = person;
	    final long id = person.getId();
	    add(linkTo(PersonController.class).withRel("person"));
	    add(linkTo(PersonController.class).slash(person.getId()).withRel("delete"));
	    add(linkTo(methodOn(PersonController.class).getV1(id)).withSelfRel());
	}
	
	public PersonResource(PersonDTOV2 person) {
		this.person = person;
	    final long id = person.getId();
	    add(linkTo(PersonController.class).withRel("person"));
	    add(linkTo(PersonController.class).slash(person.getId()).withRel("delete"));
	    add(linkTo(methodOn(PersonController.class).getV2(id)).withSelfRel());
	}
}
