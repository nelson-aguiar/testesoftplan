package com.nelsonaguiar.testesoftplan.exception;

public class PersonAlreadyExists extends RuntimeException {
	
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public PersonAlreadyExists(String msg) {
		super(msg);
	}

}
