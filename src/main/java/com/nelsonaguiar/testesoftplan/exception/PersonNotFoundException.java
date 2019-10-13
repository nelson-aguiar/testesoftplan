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
		super("Pessoa não localizada com id: " + id);
	}

	public PersonNotFoundException(final String name) {
		super("Pessoa não localizada com id: " + name);
	}
	
	public PersonNotFoundException() {
		super("Pessoa não pode ser encontrada");
	}
	
	

}
