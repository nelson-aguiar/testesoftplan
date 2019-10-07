package com.nelsonaguiar.testesoftplan.enums;

import com.nelsonaguiar.testesoftplan.dto.AbstractPerson;
import com.nelsonaguiar.testesoftplan.dto.PersonDTOV1;
import com.nelsonaguiar.testesoftplan.dto.PersonDTOV2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VersionAPI {
	
	V1(1, "V1", new PersonDTOV1()),
	V2(1, "V2", new PersonDTOV2());
	
	private int id;	
	private String code;
	private AbstractPerson person;

}
