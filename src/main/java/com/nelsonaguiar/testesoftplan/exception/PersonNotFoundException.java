package com.nelsonaguiar.testesoftplan.exception;

import lombok.Getter;

@Getter
public class PersonNotFoundException extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Long id;

	public PersonNotFoundException(final long id) {
		super("Person could not be found with id: " + id);
	}

	public PersonNotFoundException(final String name) {
		super("Person could not be found with name: " + name);
	}

}
